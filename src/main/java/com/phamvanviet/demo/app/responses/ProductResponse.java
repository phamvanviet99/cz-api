package com.phamvanviet.demo.app.responses;

import com.phamvanviet.demo.app.dtos.AttributeValueDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private String name;

    private Long unitPrice;

    private String state;

    private double rate;

    private double discount;

    private int quantity;

    private String shortDescription;

    private String description;

    private List<AttributeValueResponse> attributeValues;

}
