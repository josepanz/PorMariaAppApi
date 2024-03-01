package com.pormaria.api.crud.services;

import com.pormaria.api.crud.models.MembershipModel;
import com.pormaria.api.crud.repositories.IMembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MembershipService {
    @Autowired
    IMembershipRepository iMembershipRepository;

    public ArrayList<MembershipModel> getAllMemberships() {
        return (ArrayList<MembershipModel>) iMembershipRepository.findAll();
    }

    public MembershipModel saveMembership(MembershipModel model) {
        return iMembershipRepository.save(model);
    }

    public Optional<MembershipModel> getMembershipById(Long id) {
        return iMembershipRepository.findById(id);
    }

    public Optional<MembershipModel> updateMembershipById(MembershipModel request, Long id) {
        if (iMembershipRepository.findById(id).isPresent()) {
            MembershipModel model = iMembershipRepository.findById(id).get();
            model.setGroup(request.getGroup());
            model.setPosition(request.getPosition());
            model.setUser(request.getUser());
            model.setActive(request.isActive());
            model.setDisabledDate(request.getDisabledDate());
            model.setPrincipal(request.isPrincipal());
            return Optional.of(model);
        }
        return Optional.empty();
    }

    public boolean deleteMembershipById(Long id) {
        try {
            iMembershipRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
