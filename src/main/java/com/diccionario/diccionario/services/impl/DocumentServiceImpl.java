package com.diccionario.diccionario.services.impl;

import com.diccionario.diccionario.models.DocumentModel;
import com.diccionario.diccionario.models.ResponseModel;
import com.diccionario.diccionario.repository.impl.DocumentRepositoryImpl;
import com.diccionario.diccionario.services.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements IDocumentService {

    @Autowired
    DocumentRepositoryImpl docRepo = new DocumentRepositoryImpl();

    @Override
    public ResponseModel getDocumentsById(int idLesson) {
        ResponseModel response = new ResponseModel();
        try{
            response.setData(docRepo.getDocumentsByLesson(idLesson));
            if (response.getData() == null){
                response.setMessageResponse("No se encuentran documentos cargados para la leccion N°" + idLesson +".");
            } else {
                response.setMessageResponse("Documentos cargados correctamente.");
            }
            response.setError(null);
        }catch (Exception e){
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error al realizar llamado al servicio");
        }
        return response;
    }

    @Override
    public ResponseModel uploadDocument(DocumentModel doc) {
        ResponseModel response = new ResponseModel();

        try{
            int res = docRepo.uploadDocument(doc);
            response.setData(res);
            response.setError(null);
            switch (res){
                case 1: response.setMessageResponse("Documento almacenado correctamente.");break;
                case 0: response.setMessageResponse("Documento no se almacenó en la BD.");break;
                case -1: response.setMessageResponse("Error en servicio al almacenar.");break;
            }

        } catch (Exception e){
            response.setError(e.getMessage());
            response.setMessageResponse("Error en servicio al almacenar.");
            response.setData(null);
            e.printStackTrace();
        }

        return response;
    }
}
