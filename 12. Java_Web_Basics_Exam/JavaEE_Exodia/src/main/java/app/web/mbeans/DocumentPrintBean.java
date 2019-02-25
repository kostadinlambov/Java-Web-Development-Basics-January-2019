package app.web.mbeans;

import app.services.DocumentService;
import app.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class DocumentPrintBean {
    private DocumentService documentService;

    public DocumentPrintBean() {
    }

    @Inject
    public DocumentPrintBean(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void printDocument(String documentId) throws IOException {

        boolean result = this.documentService.removeDocument(documentId);

        if(!result){
            throw new IllegalArgumentException("Something went wrong");
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/jsf/home.xhtml");

    }
}
