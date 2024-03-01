package com.pormaria.api.crud.repositories;

import com.pormaria.api.crud.models.MembershipModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMembershipRepository extends JpaRepository<MembershipModel, Long> {
}
