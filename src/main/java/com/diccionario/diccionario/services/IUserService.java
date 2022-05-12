package com.diccionario.diccionario.services;

import com.diccionario.diccionario.models.LogInModel;
import com.diccionario.diccionario.models.ResponseModel;
import com.diccionario.diccionario.models.UserModel;

public interface IUserService {

    public ResponseModel getAllUsers();

    public ResponseModel getClientByEmail(String email);

    public ResponseModel deleteClient(String email);

    public ResponseModel editClient(UserModel user);

    public ResponseModel createClient(UserModel user);

    public ResponseModel logIn(LogInModel log);

}
