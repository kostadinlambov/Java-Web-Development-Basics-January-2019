package app.domain.models.binding;

import app.domain.entities.Gender;

import javax.validation.constraints.NotNull;

public class UserRegisterBindingModel {
    private String username;
    private String password;
    private String confirmPassword;
    private Gender gender;

    public UserRegisterBindingModel() {
    }


    @NotNull
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotNull
    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
