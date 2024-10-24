package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.BodyMeasurements;
import com.grocipes_backend.grocipes.models.DTO.BodyMeasurementsDTO;
import com.grocipes_backend.grocipes.models.DTO.ProfileInfoDTO;
import com.grocipes_backend.grocipes.models.UserEntity;
import com.grocipes_backend.grocipes.repositories.BodyMeasurementsRepository;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

//@Transactional
//    public Page<MyModel> findMyModelTop5() {
//        return myModelRepository.findMyModelTop5(new PageRequest(0, 5));
//    }
    public ProfileInfoDTO getUserProfileInfo(Integer userId){
        return bodyMeasurementsRepository
                .getUserProfileInfo(userId, PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
    }

}
