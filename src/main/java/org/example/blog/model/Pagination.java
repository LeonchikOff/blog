package org.example.blog.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
    private final String previousUrl;
    private final String nextUrl;
    private final List<PageItem> pages;

    private Pagination(String previousUrl, String nextUrl, List<PageItem> pages) {
        this.previousUrl = previousUrl;
        this.nextUrl = nextUrl;
        this.pages = pages;
    }

    public boolean isActivePreviousPage() {
        return previousUrl != null;
    }

    public boolean isActiveNextPage() {
        return nextUrl != null;
    }

    public String getPreviousUrl() {
        return previousUrl;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public List<PageItem> getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }


    public static final class PageItem {
        private final String pageUrl;
        private final String captionOfNumberPage;

        private PageItem(String pageUrl, String captionOfNumberPage) {
            this.pageUrl = pageUrl;
            this.captionOfNumberPage = captionOfNumberPage;
        }

        private static PageItem createPageItem(String pageUrl, String captionOfNumberPage) {
            return new PageItem(pageUrl, captionOfNumberPage);
        }

        private static PageItem createCurrent(String captionOfNumberPage) {
            return new PageItem(null, captionOfNumberPage);
        }

        private static PageItem createEllipsis() {
            return new PageItem(null, null);
        }

        public String getPageUrl() {
            return pageUrl;
        }

        public String getCaptionOfNumberPage() {
            return captionOfNumberPage;
        }
    }

    public static class Builder {
        private static final int DEFAULT_LIMIT_ITEMS_PER_PAGE = 10;
        private static final int DEFAULT_MAX_PAGINATION_BUTTONS_PER_PAGE = 9;

        private final String basePageUrl;
        private final int totalCountItems;
        private final int offset;
        private int limit;
        private int maxPaginationButtonsPerPage;

        public Builder(String basePageUrl, int offset, int totalCountItems) {
            this.basePageUrl = basePageUrl;
            this.totalCountItems = totalCountItems;
            this.offset = offset;
            this.limit = DEFAULT_LIMIT_ITEMS_PER_PAGE;
            this.maxPaginationButtonsPerPage = DEFAULT_MAX_PAGINATION_BUTTONS_PER_PAGE;
        }

        public Builder builderWithCustomLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder builderWithCustomMaxPaginationButtonsPerPage(int maxPaginationButtonsPerPage) {
            this.maxPaginationButtonsPerPage = maxPaginationButtonsPerPage;
            return this;
        }

        public Pagination build() {
            if (totalCountItems <= limit) {
                return null;
            }
            int currentPage = offset / limit + 1;
            String previousUrlPage = getPreviousUrlPage(currentPage);
            String nextUrlPage = getNextUrlPage(currentPage);
            int maxPage = getMaxPage();
            List<PageItem> pages;
            if (maxPage <= maxPaginationButtonsPerPage) {
                pages = createButtonsOnly(currentPage, maxPage);
            } else {
                int borderValue = (maxPaginationButtonsPerPage - 1) / 2;
                if (currentPage < (maxPaginationButtonsPerPage - borderValue)) {
                    pages = createButtonsWithLastPageOnly(currentPage, maxPage);
                } else if (currentPage > maxPage - (maxPaginationButtonsPerPage - borderValue)) {
                    pages = createButtonsWithFirstPageOnly(currentPage, maxPage);
                } else {
                    pages = createButtonsWithMiddlePages(currentPage, maxPage);
                }
            }
            return new Pagination(previousUrlPage, nextUrlPage, pages);

        }

        private List<PageItem> createButtonsWithMiddlePages(int currentPage, int maxPage) {
            int shiftValue = (maxPaginationButtonsPerPage - 5) / 2;
            List<PageItem> pages = new ArrayList<>();
            pages.add(PageItem.createPageItem(createUrlForPage(1), "1"));
            pages.add(PageItem.createEllipsis());
            pages.addAll(createPageItems(currentPage, currentPage - shiftValue, currentPage + shiftValue));
            pages.add(PageItem.createEllipsis());
            pages.add(PageItem.createPageItem(createUrlForPage(maxPage), String.valueOf(maxPage)));
            return pages;
        }

        private List<PageItem> createButtonsWithFirstPageOnly(int currentPage, int maxPage) {
            List<PageItem> pages = new ArrayList<>();
            pages.add(PageItem.createPageItem(createUrlForPage(1), "1"));
            pages.add(PageItem.createEllipsis());
            pages.addAll(createPageItems(currentPage, maxPage - (maxPaginationButtonsPerPage - 3), maxPage));
            return pages;
        }

        private List<PageItem> createButtonsWithLastPageOnly(int currentPage, int maxPage) {
            List<PageItem> pages = createPageItems(currentPage, 1, (maxPaginationButtonsPerPage - 2));
            pages.add(PageItem.createEllipsis());
            pages.add(PageItem.createPageItem(createUrlForPage(maxPage), String.valueOf(maxPage)));
            return pages;
        }

        private List<PageItem> createButtonsOnly(int currentPage, int maxPage) {
            return createPageItems(currentPage, 1, maxPage);
        }

        private List<PageItem> createPageItems(int currentNumberOfPage, int minNumberOfPage, int maxNumberOfPage) {
            List<PageItem> pageItems = new ArrayList<>();
            for (int pageNumber = minNumberOfPage; pageNumber < maxNumberOfPage; pageNumber++) {
                if (currentNumberOfPage == pageNumber) {
                    pageItems.add(PageItem.createCurrent(String.valueOf(pageNumber)));
                } else {
                    pageItems.add(PageItem.createPageItem(createUrlForPage(pageNumber), String.valueOf(pageNumber)));
                }
            }
            return pageItems;
        }

        private String createUrlForPage(int pageNumber) {
            return pageNumber > 1
                    ? basePageUrl + "page=" + pageNumber
                    : basePageUrl.substring(0, basePageUrl.length() - 1);
        }

        private String getPreviousUrlPage(int currentPageNumber) {
            return currentPageNumber > 1
                    ? basePageUrl + "page=" + (currentPageNumber - 1)
                    : null;
        }

        private String getNextUrlPage(int currentPageNumber) {
            return offset + limit < totalCountItems
                    ? basePageUrl + "page=" + (currentPageNumber + 1)
                    : null;
        }

        private int getMaxPage() {
            int maxPage = totalCountItems / limit;
            if (totalCountItems % limit > 0) maxPage++;
            return maxPage;
        }
    }
}

