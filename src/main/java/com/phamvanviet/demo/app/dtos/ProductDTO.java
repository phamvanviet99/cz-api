package com.phamvanviet.demo.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Data
public class ProductDTO {

    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty("unitPrice")
    private Long unitPrice;

    @JsonProperty("state")
    private String state;

    @JsonProperty("rate")
    @Length(max = 5, message = "Rate cannot exceed 5 stars ")
    private double rate;

    @JsonProperty("discount")
    @Length(max = 100, message = "Discount cannot exceed 100%")
    private double discount;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("shortDescription")
    @Length(max = 150, message = "Short description cannot exceed 100 characters")
    private String shortDescription;

    @JsonProperty("description")
    @Length(max = 255, message = "Description cannot exceed 1000 characters")
    private String description;

    @JsonProperty("categoryId")
    private Integer categoryId;

    @JsonProperty("attributeValues")
    private List<Integer> attributeValues;

}
