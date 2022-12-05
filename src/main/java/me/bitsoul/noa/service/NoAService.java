package me.bitsoul.noa.service;

import me.bitsoul.noa.constant.HeadConstants;
import me.bitsoul.noa.dao.NoATokenDAO;
import me.bitsoul.noa.dto.EventDTO;
import me.bitsoul.noa.dto.NoATokenDTO;
import me.bitsoul.noa.dto.UserDTO;
import me.bitsoul.noa.entry.NoATokenDO;
import me.bitsoul.noa.enums.NoATokenStatusEnum;
import me.bitsoul.noa.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class NoAService implements IService<NoATokenDO, NoATokenDTO> {
    @Autowired
    private NoATokenDAO noATokenDAO;

    @Autowired
    private UserService userService;

    public NoATokenDTO askForNoa(String userId, String eventId) {
        UserDTO user = userService.getUser(userId);
        if (Objects.isNull(user)) {
            throw new BusinessException(HeadConstants.PARAM_ERROR_CODE, HeadConstants.PARAM_ERROR_MSG);
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

    public void claimNoa() {

    }
}
