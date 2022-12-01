package me.bitsoul.noa.api.v1;

import io.swagger.annotations.ApiOperation;
import me.bitsoul.noa.vo.resp.BaseResp;
import me.bitsoul.noa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("注册登录接⼝")
    @PostMapping("/signin")
    public BaseResp signIn() {
        return new BaseResp();
    }

}
