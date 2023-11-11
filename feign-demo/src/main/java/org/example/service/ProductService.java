package org.example.service;

import org.example.Product;
import org.example.service.dto.ReqProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 商品服务
 */
// 声明需要调用的服务
@FeignClient("service-provider")
public interface ProductService {

    /**
     * 查询商品列表
     *
     * @return
     */
    // 配置需要调用的服务地址及参数
    @GetMapping("/product/list")
    List<Product> selectProductList();


    @PostMapping("/product/id")
    List<Product> selectProductList(ReqProduct reqProduct);

}
