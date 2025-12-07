package com.dlmk.cheflist_backend.exception;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(String message) { super(message); }
}
