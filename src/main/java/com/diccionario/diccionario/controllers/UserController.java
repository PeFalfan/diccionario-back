package com.diccionario.diccionario.controllers;

import com.diccionario.diccionario.models.ResponseModel;
import com.diccionario.diccionario.services.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    // user Service object
    UserServiceImpl userService = new UserServiceImpl();

    // Method to get all users
    @GetMapping(value = "/getAllUsers")
    public @ResponseBody ResponseModel getAllUsers(){
        ResponseModel response = new ResponseModel();
        try{

            response = userService.getAllUsers();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
