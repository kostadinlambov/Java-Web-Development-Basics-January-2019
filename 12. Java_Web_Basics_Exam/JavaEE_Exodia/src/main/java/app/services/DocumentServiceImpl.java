package app.services;

import app.domain.entities.Document;
import app.domain.models.service.DocumentServiceModel;
import app.repositories.DocumentRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService {
    private final ModelMapper modelMapper;
    private final DocumentRepository documentRepository;

    @Inject
    public DocumentServiceImpl(ModelMapper modelMapper, DocumentRepository documentRepository) {
        this.modelMapper = modelMapper;
        this.documentRepository = documentRepository;
    }


    @Override
    public DocumentServiceModel save(DocumentServiceModel documentServiceModel) {
        try {
            Document document = this.modelMapper.map(documentServiceModel, Document.class);
            Document savedDocument = this.documentRepository.save(document);

            return this.modelMapper.map(savedDocument, DocumentServiceModel.class);

        } catch (Exception e) {
            throw new IllegalArgumentException("Something went wrong!");
        }

    }

    @Override
    public DocumentServiceModel findById(String id) {
        Document document = this.documentRepository.findById(id);
        if (document == null) {
            throw new IllegalArgumentException("Document does not exists!");
        }
        return this.modelMapper.map(document, DocumentServiceModel.class);
    }

    @Override
    public List<DocumentServiceModel> allDocuments() {
        List<Document> documentsAll = this.documentRepository.findAll();

        if (documentsAll.size() > 0) {
            return documentsAll.stream().map(document -> this.modelMapper.map(document, DocumentServiceModel.class)).collect(Collectors.toList());
        }

        return null;
    }

    @Override
    public boolean removeDocument(String documentId) {
        Document documentRepositoryById = this.documentRepository.findById(documentId);

        if (documentRepositoryById != null) {
            int count = this.documentRepository.removeDocument(documentId);
            return count > 0;
        }
        return false;
    }


}
