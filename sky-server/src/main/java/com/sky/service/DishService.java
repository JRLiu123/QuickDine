package com.sky.service;

import com.sky.dto.DishDTO;
import org.springframework.stereotype.Service;

/**
 * ClassName: DishService
 * Package: com.sky.service
 * Description:
 *
 * @Author Jingran Liu
 * @Create 2025/1/14 23:53
 * @Version 1.0
 */

public interface DishService {
    /**
     * Add new dish adn flavor
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);
}
