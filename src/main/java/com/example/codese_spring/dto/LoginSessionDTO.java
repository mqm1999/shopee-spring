package com.example.codese_spring.dto;

public class LoginSessionDTO {
    private String key;
    private String value;

    public LoginSessionDTO() {
    }

    public LoginSessionDTO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
