package com.pormaria.api.crud.services;

import com.pormaria.api.crud.models.NotepadModel;
import com.pormaria.api.crud.repositories.INoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    INoteRepository iNoteRepository;

    public NotepadModel saveNote(NotepadModel notepadModel) {
        return iNoteRepository.save(notepadModel);
    }

    public List<NotepadModel> saveAllNote(List<NotepadModel> notepadModel) {
        return iNoteRepository.saveAll(notepadModel);
    }

    public Optional<NotepadModel> getNoteById(Long id) {
        return iNoteRepository.findById(id);
    }

    public Optional<List<NotepadModel>> getNotesForEvents(Long id) {
        return Optional.ofNullable(iNoteRepository.findByEventId(id));
    }

    public Optional<NotepadModel> updateNotepadById(NotepadModel request, Long id) {
        if (iNoteRepository.findById(id).isPresent()) {
            NotepadModel note = iNoteRepository.findById(id).get();
            note.setCreationDate(request.getCreationDate());
            note.setLastModifiedDate(new Date());
            note.setLastModifierUser(request.getWriteUser());
            note.setNotes(request.getNotes());
            iNoteRepository.save(note);
            return Optional.of(note);
        }
        return Optional.empty();
    }

    public boolean deleteNoteById(Long id) {
        try {
            iNoteRepository.deleteById(id);
            System.out.println("Note id deleted: " + id);
            return true;
        } catch (Exception e) {
            System.out.println("Note id cant deleted: " + id);
            return false;
        }
    }

    // TODO: revisar, llama pero no elimina nada en la base de datos
    public void deleteNotes(List<NotepadModel> notes) {
        try {
            for (NotepadModel note : notes) {
                iNoteRepository.deleteById(note.getId());
            }
            System.out.println("notes deleted "+ notes.size());
        } catch (Exception e) {
            System.out.println("Notes cant be delete.");
        }
    }

    public boolean deleteNoteByEventId(Long id) {
        try {
            iNoteRepository.deleteByEventId(id);
            System.out.println("Notes for event id deleted: " + id);
            return true;
        } catch (Exception e) {
            System.out.println("Notes for event id cant deleted: " + id);
            return false;
        }
    }

}
