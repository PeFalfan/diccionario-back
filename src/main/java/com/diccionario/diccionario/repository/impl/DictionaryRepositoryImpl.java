package com.diccionario.diccionario.repository.impl;

import com.diccionario.diccionario.models.DictionaryModel;
import com.diccionario.diccionario.models.TermModel;
import com.diccionario.diccionario.repository.IDictionaryRepository;
import com.diccionario.diccionario.utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DictionaryRepositoryImpl implements IDictionaryRepository {

    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public DictionaryModel getDictionary() {

        DictionaryModel dictionary = new DictionaryModel();

        List<TermModel> terms = new ArrayList<>();

        String query = "SELECT * FROM diccionario";

        try (Statement stmt = getConnection().createStatement();

             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                terms.add(mapTerm(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        dictionary.setTerms(terms);
        return dictionary;
    }

    public TermModel mapTerm(ResultSet rs) throws SQLException {

        TermModel term = new TermModel();

        term.setWord(rs.getString("palabra"));
        term.setTraslation(rs.getString("significado"));

        return  term;
    }

    @Override
    public void addTerm(TermModel term) {

    }
}
