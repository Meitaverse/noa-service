package me.bitsoul.noa.dao;

import me.bitsoul.noa.entry.NoATokenDO;
import me.bitsoul.noa.enums.NoATokenStatusEnum;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.bitsoul.noa.constant.FieldConstant.FIELD_UPDATED_AT;
import static me.bitsoul.noa.entry.NoATokenDO.*;

@Repository
public class NoATokenDAO extends BaseDAO<NoATokenDO> {
    public NoATokenDAO(MongoTemplate mongoTemplate) {
        super(mongoTemplate, NoATokenDO.class);
    }


    public void setTransactionHash(String walletAddress, String eventId, String transactionHash) {
        updateOne(Query.query(Criteria.where(FIELD_TO_ADDRESS).is(walletAddress).and(FIELD_EVENT_ID).is(eventId)), Update.update(FIELD_TRANSACTION_HASH, transactionHash));
    }

    public NoATokenDO findNoA(String toAddress, String eventId) {
        return findOne(Query.query(Criteria.where(FIELD_TO_ADDRESS).is(toAddress).and(FIELD_EVENT_ID).is(eventId)));
    }

    public List<NoATokenDO> findClaimingNoaToken() {
        return find(Query.query(Criteria.where(FIELD_STATUS).is(NoATokenStatusEnum.UNRECEIVED).and(FIELD_TRANSACTION_HASH).exists(true)));
    }

    public void tokenReceivedByHash(String transactionHash, String tokenId) {
        Update update = new Update();
        update.set(FIELD_STATUS, NoATokenStatusEnum.RECEIVED);
        update.set(FIELD_TOKEN_ID, tokenId);
        update.set(FIELD_UPDATED_AT, System.currentTimeMillis());
        updateOne(Query.query(Criteria.where(FIELD_TRANSACTION_HASH).is(transactionHash)), update);
    }
}
