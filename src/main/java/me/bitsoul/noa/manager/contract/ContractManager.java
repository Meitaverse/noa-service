package me.bitsoul.noa.manager.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContractManager {

    @Autowired
    private Web3j web3;

    @Autowired
    private NoaContract noaContract;

    public String mint(String to_address, String name, String desc, String image, String eventId, String eventMetadataURI) throws Exception {
        //调用合约方法
        NoaContract.SlotDetail slotDetail = new NoaContract.SlotDetail(name, desc, image, new BigInteger(eventId), eventMetadataURI);

        RemoteCall<TransactionReceipt> setWord = noaContract.mint(slotDetail, to_address, Collections.singletonList(new byte[32]));
        TransactionReceipt transactionReceipt = setWord.send();
        return transactionReceipt.getTransactionHash();
    }

    public String getTokenIdByHash(String transactionHash) throws Exception {
        EthGetTransactionReceipt ethGetTransactionReceipt = web3.ethGetTransactionReceipt(transactionHash).send();

        return ethGetTransactionReceipt
                .getTransactionReceipt()
                .map(noaContract::getEventTokenEvents)
                .map(list -> list.get(0))
                .map(response -> response.tokenId)
                .map(BigInteger::toString)
                .get();
    }

}
