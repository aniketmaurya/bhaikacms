package com.cmssystem.useradmin.utility;

import org.mindrot.jbcrypt.BCrypt;

public class UtilityClass {

    private String encurptedPassword;

    public String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public boolean checkPass(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }


}
