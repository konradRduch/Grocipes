package com.grocipes_backend.grocipes;


import static org.junit.jupiter.api.Assertions.*;

import com.grocipes_backend.grocipes.models.DTO.EatDeadlineDTO;
import com.grocipes_backend.grocipes.models.EatDeadline;
import com.grocipes_backend.grocipes.models.NutritionSchedule;
import com.grocipes_backend.grocipes.models.Recipe;
import com.grocipes_backend.grocipes.models.UserEntity;
import com.grocipes_backend.grocipes.repositories.EatDeadlineRepository;
import com.grocipes_backend.grocipes.repositories.NutritionScheduleRepository;
import com.grocipes_backend.grocipes.repositories.RecipeRepository;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@Transactional
public class EatdeadlineRepositoryTest {

    @Autowired
    private EatDeadlineRepository eatDeadlineRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private NutritionScheduleRepository nutritionScheduleRepository;

    @BeforeEach
    void setUp() {

        UserEntity user = new UserEntity();
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        UserEntity savedUser = userEntityRepository.save(user);


        Recipe recipe = new Recipe();
        recipe.setTitle("Spaghetti Bolognese");
        recipe.setTypeOfMeal("Dinner");
        Recipe savedRecipe = recipeRepository.save(recipe);


        NutritionSchedule schedule = new NutritionSchedule();
        schedule.setUserEntity(savedUser);
        NutritionSchedule savedSchedule = nutritionScheduleRepository.save(schedule);


        EatDeadline eatDeadline = new EatDeadline();
        eatDeadline.setRecipe(savedRecipe);
        eatDeadline.setNutritionSchedule(savedSchedule);
        eatDeadline.setEating_date(LocalDateTime.now());
        eatDeadline.setDone(false);
        eatDeadline.setRate(4);
        eatDeadline.setComment("Very tasty!");
        eatDeadline.setTotalPrice(50.0);
        eatDeadlineRepository.save(eatDeadline);
    }

    @Test
    void findAllEatDeadlinesByUserIdTest() {
        Integer userId = userEntityRepository.findAll().get(0).getId();
        List<EatDeadlineDTO> deadlines = eatDeadlineRepository.findAllEatDeadline(userId);
        assertNotNull(deadlines, "The deadline list should not be null");
        assertFalse(deadlines.isEmpty(), "The deadline list should not be empty");
        EatDeadlineDTO deadlineDTO = deadlines.get(0);
        assertNotNull(deadlineDTO.getRecipeId(), "Recipe ID should not be null");
        assertEquals("Cheese toast", deadlineDTO.getTitle(), "The title should be 'Cheese toast'");
        assertEquals("Breakfast", deadlineDTO.getTypeOfMeal(), "Meal type should be 'Breakfast'");
        assertFalse(deadlineDTO.isDone(), "Status 'done' should be false");
        assertEquals(10, deadlineDTO.getRate(), "The rating should be 10");
        assertEquals("Delicious toast!!", deadlineDTO.getComment(), "The comment should be 'Delicious toast!!'");
    }

}
