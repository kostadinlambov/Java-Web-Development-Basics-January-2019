package app.repositories;

import app.domain.entities.Document;

public interface DocumentRepository extends GenericRepository<Document, String> {
    int removeDocument(String id);
}
