package com.diccionario.diccionario.services;

import com.diccionario.diccionario.models.ResponseModel;

public interface IDictionaryService {

    public ResponseModel getDictionary();

    public ResponseModel addTerm();

}
