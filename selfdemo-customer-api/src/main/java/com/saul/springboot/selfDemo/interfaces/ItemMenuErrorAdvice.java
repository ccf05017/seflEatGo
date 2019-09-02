package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.domain.ItemMenuNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ItemMenuErrorAdvice {

    @ResponseBody
    // 여기서 200번대 return (예를 들어 204 no_content 같은 거) 주면, error 로 인식을 안하는 건지 아무튼 return 값이 안 나간다.
    // 근데 테스트 코드에서는 돌아..... 이게 뭐야....
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemMenuNotFoundException.class)
    public String handleNotFound() {

        return "No ItemMenu found in this restaurant";
    }

}
