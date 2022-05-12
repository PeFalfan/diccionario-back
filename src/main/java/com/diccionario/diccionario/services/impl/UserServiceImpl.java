package com.diccionario.diccionario.services.impl;

import com.diccionario.diccionario.models.LogInModel;
import com.diccionario.diccionario.models.ResponseModel;
import com.diccionario.diccionario.models.UserModel;
import com.diccionario.diccionario.repository.impl.UserRepositoryImpl;
import com.diccionario.diccionario.services.IUserService;

public class UserServiceImpl implements IUserService {

    // Object for the repo
    UserRepositoryImpl userRepo = new UserRepositoryImpl();

    @Override
    public ResponseModel getAllUsers() {

        ResponseModel response = new ResponseModel();

        try {
            response.setData(userRepo.getAllCLients());
            response.setMessageResponse("Usuarios cargados correctamente");
            response.setError(null);
        } catch ( Exception e) {
            response.setData(null);
            response.setMessageResponse("Error al cargar usuarios");
            response.setError(e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseModel logIn(LogInModel log) {

        ResponseModel response = new ResponseModel();

        try{

            LogInModel saveLogIn = userRepo.logIn(log);

            // yet unencripted

            if (log.getPassword().equals(saveLogIn.getPassword())){
                response.setMessageResponse("Validacion de usuario correcta");
                response.setData(1);
                response.setError(null);
            } else{
                response.setError("Credenciales incorrectas");
                response.setMessageResponse("Error en credenciales");
                response.setData(0);
            }

        } catch (Exception e){

            response.setError(e.getMessage());
            response.setMessageResponse("Error al consultar datos");
            response.setData(null);
            e.printStackTrace();

        }

        return response;
    }

    @Override
    public ResponseModel getClientByEmail(String email) {
        return null;
    }

    @Override
    public ResponseModel deleteClient(String email) {
        return null;
    }

    @Override
    public ResponseModel editClient(UserModel user) {
        return null;
    }

    @Override
    public ResponseModel createClient(UserModel user) {
        return null;
    }
}