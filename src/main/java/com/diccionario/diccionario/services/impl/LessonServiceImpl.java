package com.diccionario.diccionario.services.impl;

import com.diccionario.diccionario.models.ResponseModel;
import com.diccionario.diccionario.repository.impl.LessonRepositoryImpl;
import com.diccionario.diccionario.services.ILessonService;

public class LessonServiceImpl implements ILessonService {

    LessonRepositoryImpl lessonRepo = new LessonRepositoryImpl();

    @Override
    public ResponseModel getLessons() {

        ResponseModel response = new ResponseModel();

        try {

            response.setData(lessonRepo.getLessons());
            response.setMessageResponse("Lecciones cargadas correctamente");
            response.setError(null);

        } catch ( Exception e ){
            response.setData(null);
            response.setMessageResponse("Error al cargar lecciones");
            response.setError(e.getMessage());
        }

        return response;
    }
}
