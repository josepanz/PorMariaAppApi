package com.pormaria.api.crud.repositories;

import com.pormaria.api.crud.models.NotepadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INoteRepository extends JpaRepository<NotepadModel, Long> {
    @Query("SELECT n FROM NotepadModel n WHERE n.event.id = :eventId")
    List<NotepadModel> findByEventId(@Param("eventId") Long eventId);

    @Modifying
    @Query("DELETE FROM NotepadModel n WHERE n.event = :eventId")
    void deleteByEventId(@Param("eventId") Long eventId);
}
