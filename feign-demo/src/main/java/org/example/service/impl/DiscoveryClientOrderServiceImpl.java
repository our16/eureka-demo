package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.Order;
import org.example.Product;
import org.example.service.OrderService;
import org.example.service.ProductService;
import org.example.service.dto.ReqProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class DiscoveryClientOrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    /**
     * 根据主键查询订单
     *
     * @param
     * @return
     */
    @Override
    public Order selectOrderList() {
        return new Order(0, "feign-001", "中国", 31994D,
                selectProductListByDiscoveryClient());
    }

    @Override
    public Order selectOrderById(ReqProduct reqProduct) {
        List<Product> products = productService.selectProductList(reqProduct);
        return new Order(0, "post", "network", 31994D, products);
    }

    private List<Product> selectProductListByDiscoveryClient() {
        // 获取服务列表
        return productService.selectProductList();
    }

}
