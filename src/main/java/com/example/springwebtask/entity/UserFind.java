package com.example.springwebtask.entity;

import com.example.springwebtask.service.UserService;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data

public class UserFind {

    @NotEmpty(message = "IDは必須です")
    private String loginId;
    @NotEmpty(message = "PASSは必須です")
    private String password;
    private String name;

}
