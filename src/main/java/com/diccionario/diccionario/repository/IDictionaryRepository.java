package com.diccionario.diccionario.repository;

import com.diccionario.diccionario.models.DictionaryModel;
import com.diccionario.diccionario.models.TermModel;

public interface IDictionaryRepository {

    public DictionaryModel getDictionary();

    public void addTerm(TermModel term);
}
