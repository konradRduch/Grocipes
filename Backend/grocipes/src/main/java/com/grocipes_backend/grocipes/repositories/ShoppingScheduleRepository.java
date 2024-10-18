package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.ShoppingSchedule;
import com.grocipes_backend.grocipes.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingScheduleRepository extends JpaRepository<ShoppingSchedule, Integer> {
    ShoppingSchedule findShoppingScheduleByUserEntity(UserEntity user);

    @Query(value = "SELECT * FROM shopping_schedule WHERE user_id = :user_id", nativeQuery = true)
    List<ShoppingSchedule> findByUserId(@Param("user_id") Integer userId);
}
