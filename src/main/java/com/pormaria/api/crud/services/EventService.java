package com.pormaria.api.crud.services;

import com.pormaria.api.crud.models.EventModel;
import com.pormaria.api.crud.models.EventTypeModel;
import com.pormaria.api.crud.repositories.IEventRepository;
import com.pormaria.api.crud.repositories.IEventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    IEventRepository iEventRepository;

    @Autowired
    IEventTypeRepository iEventTypeRepository;

    public ArrayList<EventModel> getAllEvents() {
        return (ArrayList<EventModel>) iEventRepository.findAll();
    }

    public ArrayList<EventTypeModel> getAllEventsType() {
        return (ArrayList<EventTypeModel>) iEventTypeRepository.findAll();
    }

    public EventModel saveEvent(EventModel eventModel) {
        return iEventRepository.save(eventModel);
    }

    public Optional<EventModel> getEventById(Long id) {
        return iEventRepository.findById(id);
    }

    public Optional<EventModel> updateEventById(EventModel request, Long id) {
        Optional <EventModel> e = iEventRepository.findById(id);
        if (e.isPresent()) {
            EventModel event = e.get();
            event.setEventDate(request.getEventDate());
            event.setEventTimeTo(request.getEventTimeTo());
            event.setEventTitle(request.getEventTitle());
            event.setEventSubtitle(request.getEventSubtitle());
            event.setEventDescription(request.getEventDescription());
            event.setNotes(request.getNotes());
            iEventRepository.save(event);
            return Optional.of(event);
        }
        return Optional.empty();
    }

    public boolean deleteEventById(Long id) {
        try {
            iEventRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
