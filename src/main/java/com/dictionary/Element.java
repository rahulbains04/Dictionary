package com.dictionary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Element {
    String origin;

    Meaning[] meanings;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Meaning[] getMeanings() {
        return meanings;
    }

    public void setMeanings(Meaning[] meanings) {
        this.meanings = meanings;
    }
}
