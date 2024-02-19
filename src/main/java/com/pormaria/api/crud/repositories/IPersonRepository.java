package com.pormaria.api.crud.repositories;

import com.pormaria.api.crud.models.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<PersonModel, Long> {
}
