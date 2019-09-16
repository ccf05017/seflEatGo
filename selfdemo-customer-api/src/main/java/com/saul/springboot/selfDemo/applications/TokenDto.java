package com.saul.springboot.selfDemo.applications;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {

    private String accessToken;

}
