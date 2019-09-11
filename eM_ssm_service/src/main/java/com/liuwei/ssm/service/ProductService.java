package com.liuwei.ssm.service;

import com.liuwei.ssm.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll(int page,int pageSize) throws Exception;

    int findSum()throws Exception;

    void save(Product product);
}
