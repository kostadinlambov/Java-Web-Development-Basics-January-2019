package app.web.mbeans;


import app.domain.models.binding.DocumentCreateBindingModel;
import app.domain.models.service.DocumentServiceModel;
import app.domain.models.view.DocumentDownloadViewModel;
import app.services.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.OutputStream;

@Named
@RequestScoped
public class DocumentDownloadBean {

    private ModelMapper modelMapper;
    private DocumentService documentService;
    private DocumentDownloadViewModel documentDownloadViewModel;

    public DocumentDownloadBean() {
    }

    @Inject
    public DocumentDownloadBean(ModelMapper modelMapper, DocumentService documentService) {
        this.modelMapper = modelMapper;
        this.documentService = documentService;
    }

    @PostConstruct
    public void init(){
        this.documentDownloadViewModel = new DocumentDownloadViewModel();


    }

    public void downloadDocument(String id) throws IOException {

        DocumentServiceModel byId1 = this.documentService.findById(id);
        DocumentServiceModel documentToDownload= this.modelMapper.map(byId1, DocumentServiceModel.class);

        FacesContext.getCurrentInstance().getExternalContext().addResponseHeader("Content-Disposition" ,"attachment; filename=MyFileName.pdf");

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset();
        ec.setResponseContentType("application/pdf");
        ec.setResponseContentLength(this.documentDownloadViewModel.toString().length());
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + documentToDownload.getTitle() + "\"");

        OutputStream output = ec.getResponseOutputStream();
        output.write(documentToDownload.getTitle().getBytes());
        output.write(documentToDownload.getContent().getBytes());
        fc.responseComplete();
    }
}
