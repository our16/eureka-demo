package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.Order;
import org.example.Product;
import org.example.service.OrderService;
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
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    @Override
    public Order selectOrderById(Integer id) {
        return new Order(id, "order-001", "中国", 31994D,
                selectProductListByDiscoveryClient());
    }

    private List<Product> selectProductListByDiscoveryClient() {
        // 获取服务列表
        List<String> serviceIds = discoveryClient.getServices();
        if (CollectionUtils.isEmpty(serviceIds)) {
            return null;
        }
        log.info("服务列表:[{}]", serviceIds);

        // 根据服务名称获取服务
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("service-provider");
        if (CollectionUtils.isEmpty(serviceInstances)) {
            log.info("根据名字获取服务没有找到");
            return null;
        }

        ServiceInstance si = serviceInstances.get(0);
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
