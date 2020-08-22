package com.example.smartdictionary.model;

public class ExceptionWordNotFound extends Exception {
    public ExceptionWordNotFound() {
        super("word not founded");
    }
}
