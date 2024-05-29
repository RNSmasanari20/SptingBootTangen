package com.example.springwebtask.service;

import com.example.springwebtask.entity.UserFind;

import java.util.List;

public interface IUserService {

    List<UserFind> findUser(String loginId, String password);
}
