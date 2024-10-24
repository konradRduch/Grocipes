package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.BodyMeasurements;
import com.grocipes_backend.grocipes.models.DTO.BodyMeasurementsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BodyMeasurementsRepository extends JpaRepository<BodyMeasurements, Integer> {
    BodyMeasurements findBodyMeasurementsById(Integer id);
    BodyMeasurements findBodyMeasurementsByUserEntityId(Integer userId);


    @Query("SELECT new com.grocipes_backend.grocipes.models.DTO.BodyMeasurementsDTO(bm.id, bm.weight, bm.height, bm.abdominal_circumference, bm.body_fat_leve, bm.physical_activity, bm.measurement_date,u.id) " +
            "FROM BodyMeasurements bm " +
            "JOIN UserEntity u ON u.id = bm.userEntity.id "+
            "WHERE u.id = :userId"
    )
    List<BodyMeasurementsDTO>findUserBodyMeasurements(@Param("userId") Integer userId);

}
