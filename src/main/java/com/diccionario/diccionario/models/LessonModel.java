package com.diccionario.diccionario.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.util.List;

@EntityScan
public class LessonModel implements Serializable {

    private static final long serialVersionUID = -7142475375308616051L;
    private int idLeccion;
    private String tituloLeccion;
    private List<QuestionModel> preguntas;

    public LessonModel() {
    }

    public int getIdLeccion() {
        return idLeccion;
    }

    public void setIdLeccion(int idLeccion) {
        this.idLeccion = idLeccion;
    }

    public String getTituloLeccion() {
        return tituloLeccion;
    }

    public void setTituloLeccion(String tituloLeccion) {
        this.tituloLeccion = tituloLeccion;
    }

    public List<QuestionModel> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<QuestionModel> preguntas) {
        this.preguntas = preguntas;
    }
}
