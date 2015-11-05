package com.erbjuder.logger.server.web.helper;

import javax.faces.model.DataModel;

public abstract class PaginationHelper {

    private int pageSize;
    private int page = 1;
    
    public PaginationHelper(int pageSize) {
        this.pageSize = pageSize;
    }

    public abstract DataModel createPageDataModel();

    public int getPageNumber() {
        return page;
    }
    public int getPageFirstItem() {
        return page * pageSize;
    }
 
    public void nextPage() {
            page++;
    }

    public boolean isHasPreviousPage() {
        return page > 0;
    }

    public void previousPage() {
            page--;
    }

    public int getPageSize() {
        return pageSize;
    }
    
    
}
