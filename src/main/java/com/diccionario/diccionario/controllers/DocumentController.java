package com.diccionario.diccionario.controllers;

import com.diccionario.diccionario.models.DocumentModel;
import com.diccionario.diccionario.models.ResponseModel;
import com.diccionario.diccionario.services.impl.DocumentServiceImpl;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class DocumentController {
    DocumentServiceImpl docService = new DocumentServiceImpl();

    @GetMapping(value = "/getDocuments{idLesson}")
    public @ResponseBody ResponseModel getDocuments(@RequestParam(value = "idLesson") int idLesson){
        ResponseModel response = new ResponseModel();
        try {
            response = docService.getDocumentsById(idLesson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping(value = "/uploadFile")
    public @ResponseBody ResponseModel uploadFile(@RequestBody DocumentModel doc){
        ResponseModel response = new ResponseModel();
        try{
            response = docService.uploadDocument(doc);
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
