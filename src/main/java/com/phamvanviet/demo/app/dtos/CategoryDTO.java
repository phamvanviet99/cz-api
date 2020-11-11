package com.phamvanviet.demo.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDTO {
    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty("parentId")
    private Integer parentId;

    @JsonProperty("level")
    @NotBlank
    private Integer level;

    @JsonProperty("children")
    private List<CategoryDTO> children = new ArrayList<>();

    @JsonProperty("product")
    private List<ProductDTO> products;


}
