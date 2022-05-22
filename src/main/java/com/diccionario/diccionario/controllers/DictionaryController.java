package com.diccionario.diccionario.controllers;

import com.diccionario.diccionario.models.ResponseModel;
import com.diccionario.diccionario.models.TermModel;
import com.diccionario.diccionario.services.impl.DictionaryServiceImpl;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class DictionaryController {

    // DictionaryService object

    DictionaryServiceImpl dictionaryService = new DictionaryServiceImpl();

    // Method to get the complete dictionary
    @GetMapping(value = "/getDictionary")
    public @ResponseBody ResponseModel getDictionary(){
        ResponseModel response = new ResponseModel();

        try {

            response = dictionaryService.getDictionary();

        } catch ( Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @PostMapping(value = "/addTerm")
    public @ResponseBody ResponseModel addTerm(@RequestBody TermModel term){
        ResponseModel response = new ResponseModel();
        try{
            response = dictionaryService.addTerm(term);
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
