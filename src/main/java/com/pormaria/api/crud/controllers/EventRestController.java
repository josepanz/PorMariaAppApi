package com.pormaria.api.crud.controllers;

import com.pormaria.api.crud.models.EventModel;
import com.pormaria.api.crud.models.EventTypeModel;
import com.pormaria.api.crud.models.NotepadModel;
import com.pormaria.api.crud.models.UserModel;
import com.pormaria.api.crud.services.EventService;
import com.pormaria.api.crud.services.NoteService;
import com.pormaria.api.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class EventRestController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;
    @Autowired
    private NoteService noteService;

    @GetMapping(path = "/getAllEvents")
    public ArrayList<EventModel> getAllEvents() {
        return this.eventService.getAllEvents();
    }

    @GetMapping(path = "/getAllEventsType")
    public ArrayList<EventTypeModel> getAllEventsType() {
        return this.eventService.getAllEventsType();
    }

    @PostMapping(path = "/postEvent")
    public EventModel insertEvent(@RequestBody EventModel event) {
        Optional<UserModel> user = null;
        if (Objects.nonNull(event.getCreatorUser())) {
            if (Objects.nonNull(event.getCreatorUser().getId())) {
                user = this.userService.getUserById(event.getCreatorUser().getId());
            } else if (Objects.nonNull(event.getCreatorUser().getUsername())) {
                user = this.userService.getUserByUsername(event.getCreatorUser().getUsername());
            }
        }
        user.ifPresent(event::setCreatorUser);
        if (Objects.nonNull(event.getNotes())) {
            for (NotepadModel note : event.getNotes()) {
                System.out.println(note.getNotes());
                user.ifPresent(note::setWriteUser);
                note.setEvent(event);
                note.setNotes(note.getNotes());
                note.setCreationDate(new Date());
            }
        } else {
            event.setNotes(null);
        }
        return this.eventService.saveEvent(event);
    }

    @GetMapping(path = "/getEvent/{id}")
    public Optional<EventModel> getEventById(@PathVariable("id") Long id) {
        return this.eventService.getEventById(id);
    }

    @PutMapping(path = "/update/{id}")
    public Optional<EventModel> updateEventById(@RequestBody EventModel event, @PathVariable("id") Long id) {
        Optional<UserModel> user = null;
        if (Objects.nonNull(event.getCreatorUser())) {
            if (Objects.nonNull(event.getCreatorUser().getId())) {
                user = this.userService.getUserById(event.getCreatorUser().getId());
            } else if (Objects.nonNull(event.getCreatorUser().getUsername())) {
                user = this.userService.getUserByUsername(event.getCreatorUser().getUsername());
            }
        }
        user.ifPresent(event::setCreatorUser);
        if (Objects.nonNull(event.getNotes())) {
            List<NotepadModel> auxNotes = new ArrayList<>();
            Map<Long, NotepadModel> notesValidate = this.noteService.getNotesForEvents(event.getId())
                    .get()
                    .stream()
                    .collect(Collectors.toMap(NotepadModel::getId, note -> note));

            for (NotepadModel note : event.getNotes()) {
                if (Objects.nonNull(note.getId())) {
                    System.out.println("nota existente del evento");
                    Optional<NotepadModel> auxNote = this.noteService.getNoteById(note.getId());
                    if (auxNote.isPresent()) {
                        auxNote.get().setNotes(note.getNotes());
                        auxNote.get().setLastModifiedDate(new Date());
                        user.ifPresent(auxNote.get()::setLastModifierUser);
                        auxNotes.add(auxNote.get());
                        if (!notesValidate.isEmpty()) {
                            notesValidate.remove(auxNote.get().getId());
                        }
                    }
                } else {
                    System.out.println("nota nueva del evento");
                    user.ifPresent(note::setWriteUser);
                    note.setEvent(event);
                    note.setCreationDate(new Date());
                    auxNotes.add(note);
                }
            }
            if (!notesValidate.isEmpty()) {
                List<NotepadModel> deletedNotes = new ArrayList<>(notesValidate.values());
                this.noteService.deleteNotes(deletedNotes);
            }
            event.setNotes(auxNotes);
        } else {
            event.setNotes(null);
        }
        return this.eventService.updateEventById(event, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteEventById(@PathVariable("id") Long id) {
        return this.eventService.deleteEventById(id);
    }

}
