package me.bitsoul.noa.api.v1;

import io.swagger.annotations.ApiOperation;
import me.bitsoul.noa.service.AuthService;
import me.bitsoul.noa.vo.SignInVO;
import me.bitsoul.noa.vo.resp.BaseResp;
import me.bitsoul.noa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @ApiOperation("注册登录接⼝")
    @PostMapping("/signin")
    public BaseResp signIn(@RequestBody @Validated SignInVO body) {
        return authService.signIn(body.getWalletAddress(), body.getSigned());
    }

}
