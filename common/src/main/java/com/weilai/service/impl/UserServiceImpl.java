package com.weilai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.common.CommonQuery;
import com.weilai.entity.Code;
import com.weilai.entity.User;
import com.weilai.mapper.UserMapper;
import com.weilai.pojo.Userpo;
import com.weilai.service.CoderService;
import com.weilai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${myconfig.salt}")
    private String salt ;
    /**
     * 登录查询
     * @param user
     * @return
     */
    @Override
    public Userpo login(User user) {

        Userpo po = new Userpo();
//        User u = new User();
        //判断redis中是否存在该对象
//        String token = user.getUsername();
//        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
//        byte[] sign1 = sign.sign(token);
//        token = LOGIN_TOKEN + token;
        //System.out.println(token);
        //redis中拥有token
//        if (stringTemplate.hasKey(token)){
//            String users = stringTemplate.opsForValue().get(token);
//            System.out.println(users);
//            po = JSONUtil.toBean(users, Userpo.class);
//            System.out.println("111111");
//            System.out.println(po);
//            return po;
//        }
        //没用token 从数据空中查找
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        User user1 = getOne(wrapper);

        //判断密码是否一致 不一样直接返回空
        if(!user1.getPassword().equals(user.getPassword())){
            return null;
        }
        //user不为空去数据库中查询
//        if(user1 != null){
//            //存入redis一份 //把对象加密后返回存入redis
////            stringTemplate.opsForValue().set(token  ,JSONUtil.toJsonStr(user1),60, TimeUnit.MINUTES);
//            //System.out.println(user);
//             po = BeanUtil.copyProperties(user1, Userpo.class);
//             System.out.println(po);
//             return po;
//        }
        po = BeanUtil.copyProperties(user1, Userpo.class);
//        BeanUtil.copyProperties(user,po);

        return po;
    }
    //查询user信息
    @Override
    public Page<User> getUser(String username,Integer current) {

        Page<User> page = new Page<>(current,MAX_PAGE_SIZE);
        //
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //为空就查全部
        if (username != null ){
            wrapper.like(User::getUsername,username);
        }

        page = page(page, wrapper);
//        Page<Userpo> pagepo = new Page<>();
//        BeanUtil.copyProperties(page,pagepo);
//        System.out.println(page1);
//        //查询到所有的用户信息
//        List<User> list = list(wrapper);
//        //用一个集合来存储po对象
//        Userpo po = new Userpo();
//        List<Userpo> poList = new ArrayList<>();
//        //去封装成po对象返回
//        for(User k : list){
//            BeanUtil.copyProperties(k,po);
//            poList.add(po);
//        }
//        page.setRecords(poList);
        return page;
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
        user.setIsValid("1");
        DateTime now = DateTime.now();
        user.setCreateTime(now.toDateStr());
        save(user);
        return true;
    }

    /**
     * 使用redis来实现注册功能  从redis中读取验证码实现注册功能
     * @param user
     * @param code
     * @return
     */
    @Transactional
    @Override
    public String register(User user, String code) {

        //判断redis中是否有这个验证码
        String email = user.getEmail();
        if (!stringTemplate.hasKey(email)){
            //没有这个验证码就返回注册失败
            return "验证码失效了请重新去获取吧";
        }
        //判断验证码是否一致
        String redisCode = stringTemplate.opsForValue().get(email);

        if(!code.equals(redisCode)){
            return "验证码错误";
        }
        //判断用户名是否存在
        String username = user.getUsername();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User one = this.getOne(wrapper);
        if (one != null){
            return "用户名存在请换一个吧";
        }
        //有验证码吧验证码从redis中删除
        stringTemplate.delete(email);
//        System.out.println(user);
        //设置一些基本默认信息
        user.setUserIcon("http://139.159.252.234:8083/upload/5ee38a06-ff99-4092-8c36-b508db1eb6c5.jpg");
        user.setUserType(0);
        user.setCreateTime( DateUtil.date().toString());
        user.setIsValid("1");

        //把用户命和密码注册
        Boolean flag = this.save(user);
        System.out.println(flag);
        if (!flag){
            return "注册失败";
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
    public User getOneById(Integer uid) {
        User user = this.getById(uid);

        return user;
    }

    @Transactional
    @Override
    public Userpo banUser(String email) {
        Userpo po = new Userpo();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail,email);
        User one = getOne(wrapper);
        //改状态
        one.setBan("0");
        //保存回去
        boolean b = updateById(one);
        if(!b){
            return null;
        }else{
            po = BeanUtil.copyProperties(one,Userpo.class);
            return po;
        }

    }
    @Transactional
    @Override
    public Userpo unBan(String email,Integer uid) {
        Userpo po = new Userpo();
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(User::getEmail,email);
        User one = this.getOneById(uid);
        if (one == null){
            return null;
        }
        if (!one.getEmail().equals(email)){
            return null;
        }
        //改状态
        one.setBan("1");
        //保存回去
         updateById(one);
        boolean b = updateById(one);
        if(!b){
            return null;
        }else{
            po = BeanUtil.copyProperties(one,Userpo.class);
            return po;
        }
    }

    @Override
    public Page<User> listByPage(CommonQuery query) {

        Page<User> page = new Page<>(query.getPageNum(), query.getPageSize());
        System.out.println(query);
        List<User> user = this.baseMapper.listByPage(query);
        //通过uid查询用户信息
        page.setRecords(user);
        page.setTotal(user.size());
        return page;
    }


}
