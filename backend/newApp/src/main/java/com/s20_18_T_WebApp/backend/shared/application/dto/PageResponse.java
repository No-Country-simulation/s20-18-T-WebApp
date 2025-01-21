package com.s20_18_T_WebApp.backend.shared.application.dto;


//import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;

public class PageResponse<T> {

    private final List<T> content;

    private final PaginationMetadata pagination;

    public PageResponse(
            List<T> content,

            PaginationMetadata pagination
    ) {
        this.content = content;
        this.pagination = pagination;
    }


    /**
     * Converts a Page object to a PageResponse object.
     *
     * @param page The Page object containing the content and pagination information.
     * @param <T>  The type of elements in the page content.
     * @return A PageResponse object containing the content and pagination metadata.
     */
    public static <T> PageResponse<T> from(Page<T> page) {
        // Extract the content from the Page object
        List<T> content = page.getContent();

        // Create PaginationMetadata using the Page object's pagination details
        PaginationMetadata pagination = new PaginationMetadata(
                page.getNumber(),     // Current page number
                page.getSize(),       // Size of the page
                page.getTotalElements(), // Total number of elements
                page.getTotalPages(), // Total number of pages
                page.isFirst(),       // Is this the first page?
                page.isLast()         // Is this the last page?
        );

        // Return a new PageResponse object with content and pagination metadata
        return new PageResponse<>(content, pagination);
    }

    /**
     * Retrieves the content of the current page.
     *
     * @return A list of elements of type T representing the content.
     */
    public List<T> getContent() {
        return content;
    }

    /**
     * Retrieves the pagination metadata of the current page.
     *
     * @return An object containing the pagination metadata.
     */
    public PaginationMetadata getPagination() {
        return pagination;
    }

    /**
     * Compares the given object with this PageResponse object for equality.
     * <p>
     * Two PageResponse objects are considered equal if and only if they have the same content and pagination metadata.
     *
     * @param obj The object to compare with this PageResponse object.
     * @return true if the given object is equal to this PageResponse object, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PageResponse) obj;
        return Objects.equals(this.content, that.content) &&
                Objects.equals(this.pagination, that.pagination);
    }

    /**
     * Generates a hash code for this PageResponse object.
     * <p>
     * The hash code is generated based on the content and pagination metadata of the page.
     *
     * @return An integer hash code representing this PageResponse object.
     */
    @Override
    public int hashCode() {
        // The hash code is generated based on the content and pagination metadata of the page.
        return Objects.hash(content, pagination);
    }

    @Override
    public String toString() {
        return "PageResponse[" +
                "content=" + content + ", " +
                "pagination=" + pagination + ']';
    }

    public record PaginationMetadata(
            int pageNumber,
            int pageSize,
            long totalElements,
            int totalPages,
            boolean first,
            boolean last
    ) {
    }
}
