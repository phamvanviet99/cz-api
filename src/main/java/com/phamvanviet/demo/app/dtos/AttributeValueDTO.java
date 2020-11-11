package com.phamvanviet.demo.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Data
public class AttributeValueDTO {
    @NotBlank
    private String value;

    @JsonProperty("attributeId")
    private Integer attributeId;
}
