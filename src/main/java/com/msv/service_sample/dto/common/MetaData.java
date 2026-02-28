package com.msv.service_sample.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

/**
 * Pagination and result metadata attached to list responses.
 *
 * Example:
 * "meta": {
 *   "page":          0,
 *   "size":          20,
 *   "totalElements": 150,
 *   "totalPages":    8,
 *   "first":         true,
 *   "last":          false
 * }
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Pagination metadata")
public class MetaData {

    @Schema(description = "Current page number (0-based)", example = "0")
    private final Integer page;

    @Schema(description = "Page size", example = "20")
    private final Integer size;

    @Schema(description = "Total number of elements", example = "150")
    private final Long totalElements;

    @Schema(description = "Total number of pages", example = "8")
    private final Integer totalPages;

    @Schema(description = "Whether this is the first page", example = "true")
    private final Boolean first;

    @Schema(description = "Whether this is the last page", example = "false")
    private final Boolean last;

    /**
     * Build MetaData directly from a Spring Data Page.
     */
    public static MetaData of(Page<?> page) {
        return MetaData.builder()
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }
}