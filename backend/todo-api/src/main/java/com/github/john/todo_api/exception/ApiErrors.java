package com.github.john.todo_api.exception;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
@Data
public class ApiErrors {
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String msg) {
    this.errors = Arrays.asList(msg);
    }
}
