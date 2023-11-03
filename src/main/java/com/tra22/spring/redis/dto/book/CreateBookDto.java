package com.tra22.spring.redis.dto.book;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateBookDto implements Serializable {
    private String title;
    private String description;
}
