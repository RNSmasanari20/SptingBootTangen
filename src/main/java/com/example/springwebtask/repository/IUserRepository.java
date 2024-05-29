package com.example.springwebtask.repository;

import com.example.springwebtask.entity.UserFind;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepository {
    List<UserFind> findUser(String loginId, String password) throws SQLException;


}
