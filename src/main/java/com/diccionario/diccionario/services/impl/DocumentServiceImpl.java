package com.diccionario.diccionario.services.impl;

import com.diccionario.diccionario.models.DocumentModel;
import com.diccionario.diccionario.models.ResponseModel;
import com.diccionario.diccionario.models.UserModel;
import com.diccionario.diccionario.repository.impl.DocumentRepositoryImpl;
import com.diccionario.diccionario.repository.impl.UserRepositoryImpl;
import com.diccionario.diccionario.services.IDocumentService;
import com.diccionario.diccionario.utils.EmailCommunication;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class DocumentServiceImpl implements IDocumentService {

    @Autowired
    DocumentRepositoryImpl docRepo = new DocumentRepositoryImpl();

    UserRepositoryImpl userRepo = new UserRepositoryImpl();

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

            if (res == 1){
                res = sendDocument(doc);
            }

            response.setData(res);
            response.setError(null);
            switch (res) {
                case 1 -> response.setMessageResponse("Documento almacenado correctamente.");
                case 0 -> response.setMessageResponse("Documento no se almacenó en la BD.");
                case -1 -> response.setMessageResponse("Error en servicio al almacenar.");
            }

        } catch (Exception e){
            response.setError(e.getMessage());
            response.setMessageResponse("Error en servicio al almacenar.");
            response.setData(null);
            e.printStackTrace();
        }

        return response;
    }

    public int sendDocument(DocumentModel doc){
        int res = 0;

        // primero, cargo los correos de los alumnos, para enviar los documentos
        List<UserModel> users = new ArrayList<>();

        // los alumnos tienen userType = 2
        users = userRepo.getAllCLients();


        // ahora genero un documento, que se guardará de forma local en el servidor, para ser añadido al
        // documento como un adjunto:

        // create the document

        String pName = "src/main/resources/temporal/"+doc.getNameDocument()+".pdf";

        List<String> paths = new ArrayList<>();

        paths.add(pName);

        File file = new File(pName);

        try (FileOutputStream fos = new FileOutputStream(file)){

            byte[] decoder = Base64.getDecoder().decode(doc.getDocument());

            fos.write((decoder));

            // ahora podemos iterar sobre los usuarios, y a quienes sean tipo 2, se les enviará el correo, con el archivo.

            for (UserModel usr: users ) {

                if (usr.getUserType() == 2){
                    String bodyMessage = "Estimado  " + usr.getClientName() + "\n" +
                            "\n Se adjunta documento " + doc.getNameDocument() + "\n" +
                            " Saludos!";

                    res = EmailCommunication.sendMail(usr.getClientEmail(), "Material visto en clases", bodyMessage, paths);

                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public ResponseModel getDocumentsByLessonAndUser(int idLesson, String email) {
        ResponseModel response = new ResponseModel();
        try{

            List<DocumentModel> documents = docRepo.getDocumentsByLesson(idLesson);

            int res = sendDocuments(documents, email);

            response.setData(res);
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

    public int sendDocuments(List<DocumentModel> documents, String email){
        int res = 0;

        List<String> paths = new ArrayList<>();

        for (DocumentModel doc : documents) {

            String pName = "src/main/resources/temporal/"+doc.getNameDocument()+".pdf";

            paths.add(pName);

            File file = new File(pName);

            try (FileOutputStream fos = new FileOutputStream(file)){

                byte[] decoder = Base64.getDecoder().decode(doc.getDocument());

                fos.write((decoder));

            } catch (Exception e){
                e.printStackTrace();
            }

        }

        UserModel usr = userRepo.getClientByEmail(email);

        // ahora podemos iterar sobre los usuarios, y a quienes sean tipo 2, se les enviará el correo, con el archivo.

        String bodyMessage = "Estimado  " + usr.getClientName() + "\n" +
                "\n Se adjuntan documentos de la lección N° " + documents.get(0).getIdLesson() + "\n" +
                " Saludos!";

        res = EmailCommunication.sendMail(usr.getClientEmail(), "Material solicitado", bodyMessage, paths);

        return res;
    }
}








































