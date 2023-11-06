package com.tra22.spring.redis.dto.book;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateBookDto implements Serializable {
    private long id;
    private String title;
    private String description;
    private boolean published;
}
