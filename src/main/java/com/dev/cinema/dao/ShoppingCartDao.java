package com.dev.cinema.dao;

import com.dev.cinema.model.ShoppingCart;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userId);

    void update(ShoppingCart shoppingCart);
}
