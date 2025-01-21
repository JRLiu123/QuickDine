package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * Dish page query
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);
    /**
     * delete dishes
     * @param ids
     * @return
     */
    void deleteBatch(List<Long> ids);

    /**
     * Get dish info by Id
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * Modify dish info and flavor info
     * @param dishDTO
     * @return
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * search meals and tastes in certain conditions
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
