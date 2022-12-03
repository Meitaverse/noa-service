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

    public IdSequenceDAO(MongoTemplate mongoTemplate) {
        super(mongoTemplate, IdSequenceDO.class);
    }

    /**
     * 通过scene查询
     * @param scene
     * @return
     */
    public IdSequenceDO findByScene(String scene){
        return findOne(Query.query(Criteria.where(IdSequenceDO.FIELD_SCENE).is(scene)));
    }

    /**
     * 保存
     * @param idSequence
     * @return
     */
    public IdSequenceDO save(IdSequenceDO idSequence){
        return insert(idSequence);
    }

    /**
     * 获取新的id
     * @param scene
     * @param serialNo
     * @param step
     * @return
     */
    public Long getNextId(String scene,long serialNo,long step){
        Query query = Query.query(Criteria.where(IdSequenceDO.FIELD_SCENE).is(scene)
                .and(IdSequenceDO.FIELD_SERIAL_NO).is(serialNo));
        Update update = new Update();
        update.inc(IdSequenceDO.FIELD_SERIAL_NO,step);
        IdSequenceDO idSequence = super.findAndModify(query, update, FindAndModifyOptions.options().upsert(false).returnNew(true));
        return idSequence.getSerialNo();
    }
}
