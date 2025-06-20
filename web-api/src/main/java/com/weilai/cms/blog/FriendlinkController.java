package com.weilai.cms.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.blog.model.resp.FriendLinkResp;
import com.weilai.blog.service.IFriendlinkService;
import com.weilai.system.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
@RestController
@RequestMapping("/admin/friendlink")
@Tag(name = "友链接口", description = "友链接口")
@RequiredArgsConstructor
public class FriendlinkController {

    private final IFriendlinkService friendlinkService;

    @PostMapping("/page")
    @Operation(summary = "友链分页",description = "友链分页")
    public Result<Page<FriendLinkResp>> page(){
        return Result.ok();
    }

    @GetMapping("/detection")
    @Operation(summary = "检查友链访问情况",description = "检查友链访问情况" )
    public Result<Void> detection(@RequestParam("ids") Long[] ids){
        friendlinkService.checkUrlIsAccessible(ids);
        return Result.ok();
    }
}
