package com.example.jwtTutorial.dto;


import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {

    @NotNull
    @Size(max = 50, min = 3)
    private String username;

    @NotNull
    @Size(max = 100, min = 3)
    private String password;

}
