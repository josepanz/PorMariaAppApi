package com.pormaria.api.crud.controllers;

import com.pormaria.api.crud.models.EventModel;
import com.pormaria.api.crud.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(path = "/getAllEvents")
    public ArrayList<EventModel> getAllEvents() {
        return this.eventService.getAllEvents();
    }

    @PostMapping(path = "/postEvent")
    public EventModel insertEvent(@RequestBody EventModel event) {
        return this.eventService.saveEvent(event);
    }

    @GetMapping(path = "/getEvent/{id}")
    public Optional<EventModel> getEventById(@PathVariable("id") Long id) {
        return this.eventService.getEventById(id);
    }

    @PutMapping(path = "/update/{id}")
    public Optional<EventModel> updateEventById(@RequestBody EventModel request, @PathVariable("id") Long id) {
        return this.eventService.updateEventById(request, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteEventById(@PathVariable("id") Long id) {
        return this.eventService.deleteEventById(id);
    }

}
