package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: DishFlavorMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author Jingran Liu
 * @Create 2025/1/15 0:34
 * @Version 1.0
 */
@Mapper
public interface DishFlavorMapper {
    /**
     * insert flavor batch data
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * delete flavor data based on dish Id
     * @param dish_id
     */
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * Batch delete dish flavor data based on the collection of dish IDs.
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);
}
