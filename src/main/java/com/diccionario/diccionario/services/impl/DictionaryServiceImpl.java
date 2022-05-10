package com.diccionario.diccionario.services.impl;

import com.diccionario.diccionario.models.ResponseModel;
import com.diccionario.diccionario.repository.impl.DictionaryRepositoryImpl;
import com.diccionario.diccionario.services.IDictionaryService;

public class DictionaryServiceImpl implements IDictionaryService {

    DictionaryRepositoryImpl dictionaryRepo = new DictionaryRepositoryImpl();

    @Override
    public ResponseModel getDictionary() {

        ResponseModel response = new ResponseModel();

        try{
            response.setData(dictionaryRepo.getDictionary());
            response.setMessageResponse("Diccionario consultado correctamente.");
            response.setError(null);
        } catch (Exception e){

            response.setData(null);
            response.setMessageResponse("Error al consultar Diccionario.");
            response.setError(e.getMessage());

        }

        return response;
    }

    @Override
    public ResponseModel addTerm() {
        return null;
    }
}
