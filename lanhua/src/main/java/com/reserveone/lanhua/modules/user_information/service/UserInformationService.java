package com.reserveone.lanhua.modules.user_information.service;

import com.reserveone.lanhua.modules.user_information.dto.UserInformationDTO;
import com.reserveone.lanhua.modules.user_information.entity.UserInformation;
import com.reserveone.lanhua.modules.user_information.repository.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInformationService {

    @Autowired
    private UserInformationRepository repository;

    public UserInformationDTO getByUserId(Integer idUser) {
        Optional<UserInformation> entityOpt = repository.findByIdUser(idUser);
        if (entityOpt.isPresent()) {
            return mapToDTO(entityOpt.get());
        }
        return null;
    }

    public UserInformationDTO saveOrUpdate(UserInformationDTO dto) {
        UserInformation entity = repository.findByIdUser(dto.getIdUser())
                .orElse(new UserInformation());

        entity.setIdUser(dto.getIdUser());
        entity.setNumberDni(dto.getNumberDni());
        entity.setAddress(dto.getAddress());
        entity.setUserPhone(dto.getUserPhone());
        entity.setContactName(dto.getContactName());
        entity.setKinship(dto.getKinship());
        entity.setContactPhone(dto.getContactPhone());
        entity.setEps(dto.getEps());
        entity.setRh(dto.getRh());
        entity.setMedicConditions(dto.getMedicConditions());
        entity.setDocumentUrl(dto.getDocumentUrl());
        entity.setEpsUrl(dto.getEpsUrl());
        entity.setDateEps(dto.getDateEps());

        UserInformation saved = repository.save(entity);
        return mapToDTO(saved);
    }

    private UserInformationDTO mapToDTO(UserInformation entity) {
        UserInformationDTO dto = new UserInformationDTO();
        dto.setIdUserInformation(entity.getIdUserInformation());
        dto.setIdUser(entity.getIdUser());
        dto.setNumberDni(entity.getNumberDni());
        dto.setAddress(entity.getAddress());
        dto.setUserPhone(entity.getUserPhone());
        dto.setContactName(entity.getContactName());
        dto.setKinship(entity.getKinship());
        dto.setContactPhone(entity.getContactPhone());
        dto.setEps(entity.getEps());
        dto.setRh(entity.getRh());
        dto.setMedicConditions(entity.getMedicConditions());
        dto.setDocumentUrl(entity.getDocumentUrl());
        dto.setEpsUrl(entity.getEpsUrl());
        dto.setDateEps(entity.getDateEps());
        return dto;
    }
}