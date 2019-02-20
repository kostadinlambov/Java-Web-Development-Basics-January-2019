package app.web.mbeans;

import app.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UnfriendBean {
    private UserService userService;

    public UnfriendBean() {
    }

    @Inject
    public UnfriendBean(UserService userService) {
        this.userService = userService;
    }

    public void removeFriend(String friendId) throws IOException {

        boolean result = this.userService.removeFriend(friendId);

        if(!result){
            throw new IllegalArgumentException("Something went wrong");
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/jsf/friends.xhtml");



    }
}
