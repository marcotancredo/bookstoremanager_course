package br.com.marcotancredo.bookstoremanager.model.users.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ADMIN("Admin"),
    USER("User");

    private final String description;
}
