package com.diccionario.diccionario.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.util.ArrayList;

@EntityScan
public class StudentResumeModel implements Serializable {
    private static final long serialVersionUID = -744089147320610627L;
    private String studentName;
    private String lessons;
    private String percentage;

    public StudentResumeModel() {
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getLessons() {
        return lessons;
    }

    public void setLessons(String lessons) {
        this.lessons = lessons;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
