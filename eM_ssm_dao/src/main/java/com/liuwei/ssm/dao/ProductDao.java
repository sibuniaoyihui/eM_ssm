package com.liuwei.ssm.dao;

import com.liuwei.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品信息持久层接口
 */
@Repository
public interface ProductDao {

    //查询所有的商品信息
    @Select("select *from product")
    List<Product> findAll() throws Exception;

    @Select("select count(*)from product")
    int findSum()throws Exception;

    /**
     * 保存用户
     * @param product
     */
    @Insert("insert into product(productnum, productname, cityname, departuretime, productprice,productdesc, productstatus)values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    /**
     * 根据id查询
     */
    @Select("select *from product where id=#{id}")
    Product findById(String id);

}
