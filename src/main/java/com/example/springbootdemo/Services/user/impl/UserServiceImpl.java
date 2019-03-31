package com.example.springbootdemo.Services.user.impl;

import com.example.springbootdemo.Services.user.IUserService;
import com.example.springbootdemo.dao.mapper.IUserMapper;
import com.example.springbootdemo.dao.pojo.UserDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserMapper userMapper;

    @Override
    public List<UserDO> queryAll() {
        return userMapper.allUserList();
    }
}
