package com.dev.cinema.controller;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.mappers.OrderMapper;
import com.dev.cinema.model.dto.request.RequestOrderDto;
import com.dev.cinema.model.dto.responce.ResponseOrderDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private ShoppingCartService shoppingCartService;
    private UserService userService;
    private OrderMapper orderMapper;

    public OrderController(OrderService orderService,
                           ShoppingCartService shoppingCartService,
                           UserService userService,
                           OrderMapper orderMapper) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(
            @RequestBody @Valid RequestOrderDto requestOrderDto) {
        ShoppingCart shoppingCart = shoppingCartService
                .getByUserId(requestOrderDto.getUserId());
        User user = userService.getById(requestOrderDto.getUserId());
        orderService.completeOrder(shoppingCart.getTickets(), user);
    }

    @GetMapping
    public List<ResponseOrderDto> getOrdersHistoryForUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        return orderService.getOrderHistory(userService.findByEmail(email)).stream()
                .map(orderMapper::mapToResponseOrderDto)
                .collect(Collectors.toList());
    }
}
