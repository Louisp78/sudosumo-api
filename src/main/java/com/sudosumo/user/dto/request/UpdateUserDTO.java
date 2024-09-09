package com.sudosumo.user.dto.request;

import lombok.Data;

@Data
public class UpdateUserDTO {
    private String name;
    private String username;
    private String bio;
}
