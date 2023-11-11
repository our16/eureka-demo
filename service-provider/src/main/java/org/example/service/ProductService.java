package org.example.service;

import org.example.Product;
import org.example.service.dto.ReqProduct;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 商品服务
 */
public interface ProductService {

    /**
     * 查询商品列表
     *
     * @return
     */
    List<Product> selectProductList();

    List<Product> selectProductList(ReqProduct reqProduct);
}
