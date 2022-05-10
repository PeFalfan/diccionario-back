package com.diccionario.diccionario.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class TermModel implements Serializable {
    private static final long serialVersionUID = 967910914394660507L;
    private String word;
    private String traslation;

    public TermModel() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTraslation() {
        return traslation;
    }

    public void setTraslation(String traslation) {
        this.traslation = traslation;
    }
}
