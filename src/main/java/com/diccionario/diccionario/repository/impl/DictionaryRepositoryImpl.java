package com.diccionario.diccionario.repository.impl;

import com.diccionario.diccionario.models.DictionaryModel;
import com.diccionario.diccionario.models.TermModel;
import com.diccionario.diccionario.repository.IDictionaryRepository;
import com.diccionario.diccionario.utils.DataBaseConnection;

import java.sql.*;
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
        term.setPronunciation(rs.getString("pronunciacion"));

        return  term;
    }

    @Override
    public int addTerm(TermModel term) {

        int res = 0;
        String query = "INSERT INTO diccionario VALUES( ?, ?, ?)";
        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1,term.getWord());
            stmt.setString(2, term.getTraslation());
            stmt.setString(3, term.getPronunciation());

            res = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            res = -1;
        }
        return res;
    }
}
