package com.example.springbootdemo.controller.User;

import com.example.springbootdemo.Services.user.IUserService;
import com.example.springbootdemo.dao.pojo.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(name = "userList")
    public String queryUserList(Model model){
        List<UserDO> userDOList = userService.queryAll();
        model.addAttribute("userList",userDOList);
        return "user";
    }

}
