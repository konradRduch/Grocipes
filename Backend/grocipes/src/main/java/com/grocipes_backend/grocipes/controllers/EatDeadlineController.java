package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.DTO.CreateEatDeadlineDTO;
import com.grocipes_backend.grocipes.models.DTO.EatDeadlineDTO;
import com.grocipes_backend.grocipes.models.DTO.ProductShoppingListCreationDTO;
import com.grocipes_backend.grocipes.models.ShoppingList;
import com.grocipes_backend.grocipes.models.ShoppingSchedule;
import com.grocipes_backend.grocipes.security.JWTGenerator;
import com.grocipes_backend.grocipes.services.EatDeadlineService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eatDeadline")
public class EatDeadlineController {
    private final EatDeadlineService eatDeadlineService;
    private JWTGenerator jwtGenerator;

    public EatDeadlineController(EatDeadlineService eatDeadlineService, JWTGenerator jwtGenerator) {
        this.eatDeadlineService = eatDeadlineService;
        this.jwtGenerator = jwtGenerator;
    }
    @GetMapping("/getAllEatDeadline")
    public List<EatDeadlineDTO>getAllEatDeadline(HttpServletRequest request){
        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            return eatDeadlineService.getAllEatDeadline(userId);
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }
    @GetMapping("/{id}")
    public EatDeadlineDTO getEatDeadline(@PathVariable Integer id,HttpServletRequest request){
        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            return eatDeadlineService.getEatDeadline(userId,id);
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Void>addEatDeadline(@RequestBody CreateEatDeadlineDTO createEatDeadlineDTO, HttpServletRequest request){
        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            createEatDeadlineDTO.setUserId(userId);
            eatDeadlineService.addEatDeadline(createEatDeadlineDTO);
            return ResponseEntity.ok().build();
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<Void>commentMeal(@PathVariable Integer id, @RequestBody CreateEatDeadlineDTO createEatDeadlineDTO, HttpServletRequest request){
        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            createEatDeadlineDTO.setUserId(userId);
            eatDeadlineService.commentMeal(id,createEatDeadlineDTO);
            return ResponseEntity.ok().build();
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }

    @PutMapping("/rate/{id}")
     public ResponseEntity<Void>rateMeal(@PathVariable Integer id, @RequestBody CreateEatDeadlineDTO createEatDeadlineDTO, HttpServletRequest request){
        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            createEatDeadlineDTO.setUserId(userId);
            eatDeadlineService.rateMeal(id, createEatDeadlineDTO);
            return ResponseEntity.ok().build();
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }


}
