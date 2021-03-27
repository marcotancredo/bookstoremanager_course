package br.com.marcotancredo.bookstoremanager.model.users.utils;

import br.com.marcotancredo.bookstoremanager.model.users.dto.MessageDTO;
import br.com.marcotancredo.bookstoremanager.model.users.entity.User;

public class MessageDTOUtils {

    public static MessageDTO creationMessage(User createdUser) {
        return returnMessage(createdUser, "created");
    }

    public static MessageDTO updatedMessage(User updatedUser) {

        return returnMessage(updatedUser, "updated");
    }

    private static MessageDTO returnMessage(User user, String action) {
        String userName = user.getUsername();
        Long userId = user.getId();

        String createdUserMessage = String.format("User %s with ID %s successfully %s!", userName, userId, action);

        return MessageDTO.builder()
                .message(createdUserMessage)
                .build();
    }
}
