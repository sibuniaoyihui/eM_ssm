package com.liuwei.ssm.dao;

import com.liuwei.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SysLogDao {

    @Insert("insert into sysLog(visitTime,username,ip,url,executionTime,method)values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog);

    @Select("select *from sysLog")
    public List<SysLog> findAll();
}
