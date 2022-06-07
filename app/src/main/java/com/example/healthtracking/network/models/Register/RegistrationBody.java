package com.example.healthtracking.network.models.Register;

import com.google.gson.annotations.SerializedName;

public class RegistrationBody {
    @SerializedName("username")
    private String editUserName;
    @SerializedName("login")
    private String editLogin;
    @SerializedName("password")
    private String editPassword;

    public RegistrationBody(String editUserName, String editLogin, String editPassword) {
        this.editUserName = editUserName;
        this.editLogin = editLogin;
        this.editPassword = editPassword;
    }

    public String getEditUserName() {
        return editUserName;
    }

    public void setEditUserName(String editUserName) {
        this.editUserName = editUserName;
    }

    public String getEditLogin() {
        return editLogin;
    }

    public void setEditLogin(String editLogin) {
        this.editLogin = editLogin;
    }

    public String getEditPassword() {
        return editPassword;
    }

    public void setEditPassword(String editPassword) {
        this.editPassword = editPassword;
    }
}
