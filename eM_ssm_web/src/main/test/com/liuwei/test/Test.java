package com.liuwei.test;

import com.liuwei.ssm.domain.Product;
import com.liuwei.ssm.service.ProductService;
import com.liuwei.ssm.service.impl.ProductServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {

    @org.junit.Test
    public void testOracle() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductService productService = (ProductService) context.getBean("productService");
//        int sum = productService.findSum();
        List<Product> all = productService.findAll();
        System.out.println(all);
    }
}
