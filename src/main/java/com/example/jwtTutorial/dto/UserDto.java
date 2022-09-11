package com.example.jwtTutorial.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull
    @Size(max = 50, min = 3)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(max = 100, min = 3)
    private String password;

    @NotNull
    @Size(max = 50, min = 3)
    private String nickname;
}
