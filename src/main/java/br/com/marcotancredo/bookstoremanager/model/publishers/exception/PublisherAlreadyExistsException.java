package br.com.marcotancredo.bookstoremanager.model.publishers.exception;

import javax.persistence.EntityExistsException;

public class PublisherAlreadyExistsException extends EntityExistsException {
    public PublisherAlreadyExistsException(String name, String code) {
        super(String.format("Publisher with name %s or code %s already exists!", name, code));
    }
}
