package com.liuwei.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuwei.ssm.domain.Orders;
import com.liuwei.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

//    /**
//     * 查询全部订单，未分页的
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception {
//        ModelAndView mv = new ModelAndView();
//        List<Orders> all = ordersService.findAll();
//        mv.addObject("ordersList",all);
//        mv.setViewName("orders-list");  //设置跳转页面
//        return mv;
//    }

    /**
     * 查询全部订单，分页查询
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")int page,@RequestParam(name = "pageSize",required = true,defaultValue = "5")int pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> all = ordersService.findAll(page,pageSize);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(all);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");  //设置跳转页面
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }

}
