package com.diccionario.diccionario.repository.impl;

import com.diccionario.diccionario.models.UserModel;
import com.diccionario.diccionario.repository.IUserRepository;
import com.diccionario.diccionario.utils.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public List<UserModel> getAllCLients() {

        // list of users

        List<UserModel> users = new ArrayList<>();

        // query

        String query = "SELECT * FROM usuario";

        try (Statement stmt = getConnection().createStatement();

             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                users.add(mapUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public UserModel mapUser(ResultSet rs) throws SQLException {

        UserModel user = new UserModel();

        user.setIdUser(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("contrasena"));
        user.setName(rs.getString("nombre"));
        user.setUserType(rs.getInt("tipo_usuario_id_tipo"));

        return user;
    }

    @Override
    public UserModel getClientByEmail(String email) {
        return null;
    }

    @Override
    public int deleteClient(String email) {
        return 0;
    }

    @Override
    public int editClient(UserModel user) {
        return 0;
    }

    @Override
    public int createClient(UserModel user) {
        return 0;
    }
}
