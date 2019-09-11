package com.liuwei.ssm.dao;

import com.liuwei.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {

    @Select("select *from member where id=#{id}")
    Member findById(String id)throws Exception;

}
