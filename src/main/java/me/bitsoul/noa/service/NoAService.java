package me.bitsoul.noa.service;

import me.bitsoul.noa.constant.HeadConstants;
import me.bitsoul.noa.dao.NoATokenDAO;
import me.bitsoul.noa.dto.NoATokenDTO;
import me.bitsoul.noa.dto.UserDTO;
import me.bitsoul.noa.entry.NoATokenDO;
import me.bitsoul.noa.enums.NoATokenStatusEnum;
import me.bitsoul.noa.exception.BusinessException;
import me.bitsoul.noa.manager.contract.ContractManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthTransaction;

import java.util.List;
import java.util.Objects;

@Service
public class NoAService implements IService<NoATokenDO, NoATokenDTO> {
    @Autowired
    private NoATokenDAO noATokenDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private ContractManager contractManager;

    @Autowired
    private Web3j web3j;

    public NoATokenDTO askForNoa(Long userId, String eventId) {
        UserDTO user = userService.getUser(userId);
        if (Objects.isNull(user)) {
            throw new BusinessException(HeadConstants.PARAM_ERROR_CODE, HeadConstants.PARAM_ERROR_MSG);
        }
        if (Objects.nonNull(noATokenDAO.findNoA(user.getWalletAddress(), eventId))) {
            throw new BusinessException(HeadConstants.FAIL_CODE, "请勿重复领取");
        }
        NoATokenDO noaToken = new NoATokenDO();
        noaToken.setStatus(NoATokenStatusEnum.UNRECEIVED);
        noaToken.setClaimUrl("http://claim_url");
        noaToken.setToAddress(user.getWalletAddress());
        noaToken.setEventId(eventId);
        noaToken.setCreateAt(System.currentTimeMillis());
        noaToken.setUpdatedAt(System.currentTimeMillis());
        noATokenDAO.insert(noaToken);
        return toDTO(noaToken);
    }

    public void claimNoa(String walletAddress, String eventId) {
        NoATokenDO noaToken = noATokenDAO.findNoA(walletAddress, eventId);
        if (Objects.isNull(noaToken)) {
            throw new BusinessException(HeadConstants.FAIL_CODE, "您未领取该勋章");
        }
        try {
            String transactionHash = contractManager.mint(walletAddress, "name", "desc", "image", eventId, "http://event_metadata_url");
            noATokenDAO.setTransactionHash(walletAddress, eventId, transactionHash);
        } catch (Exception e) {
            throw new BusinessException(HeadConstants.FAIL_CODE, "铸造异常:" + e.getMessage());
        }
    }

    public void checkMintSuccess() throws Exception {
        List<NoATokenDO> tokenList = noATokenDAO.findClaimingNoaToken();
        for (NoATokenDO token : tokenList) {
            String tokenId = contractManager.getTokenIdByHash(token.getTransactionHash());
            if (Objects.nonNull(tokenId)) {
                noATokenDAO.tokenReceivedByHash(token.getTransactionHash(), tokenId);
            }
        }

    }
}
