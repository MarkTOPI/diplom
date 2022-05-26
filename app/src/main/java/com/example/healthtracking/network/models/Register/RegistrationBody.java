package com.example.healthtracking.network.models.Register;

import com.google.gson.annotations.SerializedName;

public class RegistrationBody {
    @SerializedName("editUserName")
    private String editUserName;
    @SerializedName("editLogin")
    private String editLogin;
    @SerializedName("editPassword")
    private String editPassword;
    @SerializedName("editRepeatPassword")
    private String editRepeatPassword;

    public RegistrationBody(String editUserName, String editLogin, String editPassword, String editRepeatPassword) {
        this.editUserName = editUserName;
        this.editLogin = editLogin;
        this.editPassword = editPassword;
        this.editRepeatPassword = editRepeatPassword;
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

    public String getEditRepeatPassword() {
        return editRepeatPassword;
    }

    public void setEditRepeatPassword(String editRepeatPassword) {
        this.editRepeatPassword = editRepeatPassword;
    }
}
