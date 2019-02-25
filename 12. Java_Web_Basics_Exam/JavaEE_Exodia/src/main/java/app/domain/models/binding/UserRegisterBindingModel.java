package app.domain.models.binding;

import app.validations.Password;
import app.validations.PasswordMatching;
import app.validations.UniqueUsername;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@PasswordMatching
public class UserRegisterBindingModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;

    public UserRegisterBindingModel() {
    }


    @NotNull
    @UniqueUsername
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Password(minLength = 4, maxLength = 16, containsOnlyLettersAndDigits = true, message = "Invalid Password format.")
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
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
