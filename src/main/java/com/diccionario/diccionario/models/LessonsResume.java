package com.diccionario.diccionario.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class LessonsResume implements Serializable {


    private static final long serialVersionUID = -4959575877870762014L;
    private int userId;
    private int idLesson;
    private int stateLesson;

    public LessonsResume() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public int getStateLesson() {
        return stateLesson;
    }

    public void setStateLesson(int stateLesson) {
        this.stateLesson = stateLesson;
    }
}
