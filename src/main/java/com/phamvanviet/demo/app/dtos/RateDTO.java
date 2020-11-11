package com.phamvanviet.demo.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class RateDTO {
    @JsonProperty("ratePoint")
    @NotBlank
    private int ratePoint;

    @JsonProperty("content")
    private String content;

    @JsonProperty("productId")
    private Long productId;


}
