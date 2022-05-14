package com.diccionario.diccionario.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class CommentaryModel implements Serializable {

    private static final long serialVersionUID = 1736549510665480616L;
    private int idCommentary;
    private String commentary;
    private int idLesson;
    private int idUser;

    public CommentaryModel() {
    }

    public int getIdCommentary() {
        return idCommentary;
    }

    public void setIdCommentary(int idCommentary) {
        this.idCommentary = idCommentary;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public int getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
