package com.phamvanviet.demo.app.responses;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private String name;

    private int level;

    private List<CategoryResponse> children;

    private List<ProductResponse> products;
}
