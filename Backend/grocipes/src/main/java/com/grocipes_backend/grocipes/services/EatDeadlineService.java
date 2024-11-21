package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.DTO.CreateEatDeadlineDTO;
import com.grocipes_backend.grocipes.models.DTO.EatDeadlineDTO;
import com.grocipes_backend.grocipes.models.EatDeadline;
import com.grocipes_backend.grocipes.models.NutritionSchedule;
import com.grocipes_backend.grocipes.models.Recipe;
import com.grocipes_backend.grocipes.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EatDeadlineService {

    private final EatDeadlineRepository eatDeadlineRepository;
    private final RecipeRepository recipeRepository;
    private final NutritionScheduleRepository nutritionScheduleRepository;
    private final ImageRepository imageRepository;
    private final ShoppingListRepository shoppingListRepository;

    public EatDeadlineService(EatDeadlineRepository eatDeadlineRepository, RecipeRepository recipeRepository, NutritionScheduleRepository nutritionScheduleRepository,
                              ImageRepository imageRepository,
                              ShoppingListRepository shoppingListRepository) {
        this.eatDeadlineRepository = eatDeadlineRepository;
        this.recipeRepository = recipeRepository;
        this.nutritionScheduleRepository = nutritionScheduleRepository;
        this.imageRepository = imageRepository;
        this.shoppingListRepository = shoppingListRepository;
    }

    public void addEatDeadline(CreateEatDeadlineDTO createEatDeadlineDTO) {
        //findRecipe
        Recipe recipe = recipeRepository.findRecipeById(createEatDeadlineDTO.getRecipeId());
        //findNutritionSchedule
        List<NutritionSchedule> nutritionSchedule = nutritionScheduleRepository.findByUserId(createEatDeadlineDTO.getUserId());

        EatDeadline eatDeadline = new EatDeadline();
        eatDeadline.setRecipe(recipe);
        eatDeadline.setNutritionSchedule(nutritionSchedule.get(0));
        eatDeadline.setEating_date(createEatDeadlineDTO.getEatingDate());
        eatDeadline.setDone(createEatDeadlineDTO.isDone());
        eatDeadline.setRate(createEatDeadlineDTO.getRate());
        eatDeadline.setComment(createEatDeadlineDTO.getComment());
        eatDeadlineRepository.save(eatDeadline);
    }

    public List<EatDeadlineDTO> getAllEatDeadline(Integer userId) {
        return eatDeadlineRepository.findAllEatDeadline(userId);
    }

    public EatDeadlineDTO getEatDeadline(Integer userId, Integer id){
        return eatDeadlineRepository.findAllEatDeadline(userId).stream()
                .filter(eatDeadlineDTO -> eatDeadlineDTO.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void commentMeal(Integer id, CreateEatDeadlineDTO createEatDeadlineDTO) {
       EatDeadline eatDeadline =  eatDeadlineRepository.findById(id)
               .orElseThrow(() -> new NoSuchElementException("No EatDeadline found with ID: " + id));
       eatDeadline.setComment(createEatDeadlineDTO.getComment());
       eatDeadlineRepository.save(eatDeadline);

    }

    public void rateMeal(Integer id, CreateEatDeadlineDTO createEatDeadlineDTO) {
        EatDeadline eatDeadline =  eatDeadlineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No EatDeadline found with ID: " + id));
        eatDeadline.setRate(createEatDeadlineDTO.getRate());
        eatDeadlineRepository.save(eatDeadline);
    }

    @Transactional
    public void deleteEatDeadline(Integer id) {
        imageRepository.deleteImageByEatDeadlineId(id);
        eatDeadlineRepository.deleteById(id);
    }
}
