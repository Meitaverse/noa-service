package me.bitsoul.noa.api.v1;

import io.swagger.annotations.ApiOperation;
import me.bitsoul.noa.constant.HeadConstants;
import me.bitsoul.noa.dto.NoATokenDTO;
import me.bitsoul.noa.vo.req.AskForNoaReq;
import me.bitsoul.noa.vo.req.ClaimNoaReq;
import me.bitsoul.noa.vo.resp.AskForNoaResp;
import me.bitsoul.noa.vo.resp.BaseResp;
import me.bitsoul.noa.service.NoAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoaController {
    @Autowired
    public NoAService noaService;

    @ApiOperation("勋章申请接⼝")
    @PostMapping("/v1/ask_for_noa")
    public AskForNoaResp askForNoa(@RequestBody AskForNoaReq body) {
        NoATokenDTO noAToken = noaService.askForNoa(body.getUserId(), body.getEventId());

        AskForNoaResp resp = new AskForNoaResp();
        resp.setErrCode(HeadConstants.SUCCESS_CODE);
        resp.setMsg(HeadConstants.SUCCESS_MSG);
        resp.setClaimUrl(noAToken.getClaimUrl());

        return resp;
    }

    @ApiOperation("勋章铸造接⼝")
    @PostMapping("/v1/claim_noa")
    public BaseResp claimNoa(@RequestAttribute String walletAddress, @RequestBody ClaimNoaReq body) {
        noaService.claimNoa(walletAddress, body.getEventId());
        BaseResp resp = new BaseResp();
        resp.setErrCode(HeadConstants.SUCCESS_CODE);
        resp.setMsg(HeadConstants.SUCCESS_MSG);
        return resp;
    }

}
