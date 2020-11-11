package com.phamvanviet.demo.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class AttributeDTO {
    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty("categoryId")
    private Integer categoryId;

}
