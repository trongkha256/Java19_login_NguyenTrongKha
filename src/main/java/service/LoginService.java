package service;

import com.login.model.UsersModel;
import com.login.repository.UserRepository;

import java.util.List;

public class LoginService {
    UserRepository usersRepository=new UserRepository();
    public boolean checkLogin(String email,String password){

        List<UsersModel> list=usersRepository.getUsersByEmailAndPassword(email,password);
        if (list.size()>0){
            return true;
        }
        else
        {
            return false;
        }

    }
}
