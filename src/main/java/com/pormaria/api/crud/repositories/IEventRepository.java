package com.pormaria.api.crud.repositories;

import com.pormaria.api.crud.models.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepository extends JpaRepository<EventModel, Long> {
}
