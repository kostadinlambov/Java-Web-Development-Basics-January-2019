package app.web.mbeans;

import app.domain.models.service.DocumentServiceModel;
import app.domain.models.view.DocumentViewAllModel;
import app.domain.models.view.DocumentViewModel;
import app.services.DocumentService;
import app.util.ValidationUtil;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class DocumentAllHomeBean {
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;
    private DocumentService documentService;
    List<DocumentViewAllModel> documentViewModels;

    public DocumentAllHomeBean() {
    }

    @Inject
    public DocumentAllHomeBean(ModelMapper modelMapper, ValidationUtil validationUtil, DocumentService documentService) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.documentService = documentService;
    }

    @PostConstruct
    public void init(){

        List<DocumentServiceModel> documentServiceModels = this.documentService
                .allDocuments();


        if(documentServiceModels.size() > 0){
            this.documentViewModels = documentServiceModels.stream()
                    .map(documentServiceModel -> this.modelMapper
                            .map(documentServiceModel, DocumentViewAllModel.class))
                    .collect(Collectors.toList());

        }else{
            this.documentViewModels = new ArrayList<>();
        }
    }

    public List<DocumentViewAllModel> getDocumentViewModels() {
        return this.documentViewModels;
    }

    public void setDocumentViewModels(List<DocumentViewAllModel> documentViewModels) {
        this.documentViewModels = documentViewModels;
    }
}
