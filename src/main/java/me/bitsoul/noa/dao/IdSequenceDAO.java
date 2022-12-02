package me.bitsoul.noa.dao;

import me.bitsoul.noa.entry.IdSequenceDO;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author lxbang
 * @create 2022/12/2 2:46 下午
 */
@Repository
public class IdSequenceDAO extends BaseDAO<IdSequenceDO> {

    public static final int INIT_ID = 10000;

    public IdSequenceDAO(MongoTemplate mongoTemplate) {
        super(mongoTemplate, IdSequenceDO.class);
    }

    public Long getNextId(String scene){
        Query query = Query.query(Criteria.where(IdSequenceDO.FIELD_SCENE).is(scene));
        Update update = new Update();
        update.inc(IdSequenceDO.FIELD_SERIAL_NO,1);
        update.setOnInsert(IdSequenceDO.FIELD_SERIAL_NO,INIT_ID);
        IdSequenceDO idSequence = super.findAndModify(query, update, FindAndModifyOptions.options().upsert(true).returnNew(true));
        return idSequence.getSerialNo();
    }
}
