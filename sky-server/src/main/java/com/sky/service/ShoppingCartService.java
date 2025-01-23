package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

/**
 * ClassName: ShoppingCartService
 * Package: com.sky.service
 * Description:
 *
 * @Author Jingran Liu
 * @Create 2025/1/22 21:16
 * @Version 1.0
 */
public interface ShoppingCartService {
    /**
     * add an item to the shopping cart
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * take a look at the shopping cart
     * @return
     */
    List<ShoppingCart> showShoppingCart();
    /**
     * clean the shopping cart
     * @return
     */
    void cleanShoppingCart();
}
