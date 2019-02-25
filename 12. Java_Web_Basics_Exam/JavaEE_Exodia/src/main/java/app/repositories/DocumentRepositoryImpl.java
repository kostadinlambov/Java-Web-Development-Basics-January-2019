package app.repositories;

import app.domain.entities.Document;
import app.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class DocumentRepositoryImpl implements DocumentRepository {
    private final EntityManager entityManager;

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Document save(Document entity) {
        this.entityManager.getTransaction().begin();
        try {
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();

            return entity;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public List<Document> findAll() {
        this.entityManager.getTransaction().begin();

        List<Document> documentList = this.entityManager
                .createQuery("SELECT d FROM Document AS d", Document.class)
                .getResultList();

        this.entityManager.getTransaction().commit();

        return documentList;
    }

    @Override
    public Document findById(String id) {
        this.entityManager.getTransaction().begin();
        try {
            Document document = this.entityManager
                    .createQuery("SELECT d FROM Document AS d WHERE d.id = :id", Document.class)
                    .setParameter("id", id)
                    .getSingleResult();

            this.entityManager.getTransaction().commit();
            return document;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Document update(Document entity) {
        this.entityManager.getTransaction().begin();
        try {
            Document updatedDocument = this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();

            return updatedDocument;
        }catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Long size() {
        this.entityManager.getTransaction().begin();

        Long size = this.entityManager
                .createQuery("SELECT count(d) FROM Document AS d", Long.class).getSingleResult();

        this.entityManager.getTransaction().commit();

        return size;
    }


    @Override
    public int removeDocument(String id) {
        this.entityManager.getTransaction().begin();

        int count = this.entityManager
                .createQuery("DELETE FROM Document AS d WHERE d.id = :id")
                .setParameter("id", id)
                .executeUpdate();

        this.entityManager.getTransaction().commit();
        return count;

    }
}
