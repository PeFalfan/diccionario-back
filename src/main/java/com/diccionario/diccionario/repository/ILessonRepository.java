package com.diccionario.diccionario.repository;

import com.diccionario.diccionario.models.LessonModel;

import java.util.List;

public interface ILessonRepository {

    public List<LessonModel> getLessons();
}
