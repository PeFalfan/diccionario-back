package com.diccionario.diccionario.services;

import com.diccionario.diccionario.models.ResponseModel;
import com.diccionario.diccionario.models.TermModel;

public interface IDictionaryService {

    public ResponseModel getDictionary();

    public ResponseModel addTerm(TermModel term);

}
