package com.grocipes_backend.grocipes;

import static org.junit.jupiter.api.Assertions.*;

import com.grocipes_backend.grocipes.models.BodyMeasurements;
import com.grocipes_backend.grocipes.models.DTO.BodyMeasurementsDTO;
import com.grocipes_backend.grocipes.models.DTO.ProfileInfoDTO;
import com.grocipes_backend.grocipes.models.UserEntity;
import com.grocipes_backend.grocipes.repositories.BodyMeasurementsRepository;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@Transactional
public class BodyMeasurementsRepositoryTest {
    @Autowired
    private BodyMeasurementsRepository bodyMeasurementsRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        // Tworzenie testowego użytkownika
        testUser = new UserEntity();
        testUser.setName("Jan");
        testUser.setSurname("Kowalski");
        testUser.setEmail("jan.kowalski@example.com");
        testUser.setBirthday(LocalDate.of(1990, 5, 15));
        testUser.setGender("MALE");
        userEntityRepository.save(testUser);

        // Tworzenie pomiarów ciała dla użytkownika
        BodyMeasurements measurement1 = new BodyMeasurements();
        measurement1.setWeight(80.5);
        measurement1.setHeight(180);
        measurement1.setAbdominal_circumference(90.0);
        measurement1.setBody_fat_leve(20.0);
        measurement1.setPhysical_activity("Moderate");
        measurement1.setMeasurement_date(LocalDateTime.of(2024, 1, 1,1,1,1));
        measurement1.setUserEntity(testUser);

        BodyMeasurements measurement2 = new BodyMeasurements();
        measurement2.setWeight(78.0);
        measurement2.setHeight(180);
        measurement2.setAbdominal_circumference(88.0);
        measurement2.setBody_fat_leve(18.5);
        measurement2.setPhysical_activity("Active");
        measurement2.setMeasurement_date(LocalDateTime.of(2024, 2, 1,1,1));
        measurement2.setUserEntity(testUser);

        bodyMeasurementsRepository.save(measurement1);
        bodyMeasurementsRepository.save(measurement2);
    }

    @Test
    void testFindUserBodyMeasurements() {
        List<BodyMeasurementsDTO> measurements = bodyMeasurementsRepository.findUserBodyMeasurements(testUser.getId());

        assertNotNull(measurements);
        assertEquals(2, measurements.size());
        assertEquals(80.5, measurements.get(0).getWeight());
        assertEquals(78.0, measurements.get(1).getWeight());
    }

    @Test
    void testGetUserProfileInfo() {
        List<ProfileInfoDTO> profileInfoList = bodyMeasurementsRepository.getUserProfileInfo(testUser.getId(), PageRequest.of(0, 1));

        assertNotNull(profileInfoList);
        assertFalse(profileInfoList.isEmpty());
        ProfileInfoDTO profileInfo = profileInfoList.get(0);

        assertEquals("Jan", profileInfo.getName());
        assertEquals("Kowalski", profileInfo.getSurname());
        assertEquals("jan.kowalski@example.com", profileInfo.getEmail());
        assertEquals(78.0, profileInfo.getWeight());
        assertEquals(88.0, profileInfo.getAbdominal_circumference());
    }

}
