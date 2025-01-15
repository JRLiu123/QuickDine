package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    @ApiOperation("Add new dish")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("Add new dish: {}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }
}
