package com.diccionario.diccionario.repository;

import com.diccionario.diccionario.models.LogInModel;
import com.diccionario.diccionario.models.UserModel;

import java.util.List;

public interface IUserRepository {

    public List<UserModel> getAllCLients();

    public UserModel getClientByEmail(String email);

    public int deleteClient(String email);

    public int editClient(UserModel user);

    public int createClient(UserModel user);

    public LogInModel logIn(LogInModel log);
}
