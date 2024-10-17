package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.DTO.AuthResponseDTO;
import com.grocipes_backend.grocipes.models.DTO.LoginDTO;
import com.grocipes_backend.grocipes.models.DTO.RegisterDTO;
import com.grocipes_backend.grocipes.models.NutritionSchedule;
import com.grocipes_backend.grocipes.models.Role;
import com.grocipes_backend.grocipes.models.ShoppingSchedule;
import com.grocipes_backend.grocipes.models.UserEntity;
import com.grocipes_backend.grocipes.repositories.NutritionScheduleRepository;
import com.grocipes_backend.grocipes.repositories.RoleRepository;
import com.grocipes_backend.grocipes.repositories.ShoppingScheduleRepository;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import com.grocipes_backend.grocipes.security.JWTGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserEntityRepository userEntityRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private ShoppingScheduleRepository shoppingScheduleRepository;
    private NutritionScheduleRepository nutritionScheduleRepository;
    private JWTGenerator jwtGenerator;

    public AuthController(AuthenticationManager authenticationManager, UserEntityRepository userEntityRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator, ShoppingScheduleRepository shoppingScheduleRepository, NutritionScheduleRepository nutritionScheduleRepository) {
        this.authenticationManager = authenticationManager;
        this.userEntityRepository = userEntityRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.shoppingScheduleRepository = shoppingScheduleRepository;
        this.nutritionScheduleRepository = nutritionScheduleRepository;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterDTO request){
        if(userEntityRepository.existsByEmail(request.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //create user from request
        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setBirthday(request.getBirthday());
        user.setGender(request.getGender());
        userEntityRepository.save(user);

        //creating default shopping schedule
        ShoppingSchedule shoppingSchedule = new ShoppingSchedule();
        shoppingSchedule.setName(request.getName() + " shopping schedule");
        shoppingSchedule.setUserEntity(user);
        shoppingScheduleRepository.save(shoppingSchedule);
        //creating default nutrition schedule
        NutritionSchedule nutritionSchedule = new NutritionSchedule();
        nutritionSchedule.setName(request.getName() + " nutrition schedule");
        nutritionSchedule.setUserEntity(user);
        nutritionScheduleRepository.save(nutritionSchedule);


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }



}
