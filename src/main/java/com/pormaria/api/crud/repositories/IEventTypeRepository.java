package com.pormaria.api.crud.repositories;

import com.pormaria.api.crud.models.EventTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventTypeRepository extends JpaRepository<EventTypeModel, Long> {
    
}
