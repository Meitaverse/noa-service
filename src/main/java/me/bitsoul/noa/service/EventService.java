package me.bitsoul.noa.service;

import me.bitsoul.noa.dao.EventDAO;
import me.bitsoul.noa.dto.EventDTO;
import me.bitsoul.noa.entry.EventDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EventService {
    @Autowired
    private EventDAO eventDAO;

    public void createEvent() {

    }

    public EventDTO getEvent(String eventId) {
        return toDTO(eventDAO.findOne(eventId));
    }

    private EventDTO toDTO(EventDO event) {
        if (Objects.isNull(event)) {
            return null;
        }
        EventDTO dto = new EventDTO();
        BeanUtils.copyProperties(event, dto);
        return dto;
    }
}
