package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * ClassName: DishController
 * Package: com.sky.controller.admin
 * Description:
 * Dish Management
 * @Author Jingran Liu
 * @Create 2025/1/14 23:48
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "Dish interfaces")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    @ApiOperation("Add new dish")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("Add new dish: {}", dishDTO);
        dishService.saveWithFlavor(dishDTO);

        //remove cache data
        String key = "dish_" + dishDTO.getCategoryId();
        cleanCache(key);

        return Result.success();
    }

    /**
     * Dish page query
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Dish page query")

    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("Dish page query: {}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * delete dishes
     * @param ids
     * @return
     */

    @DeleteMapping
    @ApiOperation("Delete dishes")
    public Result delete(@RequestParam List<Long> ids){
        log.info("Start deleting dishes: {}", ids);
        dishService.deleteBatch(ids);

        // delete all dish cache data whose name starts with "dish_"
        cleanCache("dish_*");

        return Result.success();
    }

    /**
     * Get dish info by Id
     * @param id
     * @return
     */

    @GetMapping("/{id}")
    @ApiOperation("Get dish info by Id")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("Get dish info by Id: {}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * Modify dish info and flavor info
     * @param dishDTO
     * @return
     */

    @PutMapping
    @ApiOperation("Modify dish info")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("Modify dish: {}", dishDTO);
        dishService.updateWithFlavor(dishDTO);

        // delete all dish cache data whose name starts with "dish_"
        cleanCache("dish_*");

        return Result.success();
    }
    /**
     * 菜品起售停售
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("菜品起售停售")
    public Result<String> startOrStop(@PathVariable Integer status, Long id){
        dishService.startOrStop(status, id);

        // delete all dish cache data whose name starts with "dish_"
        cleanCache("dish_*");

        return Result.success();
    }
    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId){
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

    /**
     * clean cache data
     * @param pattern
     */

    private void cleanCache(String pattern){
        // delete all dish cache data whose name aligns with pattern
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

}
