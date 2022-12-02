package me.bitsoul.noa.service;

import me.bitsoul.noa.dao.UserDAO;
import me.bitsoul.noa.dto.UserDTO;
import me.bitsoul.noa.entry.UserDO;
import me.bitsoul.noa.enums.IdSceneEnum;
import me.bitsoul.noa.enums.UserTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements IService<UserDO,UserDTO> {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private IdService idService;

    /**
     * 获取用户数据（自动创建用户）
     * @param walletAddress
     * @return
     */
    public UserDTO getUserWithCreate(String walletAddress){
        UserDO user = userDAO.findByWalletAddress(walletAddress);
        if (Objects.isNull(user)){
            // 创建用户
            user = createUser(walletAddress,UserTypeEnum.NORMAL);
        }
        return toDTO(user);
    }

    /**
     * 创建用户
     * @param walletAddress
     * @param userType
     * @return
     */
    private UserDO createUser(String walletAddress, UserTypeEnum userType){
        // todo 加锁保护
        UserDO userDO = new UserDO();
        userDO.setWalletAddress(walletAddress);
        userDO.setUserType(userType);
        Long id = idService.getId(IdSceneEnum.USER_ID);
        userDO.setUserId(id);
        return userDAO.save(userDO);
    }



}
