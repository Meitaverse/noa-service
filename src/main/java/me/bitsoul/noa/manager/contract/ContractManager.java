package me.bitsoul.noa.manager.contract;

import org.springframework.stereotype.Service;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.Collections;

@Service
public class ContractManager {

    private NoaContract contract;

    public String mint(String to_address, String name, String desc, String image, String eventId, String eventMetadataURI) throws Exception {
        //调用合约方法
        NoaContract.SlotDetail slotDetail = new NoaContract.SlotDetail(name, desc, image, new BigInteger(eventId), eventMetadataURI);

        RemoteCall<TransactionReceipt> setWord = contract.mint(slotDetail, to_address, Collections.emptyList());
         TransactionReceipt transactionReceipt = setWord.send();
        return transactionReceipt.getTransactionHash();
    }

}
