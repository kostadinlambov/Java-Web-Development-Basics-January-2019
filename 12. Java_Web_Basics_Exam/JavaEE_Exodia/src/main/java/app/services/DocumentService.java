package app.services;

import app.domain.models.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService  {

    DocumentServiceModel save(DocumentServiceModel documentServiceModel);

    DocumentServiceModel findById(String id);

    List<DocumentServiceModel> allDocuments();

    boolean removeDocument(String documentId);


}
