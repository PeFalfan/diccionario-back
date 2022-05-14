package com.diccionario.diccionario.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class UserAnswerModel implements Serializable {

    private static final long serialVersionUID = -7439126098997849518L;
    private int idResponse;
    private String response;
    private int idUser;
    private int idQuestion;
    private int idLesson;
    private String answer;

    public UserAnswerModel() {
    }

    public int getIdResponse() {
        return idResponse;
    }

    public void setIdResponse(int idResponse) {
        this.idResponse = idResponse;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
