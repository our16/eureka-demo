package org.example.service;

import org.example.Order;

public interface OrderService {
    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    Order selectOrderById(Integer id);
}
