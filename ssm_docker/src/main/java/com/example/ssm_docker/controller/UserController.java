package com.example.ssm_docker.controller;

import com.example.ssm_docker.bean.User;
import com.example.ssm_docker.dao.UserDao;
import com.example.ssm_docker.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDao userDao;

    @ResponseBody
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id")Integer id) {
        User user = userMapper.getUser(id);
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public void addUser(@RequestParam("lastName") String lastName, @RequestParam("email") String email){
        userMapper.addUser(lastName, email);
    }

    @ResponseBody
    @RequestMapping(value = "/user")
    public List<User> findAllUser(){
        return userDao.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public void delUser(@RequestParam("lastName") String lastName){
        userDao.deleteByLastName(lastName);
    }
}
