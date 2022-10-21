package com.login.repository;

import com.login.config.MySQLConnection;
import com.login.model.UsersModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public List<UsersModel> getUsersByEmailAndPassword(String email,String password){
        List<UsersModel> listUser=new ArrayList<>();
        try{
            String query="select * from users u where u.email=? and u.password=?";
            Connection connection= MySQLConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                UsersModel userModel=new UsersModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setAvatar(resultSet.getString("avatar"));
                userModel.setRoleId(resultSet.getInt("role_id"));
                listUser.add(userModel);
            }
            connection.close();
        }catch (Exception e){
            System.out.printf("Error getUsersByEmailAndPassword"+ e.getMessage());

        }
        return listUser;


    }
}
