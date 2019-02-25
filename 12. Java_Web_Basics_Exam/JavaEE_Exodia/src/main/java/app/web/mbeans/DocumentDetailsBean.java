package app.web.mbeans;

import app.domain.models.service.DocumentServiceModel;
import app.domain.models.service.UserServiceModel;
import app.domain.models.view.DocumentViewModel;
import app.services.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DocumentDetailsBean {
    private ModelMapper modelMapper;
    private DocumentService documentService;
    private DocumentViewModel documentViewModel;

    public DocumentDetailsBean() {
    }

    @Inject
    public DocumentDetailsBean(ModelMapper modelMapper, DocumentService documentService) {
        this.modelMapper = modelMapper;
        this.documentService = documentService;
    }

    @PostConstruct
    public void init(){

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        String id = context.getRequestParameterMap().get("id");

        DocumentServiceModel documentServiceById = this.documentService.findById(id);

        DocumentViewModel documentViewModel = this.modelMapper.map(documentServiceById, DocumentViewModel.class);

        if(documentViewModel == null){
            throw new IllegalArgumentException("Something went wrong");
        }

        this.documentViewModel = documentViewModel;
    }

    public DocumentViewModel getDocumentViewModel() {
        return this.documentViewModel;
    }

    public void setDocumentViewModel(DocumentViewModel documentViewModel) {
        this.documentViewModel = documentViewModel;
    }
}
