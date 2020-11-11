package com.phamvanviet.demo.app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Data
public class UserDTO {
    @JsonIgnore
    private long id;

    @NotBlank
    @Length(min = 4, max = 50)
    private String userName;

    @NotBlank
    @Length(min = 6, max = 50)
    private String password;

    private String email;

    @Length(min = 1, max = 8)
    private String name;

}
