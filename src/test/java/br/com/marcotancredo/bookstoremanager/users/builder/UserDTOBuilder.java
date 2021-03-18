package br.com.marcotancredo.bookstoremanager.users.builder;

import br.com.marcotancredo.bookstoremanager.model.users.dto.UserDTO;
import br.com.marcotancredo.bookstoremanager.model.users.enums.Gender;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public class UserDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Marco Antonio Tancredo";

    @Builder.Default
    private int age = 33;

    @Builder.Default
    private Gender gender = Gender.MALE;

    @Builder.Default
    private String email = "marcotancredo@gmail.com";

    @Builder.Default
    private String userName = "marcotancredo";

    @Builder.Default
    private String password = "123456";

    @Builder.Default
    private LocalDate birthDate = LocalDate.of(1988, 1, 30);

    public UserDTO builderUserDTO(){
        return new UserDTO(
                id,
                name,
                age,
                gender,
                email,
                userName,
                password,
                birthDate
        );
    }
}
