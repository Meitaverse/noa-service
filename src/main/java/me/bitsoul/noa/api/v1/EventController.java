package me.bitsoul.noa.api.v1;

import io.swagger.annotations.ApiOperation;
import me.bitsoul.noa.dto.resp.BaseResp;
import me.bitsoul.noa.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;

    @ApiOperation("活动创建接⼝")
    @PostMapping("/v1/create_event")
    private BaseResp createEvent() {
        return new BaseResp();
    }
}
