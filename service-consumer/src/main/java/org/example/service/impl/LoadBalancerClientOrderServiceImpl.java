package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.Order;
import org.example.Product;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class LoadBalancerClientOrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

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
        return new Order(id, "order-002", "中国", 31994D,
                selectProductListByDiscoveryClient());
    }

    private List<Product> selectProductListByDiscoveryClient() {
        // 根据服务名称获取服务
        ServiceInstance si = loadBalancerClient.choose("service-provider");
        if (Objects.isNull(si)) {
            log.info("根据名字获取服务没有找到");
            return null;
        }
        log.info("服务提供方信息:[{},{},{}]", si.getHost(), si.getPort(), si.getServiceId());
        StringBuffer reQueryUrl = new StringBuffer();
        reQueryUrl.append("http://" + si.getHost() + ":" + si.getPort() + "/product/list");

        // ResponseEntity: 封装了返回数据
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                reQueryUrl.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                });
        return response.getBody();
    }

}
