package me.bitsoul.noa.api.v1;

import me.bitsoul.noa.dto.UserDTO;
import me.bitsoul.noa.dto.resp.BaseResp;
import me.bitsoul.noa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public BaseResp signIn() {
        return new BaseResp();
    }

}
