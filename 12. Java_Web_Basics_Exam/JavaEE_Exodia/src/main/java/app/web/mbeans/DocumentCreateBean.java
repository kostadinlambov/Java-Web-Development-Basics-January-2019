package app.web.mbeans;

import app.domain.models.binding.DocumentCreateBindingModel;
import app.domain.models.service.DocumentServiceModel;
import app.services.DocumentService;
import app.util.ValidationUtil;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class DocumentCreateBean {
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;
    private DocumentCreateBindingModel documentCreateBindingModel;
    private DocumentService documentService;

    public DocumentCreateBean() {
    }

    @Inject
    public DocumentCreateBean(ModelMapper modelMapper, ValidationUtil validationUtil, DocumentService documentService) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.documentService = documentService;
    }

    @PostConstruct
    public void init(){
        this.documentCreateBindingModel = new DocumentCreateBindingModel();
    }

    public DocumentCreateBindingModel getDocumentCreateBindingModel() {
        return this.documentCreateBindingModel;
    }

    public void setDocumentCreateBindingModel(DocumentCreateBindingModel documentCreateBindingModel) {
        this.documentCreateBindingModel = documentCreateBindingModel;
    }

    public void createDocument() throws IOException {

        if(!this.validationUtil.isValid(this.documentCreateBindingModel)){
            throw new IllegalArgumentException("Something went wrong!");
        }

        DocumentServiceModel documentServiceModel = this.modelMapper.map(this.documentCreateBindingModel, DocumentServiceModel.class);

        try{
            DocumentServiceModel savedDocument = this.documentService.save(documentServiceModel);
            FacesContext.getCurrentInstance().getExternalContext().redirect(String.format("/jsf/details.xhtml?id=%s", savedDocument.getId()));
        }catch (Exception e){
            throw new IllegalArgumentException("Something went wrong!");
        }
    }
}
