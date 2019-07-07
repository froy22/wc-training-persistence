package com.whitecloak.training.common.response;

import java.util.List;

public class PaginatedResource<T> {

    private final List<T> items;
    private final long totalPages;
    private final long totalItems;

    public PaginatedResource(List<T> items) {
        this.items = items;
        this.totalPages = 1;
        this.totalItems = items.size();
    }

    public PaginatedResource(List<T> items, long totalPages, long totalItems) {
        this.items = items;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    public List<T> getItems() {
        return items;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }
}
