package com.diccionario.diccionario.services;

import com.diccionario.diccionario.models.DocumentModel;
import com.diccionario.diccionario.models.ResponseModel;

public interface IDocumentService {

    public ResponseModel getDocumentsById(int idLesson);

    public ResponseModel uploadDocument(DocumentModel doc);
}
