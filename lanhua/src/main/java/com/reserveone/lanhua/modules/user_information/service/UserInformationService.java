package com.reserveone.lanhua.modules.user_information.service;

import com.reserveone.lanhua.modules.user_information.dto.UserInformationDTO;
import com.reserveone.lanhua.modules.user_information.entity.UserInformation;
import com.reserveone.lanhua.modules.user_information.repository.UserInformationRepository;
import com.reserveone.lanhua.modules.user.repository.UserRepository;
import com.reserveone.lanhua.modules.user.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserInformationService {

    @Autowired
    private UserInformationRepository repository;

    @Autowired
    private UserRepository userRepository;

    public UserInformationDTO getByUserId(Integer idUser) {
        // Actualizamos el nombre del método y convertimos a Long
        Optional<UserInformation> entityOpt = repository.findByUser_IdUser(Long.valueOf(idUser));
        if (entityOpt.isPresent()) {
            return mapToDTO(entityOpt.get());
        }
        return null;
    }

    public UserInformationDTO saveOrUpdate(UserInformationDTO dto) {
        User user = userRepository.findById(Long.valueOf(dto.getIdUser()))
                .orElseThrow(() -> new RuntimeException("El usuario con ID " + dto.getIdUser() + " no existe."));

        // Actualizamos el nombre del método y convertimos a Long
        UserInformation entity = repository.findByUser_IdUser(Long.valueOf(dto.getIdUser()))
                .orElse(new UserInformation());

        entity.setUser(user);

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

        if (entity.getUser() != null) {
            // SOLUCIÓN AL ERROR 2: Usamos el getter de Lombok 'getIdUser()' y convertimos a Integer
            dto.setIdUser(entity.getUser().getIdUser().intValue());
        }

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