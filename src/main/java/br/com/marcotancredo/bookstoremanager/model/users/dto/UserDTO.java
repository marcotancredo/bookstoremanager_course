package br.com.marcotancredo.bookstoremanager.model.users.dto;

import br.com.marcotancredo.bookstoremanager.model.users.enums.Gender;
import br.com.marcotancredo.bookstoremanager.model.users.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String name;

    @NotNull
    @Max(120)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @NotNull
    private Role role;
}
