package metube.domain.models.view;

import java.util.List;

public class UserProfileViewModel {

    private String username;
    private String email;
    private List<UserTubesViewModel> tubes;

    public UserProfileViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserTubesViewModel> getTubes() {
        return tubes;
    }

    public void setTubes(List<UserTubesViewModel> tubes) {
        this.tubes = tubes;
    }
}
