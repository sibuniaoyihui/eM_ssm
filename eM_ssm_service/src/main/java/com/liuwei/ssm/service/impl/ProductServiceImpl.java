package com.liuwei.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.liuwei.ssm.dao.ProductDao;
import com.liuwei.ssm.domain.Product;
import com.liuwei.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> findAll(int page,int pageSize) throws Exception {

        PageHelper.startPage(page,pageSize);//设置分页
        return productDao.findAll();
    }

    @Override
    public int findSum() throws Exception {
        return productDao.findSum();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
