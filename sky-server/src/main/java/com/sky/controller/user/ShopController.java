package com.sky.controller.user;

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
@RestController("userShopController")
@RequestMapping("/user/shop")
@Api(tags = "shop related interface")
@Slf4j
public class ShopController {
    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

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
