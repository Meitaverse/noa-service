package me.bitsoul.noa.manager.cntract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.Collections;

@Service
public class ContractManager {
    @Value(value = "${web3.contract_address}")
    private String ownerAddress;

    @Autowired
    private Greeter contract;

    public String mint(String name, String desc, String image, Long eventId, String eventMetadataURI) throws Exception {
        //调用合约方法
        Greeter.SlotDetail slotDetail = new Greeter.SlotDetail(name, desc, image, BigInteger.valueOf(eventId), eventMetadataURI);

        RemoteCall<TransactionReceipt> setWord = contract.mint(slotDetail, ownerAddress, Collections.emptyList());
        TransactionReceipt transactionReceipt = setWord.send();
        return transactionReceipt.getTransactionHash();
    }

}
