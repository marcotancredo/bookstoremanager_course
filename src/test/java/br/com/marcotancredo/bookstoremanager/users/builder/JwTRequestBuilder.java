package br.com.marcotancredo.bookstoremanager.users.builder;

import br.com.marcotancredo.bookstoremanager.model.users.dto.JwtRequest;
import lombok.Builder;

@Builder
public class JwTRequestBuilder {

    @Builder.Default
    private String username = "marcotancredo";

    @Builder.Default
    private String password = "123456";

    public JwtRequest buildJwtRequest(){
        return new JwtRequest(username, password);
    }
}
