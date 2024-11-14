package com.mariaclara.spring_security.entities.dtos;

import org.springframework.data.domain.Page;

public record FeedDTO (Page<FeedItemDTO> tweeList,
                        int page,
                        int pageSize,
                        int totalPages,
                        long totalElements) {
}
