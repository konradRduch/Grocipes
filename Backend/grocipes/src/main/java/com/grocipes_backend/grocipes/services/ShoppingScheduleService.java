package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.ShoppingSchedule;
import com.grocipes_backend.grocipes.models.UserEntity;
import com.grocipes_backend.grocipes.repositories.ShoppingScheduleRepository;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingScheduleService {

    private final ShoppingScheduleRepository shoppingScheduleRepository;
    private  UserEntityRepository userEntityRepository;

    public ShoppingScheduleService(ShoppingScheduleRepository shoppingScheduleRepository, UserEntityRepository userEntityRepository) {
        this.shoppingScheduleRepository = shoppingScheduleRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public ShoppingSchedule findShoppingScheduleByUserId(Integer userId){
        UserEntity user = userEntityRepository.findById(userId).orElse(new UserEntity());
        return shoppingScheduleRepository.findShoppingScheduleByUserEntity(user);
    }
    public void save(ShoppingSchedule shoppingSchedule){
        shoppingScheduleRepository.save(shoppingSchedule);
    }

}
