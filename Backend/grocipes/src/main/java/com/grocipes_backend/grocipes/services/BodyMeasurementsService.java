package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.BodyMeasurements;
import com.grocipes_backend.grocipes.models.DTO.BodyMeasurementsDTO;
import com.grocipes_backend.grocipes.models.UserEntity;
import com.grocipes_backend.grocipes.repositories.BodyMeasurementsRepository;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyMeasurementsService {
    private final BodyMeasurementsRepository bodyMeasurementsRepository;
    private final UserEntityRepository userEntityRepository;

    public BodyMeasurementsService(BodyMeasurementsRepository bodyMeasurementsRepository,
                                   UserEntityRepository userEntityRepository) {
        this.bodyMeasurementsRepository = bodyMeasurementsRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public void save(BodyMeasurementsDTO bodyMeasurementsDTO){
        UserEntity user = userEntityRepository.findById(bodyMeasurementsDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found."));
        bodyMeasurementsRepository.save(new BodyMeasurements(
                bodyMeasurementsDTO.getId(),
                bodyMeasurementsDTO.getWeight(),
                bodyMeasurementsDTO.getHeight(),
                bodyMeasurementsDTO.getAbdominal_circumference(),
                bodyMeasurementsDTO.getBody_fat_leve(),
                bodyMeasurementsDTO.getPhysical_activity(),
                bodyMeasurementsDTO.getMeasurement_date(),
                user
        ));
    }



    public List<BodyMeasurementsDTO> getBodyMeasurements(Integer userId){
        return bodyMeasurementsRepository.findUserBodyMeasurements(userId);
    }

}
