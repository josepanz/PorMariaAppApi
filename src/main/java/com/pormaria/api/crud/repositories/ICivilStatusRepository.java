package com.pormaria.api.crud.repositories;

import com.pormaria.api.crud.models.CivilStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICivilStatusRepository extends JpaRepository<CivilStatusModel, Long> {
}
