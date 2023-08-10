package com.dictionary;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class myModel {

    @NotNull(message = "Cannot be empty")
    @Pattern(regexp = "^[A-Za-z]*",message="Only alphabets are allowed")
    String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
