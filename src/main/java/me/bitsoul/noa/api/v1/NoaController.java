package me.bitsoul.noa.api.v1;

import io.swagger.annotations.ApiOperation;
import me.bitsoul.noa.vo.resp.BaseResp;
import me.bitsoul.noa.service.NoATokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoaController {
    @Autowired
    public NoATokenService noaService;

    @ApiOperation("勋章申请接⼝")
    @PostMapping("/v1/ask_for_noa")
    public BaseResp askForNoa() {
        return new BaseResp();
    }

    @ApiOperation("勋章铸造接⼝")
    @PostMapping("/v1/claim_noa")
    public BaseResp claimNoa() {
        return new BaseResp();
    }

}
