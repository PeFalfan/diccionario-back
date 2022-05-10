package com.diccionario.diccionario.models;

import java.io.Serializable;

public class ResponseModel implements Serializable {

    private static final long serialVersionUID = 8310313932262293124L;
    private String messageResponse;
    private Object data;
    private String error;

    public ResponseModel() {
    }

    public String getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(String messageResponse) {
        this.messageResponse = messageResponse;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
