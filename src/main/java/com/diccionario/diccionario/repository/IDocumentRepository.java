package com.diccionario.diccionario.repository;

import com.diccionario.diccionario.models.DocumentModel;

import java.util.List;

public interface IDocumentRepository {

    public List<DocumentModel> getDocumentsByLesson(int idLesson);

    public int uploadDocument(DocumentModel document);
}
