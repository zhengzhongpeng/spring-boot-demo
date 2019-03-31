package com.example.springbootdemo.Services.user;

import com.example.springbootdemo.dao.pojo.UserDO;

import java.util.List;

public interface IUserService {

    List<UserDO> queryAll();
}
