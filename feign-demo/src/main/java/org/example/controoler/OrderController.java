package org.example.controoler;

import org.example.Order;
import org.example.service.dto.ReqProduct;
import org.example.service.impl.DiscoveryClientOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private DiscoveryClientOrderServiceImpl discoveryClientOrderService;

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    @GetMapping("/feign/{id}")
    public Order selectOrderById(@PathVariable("id") Integer id) {
        return discoveryClientOrderService.selectOrderList();
    }

    @PostMapping("/feign")
    public Order selectOrderById(@RequestBody ReqProduct reqProduct) {
        return discoveryClientOrderService.selectOrderById(reqProduct);
    }
}
