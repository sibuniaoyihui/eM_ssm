package com.liuwei.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.liuwei.ssm.dao.OrdersDao;
import com.liuwei.ssm.domain.Orders;
import com.liuwei.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ordersService")
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page, int pageSize) throws Exception {

        /**
         * 参数pageNum是开始页码值，pageSize是每页显示条数
         */
        PageHelper.startPage(page,pageSize);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return ordersDao.findById(id);
    }

}
