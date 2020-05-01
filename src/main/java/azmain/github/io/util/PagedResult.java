package azmain.github.io.util;

import java.util.ArrayList;
import java.util.List;

public class PagedResult<T> {
    private List<T> content;
    private int pageSize;
    private int pageNumber;
    private int pageCount;

    public PagedResult() {
        this.content = new ArrayList();
        this.pageSize = 0;
        this.pageNumber = 0;
        this.pageCount = 0;
    }

    public PagedResult(List<T> content, int pageSize, int pageNumber, int pageCount) {
        this.content = content;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.pageCount = pageCount;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public static final <X> PagedResult<X> EmptyPage(){
        return new PagedResult<>();
    }
}
