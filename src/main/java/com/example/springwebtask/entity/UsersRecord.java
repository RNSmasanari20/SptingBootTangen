package com.example.springwebtask.entity;

import java.sql.Timestamp;

public record UsersRecord(Integer id, String loginId, String password, String name,
                          Integer role, Timestamp createTs, Timestamp updateTs) {
}
