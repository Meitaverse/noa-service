package me.bitsoul.noa.dao;

import me.bitsoul.noa.entry.NoATokenDO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NoATokenDAO extends BaseDAO<NoATokenDO> {
    public NoATokenDAO(MongoTemplate mongoTemplate) {
        super(mongoTemplate, NoATokenDO.class);
    }


}
