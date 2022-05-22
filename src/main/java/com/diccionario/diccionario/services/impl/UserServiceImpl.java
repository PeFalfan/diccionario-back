package com.diccionario.diccionario.services.impl;

import com.diccionario.diccionario.models.LogInModel;
import com.diccionario.diccionario.models.ResponseModel;
import com.diccionario.diccionario.models.UserModel;
import com.diccionario.diccionario.repository.impl.UserRepositoryImpl;
import com.diccionario.diccionario.services.IUserService;
import com.diccionario.diccionario.utils.EmailCommunication;
import com.diccionario.diccionario.utils.PasswordEncryption;
import com.diccionario.diccionario.utils.PasswordGenerator;

public class UserServiceImpl implements IUserService {

    PasswordEncryption pe = new PasswordEncryption();

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
        ResponseModel response = new ResponseModel();
        try {
            int res = userRepo.createClient(user);
            response.setData(res);
            switch (res){
                case 1:response.setMessageResponse("Usuario creado correctamente.");break;
                case 0:response.setMessageResponse("Usuario ya existe en la BD");break;
                case -1: response.setMessageResponse("Error de servicio al registrar cliente.");break;
            }
            response.setError(null);
        }catch (Exception e){
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error de servicio al registrar cliente");
        }
        return response;
    }

    public ResponseModel recoverPassword(String email) {

        ResponseModel response = new ResponseModel();

        String newPass;

        int state;

        int emailStatus = 0;

        try {
            UserModel usuario = userRepo.getClientByEmail(email);
            if (usuario == null) {
                response.setData(null);
                response.setMessageResponse("Correo no se encuentra registrado");
                response.setError("Correo no registrado");
            } else {
                newPass = PasswordGenerator.getAlphaNumericString();

                // TODO we should encript the password here
                newPass = pe.encode(newPass);

                usuario.setClientPassword(newPass);

                state = userRepo.updatePassword(usuario);

                if (state == 1) {
                    String bodyMessage = "Buenas tardes " + usuario.getClientName() + "!\n" +
                            "Junto con saludarte, te hacemos entrega de la nueva contraseña para ingresar a nuestra plataforma!\n" +
                            "Clave: "+ usuario.getClientPassword() + "\n" +
                            "Te esperamos!\n" +
                            "Atte.:\n" +
                            "Inserte nombre de universidad ficticia.";

                    emailStatus = EmailCommunication.sendMail(email, "Recuperacion de Contraseña", bodyMessage);
                }
            }

            if (emailStatus == 1) {
                response.setMessageResponse("Correo enviado correctamente!");
                response.setError(null);
            } else {
                response.setError("Error al enviar correo");
                response.setMessageResponse("Error al enviar correo...");
            }
        } catch (Exception e) {
            response.setError(e.getMessage());
            response.setMessageResponse("Error al enviar correo...");
            e.printStackTrace();
        }
        return response;
    }
}
