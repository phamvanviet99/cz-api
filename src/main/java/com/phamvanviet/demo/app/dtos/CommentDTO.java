package com.phamvanviet.demo.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class CommentDTO {
    @JsonProperty("content")
    @NotBlank
    private String content;

    @JsonProperty("productId")
    private Long productId;


}
