package me.bitsoul.noa.api.v1;

import me.bitsoul.noa.dto.resp.BaseResp;
import me.bitsoul.noa.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("create_event")
    private BaseResp createEvent() {
        return new BaseResp();
    }
}
