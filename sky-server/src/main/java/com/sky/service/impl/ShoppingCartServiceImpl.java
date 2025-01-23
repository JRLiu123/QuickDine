package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: ShoppingCartServiceImpl
 * Package: com.sky.service.impl
 * Description:
 *
 * @Author Jingran Liu
 * @Create 2025/1/22 21:16
 * @Version 1.0
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;
    /**
     * add an item to the shopping cart
     */
    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {

        //check if there is a same item in the shopping cart
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);

        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);

        // if exists, update the number of items
        if(list != null && list.size() > 0){
            ShoppingCart cart = list.get(0);
            cart.setNumber(cart.getNumber()+1);

            shoppingCartMapper.updateNumberById(cart);
        }

        // otherwise, add new record into db
        else{
            // check if the new item belong to dish or setmeal
            Long dishId = shoppingCartDTO.getDishId();

            if(dishId != null){
                // add a dish into shopping cart
                Dish dish = dishMapper.getById(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());

            }
            else{
                Long setmealId = shoppingCartDTO.getSetmealId();

                // add a setmeal into shopping cart
                Setmeal setmeal = setmealMapper.getById(setmealId);
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setAmount(setmeal.getPrice());

            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());

            shoppingCartMapper.insert(shoppingCart);
        }

    }
    /**
     * take a look at the shopping cart
     * @return
     */
    @Override
    public List<ShoppingCart> showShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(userId)
                .build();

        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);

        return list;
    }
}
