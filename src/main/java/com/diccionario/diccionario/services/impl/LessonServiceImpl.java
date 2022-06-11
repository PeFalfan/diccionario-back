package com.diccionario.diccionario.services.impl;

import com.diccionario.diccionario.models.*;
import com.diccionario.diccionario.repository.impl.LessonRepositoryImpl;
import com.diccionario.diccionario.repository.impl.UserRepositoryImpl;
import com.diccionario.diccionario.services.ILessonService;
import com.diccionario.diccionario.utils.EmailCommunication;

import java.util.ArrayList;
import java.util.List;

public class LessonServiceImpl implements ILessonService {

    LessonRepositoryImpl lessonRepo = new LessonRepositoryImpl();
    UserRepositoryImpl userRepo = new UserRepositoryImpl();

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

        UserModel profe = new UserModel();

        UserModel alumno = new UserModel();

        List<UserModel> users = new ArrayList<>();

        try{
            response.setData(lessonRepo.addCommentary(comment));
            if(response.getData().equals(1)){

                // now we send the mailt to the teacher
                // primero, cargamos a los usuarios, y ya teniendolos, podremos obtener la info del profe, y el alumno que hizo el comentario.

                users = userRepo.getAllCLients();

                for (UserModel us : users) {
                    if (us.getUserType() == 1){
                        profe = us;
                    }

                    if (us.getIdUser() == comment.getIdUser()){
                        alumno = us;
                    }
                }

                String bodyMessage = "Estimado  " + profe.getClientName() + ".                                                       " + comment.getCreationDate() + "\n" +
                                     "\nMediante el presente documento, yo " + alumno.getClientName() + ", necesito aclarar o resolver lo siguiente: \n"+
                                     comment.getCommentary() + "\n";


                EmailCommunication.sendMail(profe.getClientEmail(), "Comentario Alumno " + comment.getCreationDate(),bodyMessage);

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

    public ResponseModel getResume(){
        ResponseModel response = new ResponseModel();
        List<StudentResumeModel> resume = new ArrayList<>();
        List<LessonModel> lessons = new ArrayList<>();
        List<UserModel> users = new ArrayList<>();

        try{

            // First, we gather all the students
            users = userRepo.getAllCLients();

            // Second the lessons
            lessons = lessonRepo.getLessons();

            // now, we start creating the resume object, by each student

            for (UserModel user : users) {
                if (user.getUserType() == 2){
                    StudentResumeModel student = new StudentResumeModel();
                    student.setStudentName(user.getClientName());
                    int lastApproved = lessonRepo.lastApprovedLesson(user.getClientEmail());
                    String lessonRange = String.valueOf(lastApproved);
                    lessonRange += "/" + lessons.size();

                    student.setLessons(lessonRange);

                    student.setPercentage(calculatePercentage(lessons.size(), lastApproved ));

                    resume.add(student);
                }
            }

            response.setData(resume);
            response.setMessageResponse("Datos cargados correctamente.");
            response.setError(null);

        }catch (Exception e){
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error al cargar data de estudiantes.");
            e.printStackTrace();

        }
        return response;
    }

    public String calculatePercentage(int lessonsQuantity, int approvedLessons){
        double percentage = 0.0;

        percentage = (double) (( approvedLessons * 100 )/ lessonsQuantity);

        return String.format("%.1f", percentage);
    }
}
