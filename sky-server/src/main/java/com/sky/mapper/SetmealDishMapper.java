package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SetMealDishMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author Jingran Liu
 * @Create 2025/1/15 21:23
 * @Version 1.0
 */
@Mapper
public interface SetmealDishMapper {
    /**
     * search setmeal ids based on dish ids
     * @param dishIds
     * @return
     */
    // select setmeal_id from setmeal_dish where dish_id in (1,2,3,4)
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);
}
