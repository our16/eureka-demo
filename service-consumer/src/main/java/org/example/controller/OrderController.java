package org.example.controller;

import org.example.Order;
import org.example.service.impl.DiscoveryClientOrderServiceImpl;
import org.example.service.impl.LoadBalancerAnnoClientOrderServiceImpl;
import org.example.service.impl.LoadBalancerClientOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private DiscoveryClientOrderServiceImpl discoveryClientOrderService;


    @Autowired
    private LoadBalancerClientOrderServiceImpl loadBalancerClientOrderService;


    @Autowired
    private LoadBalancerAnnoClientOrderServiceImpl loadBalancerAnnoClientOrderService;

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    @GetMapping("/dis/{id}")
    public Order selectOrderById(@PathVariable("id") Integer id) {
        return discoveryClientOrderService.selectOrderById(id);
    }

    @GetMapping("/loadClient/{id}")
    public Order selectOrderById_loadClient(@PathVariable("id") Integer id) {
        return loadBalancerClientOrderService.selectOrderById(id);
    }

    @GetMapping("/loadAnno/{id}")
    public Order selectOrderById_loadAnno(@PathVariable("id") Integer id) {
        return loadBalancerAnnoClientOrderService.selectOrderById(id);
    }

}
