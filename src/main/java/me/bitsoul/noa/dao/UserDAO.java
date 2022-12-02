package me.bitsoul.noa.dao;

import me.bitsoul.noa.entry.UserDO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * @author lxbang
 * @create 2022/12/2 4:17 下午
 */
@Repository
public class UserDAO extends BaseDAO<UserDO> {

    public UserDAO(MongoTemplate mongoTemplate) {
        super(mongoTemplate, UserDO.class);
    }

    /**
     * 通过钱包id获取用户
     * @param walletAddress
     * @return
     */
    public UserDO findByWalletAddress(String walletAddress){
        return findOne(Query.query(Criteria.where(UserDO.FIELD_WALLET_ADDRESS).is(walletAddress)));
    }

    /**
     * 保存用户
     * @param userDO
     * @return
     */
    public UserDO save(UserDO userDO){
        Long createAt = userDO.getCreateAt();
        if (Objects.isNull(createAt)){
            userDO.setCreateAt(System.currentTimeMillis());
        }
        return insert(userDO);
    }

}
