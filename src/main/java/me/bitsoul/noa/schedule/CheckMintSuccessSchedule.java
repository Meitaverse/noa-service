package me.bitsoul.noa.schedule;

import lombok.extern.slf4j.Slf4j;
import me.bitsoul.noa.service.NoAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CheckMintSuccessSchedule {
    @Autowired
    private NoAService noAService;

    @Scheduled(cron = "*/10 * * * * *")
    public void checkMintSuccess() {
        try {
            noAService.checkMintSuccess();
        } catch (Exception e) {
            log.error("checkMintSuccess 异常:", e);
        }
    }
}
