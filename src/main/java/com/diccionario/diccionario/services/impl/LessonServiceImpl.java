package com.diccionario.diccionario.services.impl;

import com.diccionario.diccionario.models.CommentaryModel;
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

    public ResponseModel getAnswers() {
        ResponseModel response = new ResponseModel();

        try {

            response.setData(lessonRepo.getAnswers());
            response.setError(null);
            response.setMessageResponse("Respuestas cargadas correctamente.");
        }catch (Exception e) {
            response.setData(null);
            response.setMessageResponse("Error al consultar respuestas.");
            response.setError(e.getMessage());
        }
        return response;
    }

    public ResponseModel addComment(CommentaryModel comment){
        ResponseModel response = new ResponseModel();
        try{
            response.setData(lessonRepo.addCommentary(comment));
            if(response.getData().equals(1)){
                response.setMessageResponse("Comentario agregado correctamente");
                response.setError(null);
            } else{
                response.setError("Error al intentar insertar el comentario");
                response.setMessageResponse("No se pudo realizar insercion del comentario");
            }
        }catch (Exception e) {
            response.setData(null);
            response.setMessageResponse("Error de servidor al intentar insertar comentario");
            response.setError(e.getMessage());
        }
        return response;
    }

    public ResponseModel lastApproved(String email) {
        ResponseModel response = new ResponseModel();

        try {
            response.setData(lessonRepo.lastApprovedLesson(email));
            response.setMessageResponse("Solicitud realizada.");
            response.setError(null);
        } catch (Exception e) {
            response.setData(null);
            response.setMessageResponse("Error al solicitar datos");
            response.setError(e.getMessage());
            e.printStackTrace();
        }

        return response;
    }
}
