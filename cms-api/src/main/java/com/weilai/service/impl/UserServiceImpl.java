package com.weilai.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.common.PageQuery;
import com.weilai.entity.Code;
import com.weilai.entity.User;
import com.weilai.exception.CustomException;
import com.weilai.mapper.UserMapper;
import com.weilai.request.QueryUserParamsRequest;
import com.weilai.request.RegisterUserRequest;
import com.weilai.service.CoderService;
import com.weilai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.weilai.utils.SystemConstants.MAX_PAGE_SIZE;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {



    @Autowired
    private CoderService coderService;

    @Autowired
    private StringRedisTemplate stringTemplate;

//    @Value("${myconfig.salt}")
//    private String salt ;
//    /**
//     * 登录查询
//     * @param user
//     * @return
//     */
//    @Override
//    public R login(User user) {
//
////        Userpo po = new Userpo();
////        User u = new User();
//        //判断redis中是否存在该对象
////        String token = user.getUsername();
////        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
////        byte[] sign1 = sign.sign(token);
////        token = LOGIN_TOKEN + token;
//        //System.out.println(token);
//        //redis中拥有token
////        if (stringTemplate.hasKey(token)){
////            String users = stringTemplate.opsForValue().get(token);
////            System.out.println(users);
////            po = JSONUtil.toBean(users, Userpo.class);
////            System.out.println("111111");
////            System.out.println(po);
////            return po;
////        }
//        //没用token 从数据空中查找
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(User::getUsername,user.getUsername());
//        User user1 = getOne(wrapper);
//
//        //判断密码是否一致 不一样直接返回空
//        if(!user1.getPassword().equals(user.getPassword())){
//            return null;
//        }
//        //user不为空去数据库中查询
////        if(user1 != null){
////            //存入redis一份 //把对象加密后返回存入redis
//////            stringTemplate.opsForValue().set(token  ,JSONUtil.toJsonStr(user1),60, TimeUnit.MINUTES);
////            //System.out.println(user);
////             po = BeanUtil.copyProperties(user1, Userpo.class);
////             System.out.println(po);
////             return po;
////        }
//        po = BeanUtil.copyProperties(user1, Userpo.class);
////        BeanUtil.copyProperties(user,po);
//
//        return po;
//    }
    //查询user信息
    @Override
    public Page<User> getUser(QueryUserParamsRequest request) {
        Page<User> page = new Page<>(request.getCurrent(),MAX_PAGE_SIZE);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //为空就查全部
        if (StrUtil.isNotBlank(request.getNickname())){
            wrapper.like(User::getNickname,request.getNickname());
        }
        if (StrUtil.isNotBlank(request.getEmail())){
            wrapper.eq(User::getEmail,request.getEmail());
        }
        if (StrUtil.isNotBlank(request.getUserType())){
            wrapper.eq(User::getUserType,request.getUserType());
        }
        if (StrUtil.isNotBlank(request.getBan())){
            wrapper.eq(User::getBan,request.getBan());
        }
        Page<User> userPage = this.baseMapper.selectPage(page, wrapper);
        return userPage;
    }

    /**
     * 保持用户的方法
     * @param user
     * @return
     */
    @Override
    public Boolean addUser(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(User::getUsername,user.getUsername());
        User one = getOne(wrapper);
        //判断是否存在当前用户
        if (one != null){
            return false;
        }
        user.setUserType(1);
        user.setIsValid(1);
        DateTime now = DateTime.now();

        save(user);
        return true;
    }

    /**
     * 使用redis来实现注册功能  从redis中读取验证码实现注册功能
     * @param user
     * @return
     */
    @Transactional
    @Override
    public String register(RegisterUserRequest user) {
        //判断redis中是否有这个验证码
        String email = user.getEmail();
        if (!stringTemplate.hasKey(email)){
            //没有这个验证码就返回注册失败
            throw new CustomException("验证码失效请重新获取");
        }
        //判断验证码是否一致
        String redisCode = stringTemplate.opsForValue().get(email);
        if(!user.getCode().equals(redisCode)){
            throw new CustomException("验证码错误");
        }
        //判断用户名是否存在
        String username = user.getUsername();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User one = this.getOne(wrapper);
        if (one != null){
            throw new CustomException("用户名已存在请换一个吧");
        }
        //有验证码吧验证码从redis中删除
        stringTemplate.delete(email);
        //设置一些基本默认信息
        user.setAvatar("http://139.159.252.234:8083/upload/5ee38a06-ff99-4092-8c36-b508db1eb6c5.jpg");
        user.setUserType(0);
        user.setIsValid(1);
        //把用户命和密码注册
        Boolean flag = this.save(user);
        if (!flag){
            throw new CustomException("注册失败，请稍后再试");
        }
        return "注册成功";
    }

    /**
     * 这个方法数据库限制了邮箱和账户的唯一性 所以这里不去判断账号和邮箱的唯一性
     * @param user
     * @param code
     * @return
     */
    /**
     *  这个是使用email来实现的
     */
    @Override
    public String registerForEmail(User user, String code) {
        if(user == null){
            return "数据不合法";
        }

        //构建wrapper
        LambdaQueryWrapper<Code> wrapper = new LambdaQueryWrapper<Code>();
        Code code1 = new Code();
        code1.setEmail(user.getEmail());
        //判断验证码是否正确
        Code one = coderService.getOne(wrapper);
        if(one == null){
            return "验证码错误";
        }
//        System.out.println(code);
//        System.out.println(one.getCode());
        //判断验证码是否相同
//        System.out.println(!code.equals(one.getCode()));
        if(!code.equals(one.getCode())){

            //不相同
            return "验证码错误";
        }
//        System.out.println("11111111111");
        addUser(user);
        //注册完之后把数据库中的验证码删除
        LambdaQueryWrapper<Code> del = new LambdaQueryWrapper<>();
        del.eq(Code::getEmail,user.getEmail());
        coderService.remove(del);
        return "添加成功";
    }

    @Override
    public User getOneById(Long uid) {
        User user = this.getById(uid);

        return user;
    }

//    @Transactional
//    @Override
//    public Userpo banUser(String email) {
//        Userpo po = new Userpo();
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(User::getEmail,email);
//        User one = getOne(wrapper);
//        //改状态
//        one.setBan("0");
//        //保存回去
//        boolean b = updateById(one);
//        if(!b){
//            return null;
//        }else{
//            po = BeanUtil.copyProperties(one,Userpo.class);
//            return po;
//        }
//
//    }
//    @Transactional
//    @Override
//    public Userpo unBan(String email,Integer uid) {
//        Userpo po = new Userpo();
////        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
////        wrapper.eq(User::getEmail,email);
//        User one = this.getOneById(uid);
//        if (one == null){
//            return null;
//        }
//        if (!one.getEmail().equals(email)){
//            return null;
//        }
//        //改状态
//        one.setBan("1");
//        //保存回去
//         updateById(one);
//        boolean b = updateById(one);
//        if(!b){
//            return null;
//        }else{
//            po = BeanUtil.copyProperties(one,Userpo.class);
//            return po;
//        }
//    }

    @Override
    public Page<User> listByPage(PageQuery query) {

//        Page<User> page = new Page<>(query.getPageNum(), query.getPageSize());
        System.out.println(query);
//        List<User> user = this.baseMapper.listByPage(query);
//        //通过uid查询用户信息
//        page.setRecords(user);
//        page.setTotal(user.size());
        return null;
    }

    @Override
    public void resetPwd(List<Long> ids) {
        this.baseMapper.update(Wrappers.<User>lambdaUpdate().set(User::getPassword,"123456").in(User::getId,ids));
    }


}
