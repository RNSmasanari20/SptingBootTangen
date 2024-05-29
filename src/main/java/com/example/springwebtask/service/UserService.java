package com.example.springwebtask.service;

import com.example.springwebtask.entity.ProductsFind;
import com.example.springwebtask.entity.UserFind;
import com.example.springwebtask.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserFind> findUser(String loginId, String password){
        try {
            return userRepository.findUser(loginId,password);
        }catch (SQLException e){
            return null;
        }
    }


}
