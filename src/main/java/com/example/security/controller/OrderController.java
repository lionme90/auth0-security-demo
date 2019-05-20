package com.example.security.controller;


import com.example.security.TokenAuthentication;
import com.example.security.dto.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Controller
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping({"/"})
    public String home() {
        return "home";
    }

    @GetMapping({"/orders"})
    public String orders(final Map<String, Object> model, TokenAuthentication principal) {
        logger.info("Performing /orders. List authorized content");
        if (principal == null) {
            return "redirect:/login";
        }
        model.put("orders", getOrderList());
        model.put("jwt", principal.getCredentials());
        return "orders";
    }

    private List<Order> getOrderList() {
        return IntStream.range(1, 4)
                .mapToObj(i -> new Order(UUID.randomUUID().toString(), "product " + i, "customer " + i, "$ " + i * 10))
                .collect(toList());
    }


}