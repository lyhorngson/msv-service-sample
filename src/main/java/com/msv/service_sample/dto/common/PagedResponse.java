package com.msv.service_sample.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Paginated list wrapper used inside ApiResponse.data for list endpoints.
 *
 * Usage:
 *   ApiResponse.ok(
 *     PagedResponse.of(page),
 *     "Invoices retrieved",
 *     MetaData.of(page)
 *   )
 */
@Getter
@Schema(description = "Paginated list of items")
public class PagedResponse<T> {

    @Schema(description = "List of items in this page")
    private final List<T> items;

    private PagedResponse(List<T> items) {
        this.items = items;
    }

    public static <T> PagedResponse<T> of(Page<T> page) {
        return new PagedResponse<>(page.getContent());
    }

    public static <T> PagedResponse<T> of(List<T> items) {
        return new PagedResponse<>(items);
    }
}