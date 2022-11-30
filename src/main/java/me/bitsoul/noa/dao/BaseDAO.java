package me.bitsoul.noa.dao;

import com.mongodb.client.result.UpdateResult;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.bson.Document;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author liujinhua
 */
public abstract class BaseDAO<DO> {
    protected final MongoTemplate mongoTemplate;
    private final Class<DO> cls;

    protected BaseDAO(MongoTemplate mongoTemplate, Class<DO> cls) {
        this.mongoTemplate = mongoTemplate;
        this.cls = cls;
    }

    public List<DO> find(Query query) {
        return mongoTemplate.find(query, cls);
    }

    public List<DO> findAll() {
        return find(new Query());
    }

    public DO findOne(Query query) {
        return mongoTemplate.findOne(query, cls);
    }


    public DO insert(DO item) {
        mongoTemplate.insert(item);
        return item;
    }

    public Collection<DO> insertMany(List<DO> list) {
        mongoTemplate.insert(list, cls);
        return list;
    }


    public UpdateResult updateOne(Query query, Update update) {
        return mongoTemplate.updateFirst(query, update, cls);
    }

    public UpdateResult upsert(Query query, Update update) {
        return mongoTemplate.upsert(query, update, cls);
    }

    public UpdateResult updateMany(Query query, Update update) {
        return mongoTemplate.updateMulti(query, update, cls);
    }

    protected DO deleteOne(Query query) {
        return mongoTemplate.findAndRemove(query, cls);
    }

    protected List<DO> deleteMany(Query query) {
        return mongoTemplate.findAllAndRemove(query, cls);
    }


    public long count() {
        return count(new Query());
    }

    protected long count(Query query) {
        return mongoTemplate.count(query, cls);
    }


    protected DO findAndModify(Query query, Update update, FindAndModifyOptions options) {
        return mongoTemplate.findAndModify(query, update, options, cls);
    }

    public BulkOperations bulkOps(BulkOperations.BulkMode mode) {
        return mongoTemplate.bulkOps(mode, cls);
    }

    public static String generateId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void updateSetNonNull(Update update, String key, Object value) {
        if (Objects.nonNull(value)) {
            update.set(key, value);
        }
    }
    
}
