package me.bitsoul.noa.dao;

import me.bitsoul.noa.entry.EventDO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EventDAO extends BaseDAO<EventDO> {
    public EventDAO(MongoTemplate mongoTemplate) {
        super(mongoTemplate, EventDO.class);
    }

    public EventDO findOne(String eventId) {
        return findOne(Query.query(Criteria.where(EventDO.FIELD_EVENT_ID).is(eventId)));
    }
}
