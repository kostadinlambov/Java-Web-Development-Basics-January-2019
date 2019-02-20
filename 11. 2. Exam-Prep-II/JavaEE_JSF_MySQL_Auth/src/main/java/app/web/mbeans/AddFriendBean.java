package app.web.mbeans;

import app.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class AddFriendBean {

    private UserService userService;

    public AddFriendBean() {
    }

    @Inject
    public AddFriendBean(UserService userService) {
        this.userService = userService;
    }

    public void addFriend(String id) throws IOException {
        boolean result = this.userService.addFriend(id);

        if(!result){
            throw new IllegalArgumentException("Something went wrong");
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/jsf/home.xhtml");
    }
}
