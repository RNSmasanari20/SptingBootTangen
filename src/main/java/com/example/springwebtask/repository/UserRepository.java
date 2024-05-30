package com.example.springwebtask.repository;

import com.example.springwebtask.entity.UserFind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<UserFind> findUser(String loginId, String password) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("loginId", loginId);
        param.addValue("password", password);
        var list = jdbcTemplate.query("SELECT login_id, password, name, role FROM users WHERE login_id = :loginId AND password = :password",
                param, new DataClassRowMapper<>(UserFind.class));
        return list.isEmpty() ? null : list;
    }


}
