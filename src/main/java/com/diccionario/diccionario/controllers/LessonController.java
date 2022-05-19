package com.diccionario.diccionario.controllers;

import com.diccionario.diccionario.models.CommentaryModel;
import com.diccionario.diccionario.models.ResponseModel;
import com.diccionario.diccionario.services.impl.LessonServiceImpl;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class LessonController {

    LessonServiceImpl lessonService = new LessonServiceImpl();

    @GetMapping(value = "/getLessons")
    public @ResponseBody ResponseModel getLessons(){

        ResponseModel response = new ResponseModel();

        try{

            response = lessonService.getLessons();

        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @GetMapping(value = "/getResponses")
    public @ResponseBody ResponseModel getResponses(){

        ResponseModel response = new ResponseModel();

        try {
            response = lessonService.getAnswers();
        }catch (Exception e ) {
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping(value = "/addCommentary")
    public @ResponseBody ResponseModel addCommentary(@RequestBody CommentaryModel comment){
        ResponseModel response = new ResponseModel();
        try {

            response = lessonService.addComment(comment);

        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping(value = "/lastAproved{email}")
    public @ResponseBody ResponseModel lastAproved(@RequestParam(value = "email") String email){
        ResponseModel response = new ResponseModel();

        try {
            response = lessonService.lastAproved(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
