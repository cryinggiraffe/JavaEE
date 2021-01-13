package com.example.ssm_docker.dao;

import com.example.ssm_docker.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {

    public User findByLastName(String lastName);

    public void deleteByLastName(String lastName);
}