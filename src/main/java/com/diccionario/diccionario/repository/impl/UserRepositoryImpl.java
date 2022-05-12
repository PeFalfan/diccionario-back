package com.diccionario.diccionario.repository.impl;

import com.diccionario.diccionario.models.LogInModel;
import com.diccionario.diccionario.models.UserModel;
import com.diccionario.diccionario.repository.IUserRepository;
import com.diccionario.diccionario.utils.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
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

    @Override
    public LogInModel logIn(LogInModel logInIn) {

        LogInModel logIn = new LogInModel();

        String query = "SELECT email, contrasena FROM usuario WHERE email = ?";

        try(PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1,logInIn.getEmail());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                logIn = mapLogIn(rs);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return logIn;
    }

    private LogInModel mapLogIn(ResultSet rs) throws SQLException {
        LogInModel logIn = new LogInModel();

        logIn.setEmail(rs.getString("email"));
        logIn.setPassword(rs.getString("contrasena"));

        return logIn;
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
