package me.bitsoul.noa.config;

import me.bitsoul.noa.manager.cntract.Greeter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@Configuration
public class Web3jConfig {

    @Value(value = "${web3.rpc_url}")
    private String rpcUrl;

    @Value(value = "${web3.contract_address}")
    private String contractAddress;

    @Bean
    public Web3j createWeb3j() {
        return Web3j.build(new HttpService(rpcUrl));
    }

    @Bean
    public Credentials credentials() {
        return Credentials.create("1223");
    }

    @Bean
    public Greeter createGreeter(Web3j web3, TransactionManager transactionManager) throws Exception {
        BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
        return Greeter.load(contractAddress, web3, transactionManager, new StaticGasProvider(gasPrice, Contract.GAS_LIMIT));
    }

    @Bean
    public TransactionManager createTransactionManager(Web3j web3, Credentials credentials) {
        return new RawTransactionManager(web3, credentials, 3);
    }
}
