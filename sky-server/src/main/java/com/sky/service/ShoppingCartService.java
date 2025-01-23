package com.sky.service;

import com.sky.dto.ShoppingCartDTO;

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
}
