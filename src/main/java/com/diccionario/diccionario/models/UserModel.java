package com.diccionario.diccionario.models;

import java.io.Serializable;

public class UserModel implements Serializable {


    private static final long serialVersionUID = -6871345247575479631L;
    private int idUser;
    private String email;
    private String password;
    private String name;
    private int userType;

    public UserModel() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
