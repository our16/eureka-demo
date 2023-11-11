package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.Order;
import org.example.Product;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class LoadBalancerAnnoClientOrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplateLoadBalancerAnno;

    @Autowired
    private LoadBalancerClient loadBalancerClient; // Ribbon 负载均衡器

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    @Override
    public Order selectOrderById(Integer id) {
        return new Order(id, "order-003", "中国", 31994D,
                selectProductListByDiscoveryClient());
    }

    private List<Product> selectProductListByDiscoveryClient() {
        String serviceProvider = "service-provider";
        // ResponseEntity: 封装了返回数据
        ResponseEntity<List<Product>> response = restTemplateLoadBalancerAnno.exchange(
                "http://"+serviceProvider+"/product/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {});
        return response.getBody();
    }

}
