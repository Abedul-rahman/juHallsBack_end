package com.swproj.SWProject.config.dto;

import lombok.Data;

@Data
public class CreateUserReqDTO {
    private String username;
    private String password;
    private String fullName;
}
