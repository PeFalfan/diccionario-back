package com.diccionario.diccionario.models;

import java.io.Serializable;

public class UserModel implements Serializable {


    private static final long serialVersionUID = -6871345247575479631L;
    private String clientName;
    private String clientLastNames;
    private String clientPhone;
    private int idUser;
    private String clientEmail;
    private String clientPassword;
    private int userType;

    public UserModel() {
    }

    public String getClientLastNames() {
        return clientLastNames;
    }

    public void setClientLastNames(String clientLastNames) {
        this.clientLastNames = clientLastNames;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
