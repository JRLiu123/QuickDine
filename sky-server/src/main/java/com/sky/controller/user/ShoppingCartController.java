package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: ShoppintCartController
 * Package: com.sky.controller.user
 * Description:
 *
 * @Author Jingran Liu
 * @Create 2025/1/22 21:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/user/shoppingCart")
@Api(tags = "C end - shopping cart interface")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * add an item to the shopping cart
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("add an item to the shopping cart")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("Adding an item to the shopping cart: {}", shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    /**
     * take a look at the shopping cart
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("take a look at the shopping cart")
    public Result<List<ShoppingCart>> list(){
        log.info("Looking at the shopping cart...");
        List<ShoppingCart> list = shoppingCartService.showShoppingCart();
        return Result.success(list);
    }

    /**
     * clean the shopping cart
     * @return
     */
    @DeleteMapping("/clean")
    @ApiOperation("clean the shopping cart")
    public Result delete(){
        log.info("Looking at the shopping cart...");
        shoppingCartService.cleanShoppingCart();
        return Result.success();
    }
}
