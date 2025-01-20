package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ShopController
 * Package: com.sky.controller.admin
 * Description:
 *
 * @Author Jingran Liu
 * @Create 2025/1/19 17:50
 * @Version 1.0
 */
@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "shop related interface")
@Slf4j
public class ShopController {
    public static final String KEY = "SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Set status of the restaurant
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("Set status of the restaurant")
    public Result setStatus(@PathVariable Integer status){
        log.info("Set status of the restaurant: {}", status == 1 ? "on sale." : "closes.");
        redisTemplate.opsForValue().set(KEY, status);
        return Result.success();
    }
    /**
     * Get status of the restaurant
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("get status of the restaurant")
    public Result<Integer> getStatus(){

        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("get status of the restaurant: {}", status == 1 ? "on sale." : "closes.");

        return Result.success(status);
    }
}
