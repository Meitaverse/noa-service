package me.bitsoul.noa.manager;

import me.bitsoul.noa.manager.contract.ContractManager;
import me.bitsoul.noa.manager.contract.NoaContract;
import me.bitsoul.noa.service.NoAService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.*;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class ContractManagerTests {

    @Autowired
    private ContractManager contractManager;


    @Autowired
    private NoaContract noaContract;

    @Test
    public void mint() throws Exception {
        String hash = contractManager.mint("0x70997970C51812dc3A010C7d01b50e0d17dc79C8", "name", "desc", "image", "1", "http://url");
        System.out.println(hash);
    }

    @Autowired
    private Web3j web3j;

    @Autowired
    private NoAService noAService;

    @Test
    public void checkMintSuccess() throws Exception {
        noAService.checkMintSuccess();
    }
}
