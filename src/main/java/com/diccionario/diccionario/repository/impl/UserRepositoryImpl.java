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
            logIn = null;
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
        user.setClientEmail(rs.getString("email"));
        user.setClientPassword(rs.getString("contrasena"));
        user.setClientName(rs.getString("nombre"));
        user.setUserType(rs.getInt("tipo_usuario_id_tipo"));

        return user;
    }

    @Override
    public UserModel getClientByEmail(String email) {

        UserModel user;

        try(PreparedStatement stmt = getConnection()
                .prepareStatement("SELECT * FROM usuario WHERE email = ?")) {
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                user = mapUser(rs);
            }else{
                user = null;
            }
        } catch ( SQLException e) {
            user = null;
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int deleteClient(String email) {
        return 0;
    }

    @Override
    public int editClient(UserModel user) {
        int res = 0;

        String query = "UPDATE usuario SET nombre= ?, email = ?, contrasena = ? , apellidos = ?, telefono = ? " +
                "WHERE id = ?";

        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1,user.getClientName());
            stmt.setString(2,user.getClientEmail());
            stmt.setString(3,user.getClientPassword());
            stmt.setString(4,user.getClientLastNames());
            stmt.setString(5,user.getClientPhone());
            stmt.setInt(6, user.getIdUser());

            res = stmt.executeUpdate();
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int updatePassword(UserModel user) {
        int res = 0;

        String query = "UPDATE usuario SET contrasena = ? " +
                "WHERE id = ?";

        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1,user.getClientPassword());
            stmt.setInt(2, user.getIdUser());

            res = stmt.executeUpdate();
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int createClient(UserModel user) {
        int res = 0;
        String query = "INSERT INTO usuario VALUES (0, ?, ?, ?, ?)";
        try(PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1,user.getClientEmail());
            stmt.setString(2, user.getClientPassword());
            stmt.setString(3,user.getClientName());
            stmt.setInt(4, user.getUserType());

            res = stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            res = -1;
        }
        return res;
    }
}
