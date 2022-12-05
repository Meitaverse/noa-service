package me.bitsoul.noa.manager.cntract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.Collections;

@Service
public class ContractManager {

    private NoaContract contract;

    public String mint(String name, String desc, String image, Long eventId, String eventMetadataURI) throws Exception {
        //调用合约方法
        NoaContract.SlotDetail slotDetail = new NoaContract.SlotDetail(name, desc, image, BigInteger.valueOf(eventId), eventMetadataURI);

        RemoteCall<TransactionReceipt> setWord = contract.mint(slotDetail, "", Collections.emptyList());
        TransactionReceipt transactionReceipt = setWord.send();
        return transactionReceipt.getTransactionHash();
    }

}
