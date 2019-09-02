package com.saul.springboot.selfDemo.domain;

public class ItemMenuNotFoundException extends RuntimeException {

    public ItemMenuNotFoundException(Long id) {

        super("ItemMenu not found: " + id);
    }
}
