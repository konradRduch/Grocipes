package com.grocipes_backend.grocipes;
import static org.junit.jupiter.api.Assertions.*;

import com.grocipes_backend.grocipes.models.ShoppingSchedule;
import com.grocipes_backend.grocipes.models.UserEntity;
import com.grocipes_backend.grocipes.repositories.ShoppingScheduleRepository;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class ShoppingScheduleRepositoryTest {

    @Autowired
    private ShoppingScheduleRepository shoppingScheduleRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    private UserEntity testUser;
    private ShoppingSchedule testSchedule;

    @BeforeEach
    void setUp() {
        // Tworzymy użytkownika
        testUser = new UserEntity();
        testUser.setEmail("nativequery@example.com");
        testUser.setPassword("password123");
        testUser.setName("NativeUser");
        userEntityRepository.save(testUser);

        // Tworzymy harmonogram zakupów dla użytkownika
        testSchedule = new ShoppingSchedule();
        testSchedule.setUserEntity(testUser);
        shoppingScheduleRepository.save(testSchedule);
    }

    @Test
    void testFindByUserId_NativeQuery() {
        List<ShoppingSchedule> foundSchedules = shoppingScheduleRepository.findByUserId(testUser.getId());

        assertNotNull(foundSchedules);
        assertFalse(foundSchedules.isEmpty());
        assertEquals(testUser.getId(), foundSchedules.get(0).getUserEntity().getId());
    }

}
