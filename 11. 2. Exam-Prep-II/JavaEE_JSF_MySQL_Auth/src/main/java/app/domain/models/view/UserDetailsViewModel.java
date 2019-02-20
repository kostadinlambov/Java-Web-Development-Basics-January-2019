package app.domain.models.view;

import app.domain.entities.Gender;

public class UserDetailsViewModel {
    private String username;
    private Gender gender;

    public UserDetailsViewModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
