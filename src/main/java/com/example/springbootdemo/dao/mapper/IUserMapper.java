package com.example.springbootdemo.dao.mapper;

import com.example.springbootdemo.dao.pojo.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface IUserMapper {

    List<UserDO> allUserList();

}
