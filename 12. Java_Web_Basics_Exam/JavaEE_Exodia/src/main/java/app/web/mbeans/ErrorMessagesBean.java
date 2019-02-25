package app.web.mbeans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Named
@RequestScoped
public class ErrorMessagesBean {
    public List<FacesMessage> getMessages() {
        List<FacesMessage> messages = new ArrayList<FacesMessage>();
        Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
        while (iter.hasNext()) {
            messages.add(iter.next());
            System.out.println();
        }

        return messages;
    }
}
