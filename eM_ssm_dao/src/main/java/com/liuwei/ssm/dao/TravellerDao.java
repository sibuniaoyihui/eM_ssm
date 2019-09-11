package com.liuwei.ssm.dao;

import com.liuwei.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellerDao {

    @Select("select *from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    public List<Traveller>findById(String ordersId);
}
