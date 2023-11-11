package org.example.controoler;

import lombok.extern.slf4j.Slf4j;
import org.example.Product;
import org.example.service.ProductService;
import org.example.service.dto.ReqProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询商品列表
     *
     * @return
     */
    @GetMapping("/list")
    public List<Product> selectProductList() {
        log.info("接收到请求");
        return productService.selectProductList();
    }

    @PostMapping("/id")
    public List<Product> selectProductList(@RequestBody ReqProduct reqProduct) {
        log.info("接收到请求");
        return productService.selectProductList(reqProduct);
    }

}
