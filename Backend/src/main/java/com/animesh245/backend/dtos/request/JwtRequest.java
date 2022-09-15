package com.animesh245.backend.dtos.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtRequest
{
    String username;
    String password;
}
