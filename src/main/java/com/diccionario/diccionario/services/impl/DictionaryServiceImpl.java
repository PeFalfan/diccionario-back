package com.diccionario.diccionario.services.impl;

import com.diccionario.diccionario.models.ResponseModel;
import com.diccionario.diccionario.models.TermModel;
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
    public ResponseModel addTerm(TermModel term) {
        ResponseModel response = new ResponseModel();

        try{
            int res = dictionaryRepo.addTerm(term);
            response.setData(res);
            switch (res){
                case 1: response.setMessageResponse("Termino agregado correctamente.");break;
                case 0: response.setMessageResponse("Termino No agregado.");break;
                case -1: response.setMessageResponse("Error de servicio al agregar termino");break;
            }
            response.setError(null);
        } catch (Exception e){
            e.printStackTrace();
            response.setError(e.getMessage());
            response.setData(null);
            response.setMessageResponse("Error al agregar termino");
        }
        return response;
    }
}
