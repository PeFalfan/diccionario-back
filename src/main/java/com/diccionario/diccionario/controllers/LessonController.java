package com.diccionario.diccionario.controllers;

import com.diccionario.diccionario.models.ResponseModel;
import com.diccionario.diccionario.services.impl.LessonServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
