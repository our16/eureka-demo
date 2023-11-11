package org.example.service;

import org.example.Order;
import org.example.service.dto.ReqProduct;

public interface OrderService {
    /**
     * 查询产品列表
     *
     * @return
     */
    Order selectOrderList();

    /**
     * 根据ID查询列表
     * @param reqProduct
     * @return
     */
    Order selectOrderById(ReqProduct reqProduct);
}
