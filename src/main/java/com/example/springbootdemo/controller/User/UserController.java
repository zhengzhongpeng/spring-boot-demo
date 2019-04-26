package com.example.springbootdemo.controller.User;

import com.example.springbootdemo.Services.user.IUserService;
import com.example.springbootdemo.common.FreemarkerUtils;
import com.example.springbootdemo.common.PdfUtil;
import com.example.springbootdemo.dao.pojo.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "userList")
    public String queryUserList(Model model){
        List<UserDO> userDOList = userService.queryAll();
        model.addAttribute("userList",userDOList);
        return "user";
    }

    @RequestMapping(value = "downLoadUserList")
    public ResponseEntity<byte[]> downLoadUserListPDF(){
        List<UserDO> userDOList = userService.queryAll();
        Map<String,Object> model = new ConcurrentHashMap<>();
        model.put("userList",userDOList);
        String htmlStr = FreemarkerUtils.freeMarkerRender(model,"testPdfUserTableTemplate.ftl");
        byte[] pdfByte = PdfUtil.createPDF(htmlStr);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment","userList.pdf");
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(pdfByte,httpHeaders, HttpStatus.OK);
    }

}
