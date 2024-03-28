package com.monapp.monapp.Service;

import com.monapp.monapp.Model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    public boolean authenticate(User user) {

        return true;
    }
    private String mapRole(int role) {
        switch (role) {
            case 0:
                return "ADMIN";
            case 1:
                return "CHEF";
            case 2:
                return "EMPLOYEE";
            default:
                return "UNKNOWN";
        }
    }
}
