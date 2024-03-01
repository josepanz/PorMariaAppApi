package com.pormaria.api.crud.services;

import com.pormaria.api.crud.models.CivilStatusModel;
import com.pormaria.api.crud.repositories.ICivilStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CivilStatusService {
    @Autowired
    ICivilStatusRepository iCivilStatusRepository;

    public ArrayList<CivilStatusModel> getAllCivilStatus() {
        return (ArrayList<CivilStatusModel>) iCivilStatusRepository.findAll();
    }

    public CivilStatusModel saveCivilStatus(CivilStatusModel model) {
        return iCivilStatusRepository.save(model);
    }

    public Optional<CivilStatusModel> getCivilStatusById(Long id) {
        return iCivilStatusRepository.findById(id);
    }

    public Optional<CivilStatusModel> updateCivilStatusById(CivilStatusModel request, Long id) {
        if (iCivilStatusRepository.findById(id).isPresent()) {
            CivilStatusModel model = iCivilStatusRepository.findById(id).get();
            model.setCode(request.getCode());
            model.setDescription(request.getDescription());
            model.setCreationDate(request.getCreationDate());
            return Optional.of(model);
        }
        return Optional.empty();
    }

    public boolean deleteCivilStatusById(Long id) {
        try {
            iCivilStatusRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
