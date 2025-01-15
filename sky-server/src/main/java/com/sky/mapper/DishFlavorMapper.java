package com.sky.mapper;

import com.sky.entity.DishFlavor;
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


}
