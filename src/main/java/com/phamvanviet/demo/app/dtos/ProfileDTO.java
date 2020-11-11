package com.phamvanviet.demo.app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ProfileDTO {

    @JsonIgnore
    private Long Id;

    @Length(min = 1, max = 8)
    private String name;

    private String address;

    private String phoneNumber;

    private String gender;



}
