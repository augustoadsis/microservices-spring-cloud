package com.microservices.core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {
    private int page;
    private int totalPages;
    private Long totalElements;
    private boolean firstPage;
    private boolean lastPage;
    private boolean isOrdered;
    private int numberOfElements;
    private int size;
    private boolean isNull;

    public PageInfo(Page page) {
        this(page.getNumber(), page.getTotalPages(), page.getTotalElements(), page.isFirst(), page.isLast(), page.getSort().isSorted(), page.getNumberOfElements(), page.getSize(), page.isEmpty());
    }
}
