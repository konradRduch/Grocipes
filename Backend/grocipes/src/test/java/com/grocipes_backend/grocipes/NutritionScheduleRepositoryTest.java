package com.grocipes_backend.grocipes;
import static org.junit.jupiter.api.Assertions.*;

import com.grocipes_backend.grocipes.models.NutritionSchedule;
import com.grocipes_backend.grocipes.models.UserEntity;
import com.grocipes_backend.grocipes.repositories.NutritionScheduleRepository;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
public class NutritionScheduleRepositoryTest {

    @Autowired
    private NutritionScheduleRepository nutritionScheduleRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    private UserEntity testUser;
    private NutritionSchedule schedule1;
    private NutritionSchedule schedule2;

    @BeforeEach
    void setUp() {
        // Tworzymy użytkownika testowego
        testUser = new UserEntity();
        testUser.setEmail("testuser@example.com");
        testUser.setName("Test");
        testUser.setSurname("User");
        userEntityRepository.save(testUser);

        // Tworzymy dwa harmonogramy żywieniowe dla użytkownika
        schedule1 = new NutritionSchedule();
        schedule1.setUserEntity(testUser);
        schedule1.setName("Dieta na masę");
        nutritionScheduleRepository.save(schedule1);

        schedule2 = new NutritionSchedule();
        schedule2.setUserEntity(testUser);
        schedule2.setName("Dieta ketogeniczna");
        nutritionScheduleRepository.save(schedule2);
    }

    @Test
    void testFindByUserId() {
        List<NutritionSchedule> schedules = nutritionScheduleRepository.findByUserId(testUser.getId());

        assertNotNull(schedules);
        assertEquals(2, schedules.size());

        assertTrue(schedules.stream().anyMatch(s -> s.getName().equals("Dieta na masę")));
        assertTrue(schedules.stream().anyMatch(s -> s.getName().equals("Dieta ketogeniczna")));
    }

}
