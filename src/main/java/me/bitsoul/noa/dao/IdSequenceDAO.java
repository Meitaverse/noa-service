package me.bitsoul.noa.dao;

import me.bitsoul.noa.entry.IdSequenceDO;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Objects;

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
        return super.insert(idSequence);
    }

    /**
     * 获取新的序号
     * @param scene
     * @param version
     * @param step
     * @return
     */
    public Long getSerialNo(String scene, long version, long step){
        Query query = Query.query(Criteria.where(IdSequenceDO.FIELD_SCENE).is(scene)
                .and(IdSequenceDO.FIELD_VERSION).is(version));
        Update update = new Update();
        update.inc(IdSequenceDO.FIELD_SERIAL_NO,step);
        update.inc(IdSequenceDO.FIELD_VERSION,1);
        IdSequenceDO idSequence = super.findAndModify(query, update, FindAndModifyOptions.options().upsert(false).returnNew(true));
        return Objects.isNull(idSequence) ? null : idSequence.getSerialNo();
    }
}
