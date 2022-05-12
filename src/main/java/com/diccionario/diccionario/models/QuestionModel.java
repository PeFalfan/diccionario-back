package com.diccionario.diccionario.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class QuestionModel implements Serializable {

    private static final long serialVersionUID = 6615840586389421600L;
    private int idLeccion;
    private int idPregunta;
    private String textoPregunta;
    private String palabraDiccionario; // respuesta

    public QuestionModel() {
    }

    public int getIdLeccion() {
        return idLeccion;
    }

    public void setIdLeccion(int idLeccion) {
        this.idLeccion = idLeccion;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }

    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }

    public String getPalabraDiccionario() {
        return palabraDiccionario;
    }

    public void setPalabraDiccionario(String palabraDiccionario) {
        this.palabraDiccionario = palabraDiccionario;
    }
}
