package me.bitsoul.noa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class NoaServiceTests {
    @Autowired
    private NoAService noAService;

    @Test
    public void askForNoa() {
        noAService.askForNoa(10000L, "2");
    }

    @Test
    public void claimNoa() {
        noAService.claimNoa("0x70997970C51812dc3A010C7d01b50e0d17dc79C8", "2");
    }

    @Test
    public void checkMintSuccess() throws Exception {
        noAService.checkMintSuccess();
    }
}
