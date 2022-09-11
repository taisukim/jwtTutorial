package com.example.jwtTutorial.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    private String token;
}
