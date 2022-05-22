package com.diccionario.diccionario.repository.impl;

import com.diccionario.diccionario.models.DocumentModel;
import com.diccionario.diccionario.repository.IDocumentRepository;
import com.diccionario.diccionario.utils.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DocumentRepositoryImpl implements IDocumentRepository {

    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public List<DocumentModel> getDocumentsByLesson(int idLesson) {
        List<DocumentModel> documents = new ArrayList<>();

        String query = "SELECT * FROM documento WHERE leccion_id_leccion = ?";

        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setInt(1, idLesson);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                documents.add(mapDocument(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return documents.size() == 0 ? null: documents;
    }

    public DocumentModel mapDocument(ResultSet rs) throws SQLException{
        DocumentModel doc = new DocumentModel();
        doc.setIdDocument(rs.getInt("id_documento"));
        doc.setNameDocument(rs.getString("nombre_documento"));
        doc.setDocument(rs.getString("documento"));
        doc.setIdLesson(rs.getInt("leccion_id_leccion"));
        return doc;
    }

    @Override
    public int uploadDocument(DocumentModel document) {
        int res = 0;
        String query = "INSERT INTO documento VALUES (0, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1,document.getNameDocument());
            stmt.setString(2, document.getDocument());
            stmt.setInt(3, document.getIdLesson());

            res = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            res = -1;
        }

        return res;
    }
}
