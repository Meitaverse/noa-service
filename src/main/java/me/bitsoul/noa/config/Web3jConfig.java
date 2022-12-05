package me.bitsoul.noa.config;

import me.bitsoul.noa.manager.cntract.NoaContract;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@ConfigurationProperties(prefix = "web3")
public class Web3jConfig {

    private String rpcUrl;
    private String contractAddress;
    private Integer chainId;
    private String deployerAddressPrivateKey;
    private String deployerAddressPrivateKeyPath;


    @Bean
    public Web3j createWeb3j() {
        return Web3j.build(new HttpService(rpcUrl));
    }

    @Bean
    public Credentials credentials() throws IOException {
        if (StringUtils.isNotBlank(deployerAddressPrivateKey)) {
            return Credentials.create(deployerAddressPrivateKey);
        } else {
            return Credentials.create(StringUtils.join(Files.lines(Paths.get(deployerAddressPrivateKeyPath))));
        }
    }

    @Bean
    public NoaContract createGreeter(Web3j web3, TransactionManager transactionManager) throws Exception {
        return NoaContract.load(contractAddress, web3, transactionManager, new StaticGasProvider(BigInteger.valueOf(21000), null));
    }

    @Bean
    public TransactionManager createTransactionManager(Web3j web3, Credentials credentials) {
        return new RawTransactionManager(web3, credentials, chainId);
    }

    public String getRpcUrl() {
        return rpcUrl;
    }

    public void setRpcUrl(String rpcUrl) {
        this.rpcUrl = rpcUrl;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public Integer getChainId() {
        return chainId;
    }

    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }

    public String getDeployerAddressPrivateKey() {
        return deployerAddressPrivateKey;
    }

    public void setDeployerAddressPrivateKey(String deployerAddressPrivateKey) {
        this.deployerAddressPrivateKey = deployerAddressPrivateKey;
    }

    public String getDeployerAddressPrivateKeyPath() {
        return deployerAddressPrivateKeyPath;
    }

    public void setDeployerAddressPrivateKeyPath(String deployerAddressPrivateKeyPath) {
        this.deployerAddressPrivateKeyPath = deployerAddressPrivateKeyPath;
    }
}
