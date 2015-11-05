/* 
 * Copyright (C) 2014 erbjuder.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.erbjuder.logger.server.web.controllers;

import java.io.Serializable;

/**
 *
 * @author Stefan Andersson
 */
public abstract class LogMessageStatisticControllerBase extends ControllerBase implements Serializable {
//
//    
//    //
//    // Search
//    private boolean render_response_done = false;
//    private Integer itemCount = null;
//    private boolean logMsgDetailView = false;
//    private Date fromDate;
//    private Date toDate;
//    //private String freeText;
//    private String applicationName;
//    private String flowName;
//    private String flowPointName;
//    private String transactionReferenceId;
//    private int selectedOption = 1; // 1=all 2=error, 3=Success 
//    private FreeTextSearchController freeTextSearch = new FreeTextSearchController();
//    private DataBaseSearchController dataBaseSearchController = new DataBaseSearchController();
//
//    //
//    // 
//    private int selectedItemIndex;
//    private LogMessage current;
//
//    @Override
//    public abstract String getReturnPage();
//
//    @Override
//    public abstract Logger getLogger();
//    
//    public abstract LogMessageFacade getLogMessageFacade();
//
//    public LogMessageStatisticControllerBase() {
//        super();
//        init();
//    }
//
//    private void init() {
//
//        dataBaseSearchController.setTreatAsSelectedDatabases(getDefaultSearchableDatabases());
//    }
//
////    public LogMessage getCurrent() {
////        return current;
////    }
////
////    public void setCurrent(LogMessage current) {
////        this.current = current;
////    }
////
////    @Override
////    public DataModel getItems() {
//////        this.getLogger().log(Level.SEVERE, "getItems() 'items == null'=[" + (items == null) + "]");
////
////        if (items == null) {
////            items = getPagination().createPageDataModel();
////
////        }
////        return items;
////    }
////
////    @Override
////    public PaginationHelper getPagination() {
////     
////        if (pagination == null) {
////
////            pagination = new PaginationHelper(50) {
////                public int maxResult = 5000;
////
////                @Override
////                public int getItemsCount() {
////
//////                    getLogger().log(Level.SEVERE, "getItemsCount()");
////                    Boolean viewError = null;
////                    if (selectedOption == 2) {
////                        viewError = true;
////                    } else if (selectedOption == 3) {
////                        viewError = false;
////                    }
////
////                    PhaseId phaseId = FacesContext.getCurrentInstance().getCurrentPhaseId();
////
////                    if (!logMsgDetailView && PhaseId.RENDER_RESPONSE.equals(phaseId) && !render_response_done) {
////
////                        //getLogger().log(Level.SEVERE, "getLogMessageFacade().count()");
////                        render_response_done = true;
////                        itemCount = getLogMessageFacade().countNumberOfTransactions(
////                                applicationName,
////                                flowName,
////                                flowPointName,
////                                transactionReferenceId,
////                                fromDate != null ? fromDate.getTime() : -1,
////                                toDate != null ? toDate.getTime() : -1,
////                                freeTextSearch,
////                                dataBaseSearchController,
////                                viewError,
////                                maxResult);
////
////                    }
////
////                    return itemCount;
////
////                }
////
////                @Override
////                public DataModel createPageDataModel() {
////
////                    //getLogger().log(Level.SEVERE, "createPageDataModel()");
////                    Boolean viewError = null;
////                    if (selectedOption == 2) {
////                        viewError = true;
////                    } else if (selectedOption == 3) {
////                        viewError = false;
////                    }
////
////                     ListDataModel list = new ListDataModel(getLogMessageFacade().numberOfTransactions(
////                            new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()},
////                            applicationName,
////                            flowName,
////                            flowPointName,
////                            transactionReferenceId,
////                            fromDate != null ? fromDate.getTime() : -1,
////                            toDate != null ? toDate.getTime() : -1,
////                            freeTextSearch,
////                            dataBaseSearchController,
////                            viewError));
////                    return list;
////
////                }
////            };
////        }
////        return pagination;
////    }
////
////    public Date getFromDate() {
////        return fromDate;
////    }
////
////    public Date getToDate() {
////        return toDate;
////    }
////
////    public String getLogPointName() {
////        return flowPointName;
////    }
////
////    public int getSelectedItemIndex() {
////        return selectedItemIndex;
////    }
////
////    public String getTransactionReferenceId() {
////        return transactionReferenceId;
////    }
////
////    public String hideLogDataView() {
//////        this.getLogger().log(Level.SEVERE, "hideLogDataView()");
////        current = null;
////        selectedItemIndex = -1;
////        return getReturnPage();
////    }
////
////    public String prepareLogDataView() {
//////        this.getLogger().log(Level.SEVERE, "prepareLogDataView()");
////        current = (LogMessage) getItems().getRowData();
////        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
////        return getReturnPage();
////    }
////
////    public String prepareLogView(LogMessage item) {
//////        this.getLogger().log(Level.SEVERE, "prepareLogView(LogMessage item)");
////
////        this.setSearchInTransactionReferenceId(item.getTransactionReferenceID());
////        this.items = null;
////        this.pagination = null;
////        this.selectedItemIndex = -1;
////        this.logMsgDetailView = false;
////        this.render_response_done = false;
////        return getReturnPage();
////    }
////
////    public String prepareLogMsgDetailView() {
////        this.logMsgDetailView = true;
////        return getReturnPage();
////    }
////
////    public boolean isSelected(long entityId) {
////        return current != null && current.getId().equals(entityId);
////    }
////
////    public void setSelected(LogMessage item) {
////        this.current = item;
////    }
////
////    public LogMessage getSelected() {
////        return this.current;
////    }
////
////    private Date getDefaultSearchFromDate() {
////
////        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
////        calendar.set(Calendar.HOUR_OF_DAY, 0);
////        calendar.set(Calendar.MINUTE, 0);
////        calendar.set(Calendar.SECOND, 0);
////        calendar.set(Calendar.MILLISECOND, 0);
////        return CommonWebUtil.convertLocalDateToDateTimezone(calendar.getTime(), "UTC");
////    }
////
////    private Date getDefaultSearchToDate() {
////        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
////        calendar.set(Calendar.HOUR_OF_DAY, 23);
////        calendar.set(Calendar.MINUTE, 59);
////        calendar.set(Calendar.SECOND, 59);
////        calendar.set(Calendar.MILLISECOND, 0);
////
////        return CommonWebUtil.convertLocalDateToDateTimezone(calendar.getTime(), "UTC");
////    }
////
////    public String getSearchFromDate() {
//////        System.err.println("GetSearchFromDate called");
////
////        if (this.fromDate == null) {
////            this.fromDate = this.getDefaultSearchFromDate();
////        }
////
////        SimpleDateFormat format = getDateFormater();
////        return format.format(this.fromDate).toString();
////
////    }
////
////    public void setSearchFromDate(String fromDate) {
//////        System.err.println("SetSearchFromDate called");
////
////        SimpleDateFormat format = getDateFormater();
////        try {
////
////            this.fromDate = format.parse(fromDate);
////
////        } catch (Exception e) {
////            // Add output message
////            this.fromDate = null;
////        }
////    }
////
////    public String getSearchToDate() {
//////        System.err.println("GetSearchToDate called");
////
////        if (this.toDate == null) {
////            this.toDate = this.getDefaultSearchToDate();
////        }
////
////        SimpleDateFormat format = getDateFormater();
////        return format.format(this.toDate).toString();
////    }
////
////    public void setSearchToDate(String toDate) {
//////        System.err.println("SetSearchToDate called");
////
////        SimpleDateFormat format = getDateFormater();
////        try {
////
////            this.toDate = format.parse(toDate);
////
////        } catch (Exception e) {
////            // Add output message 
////            this.fromDate = null;
////        }
////
////    }
////
////    public String getSearchInTransactionReferenceId() {
////        return this.transactionReferenceId;
////    }
////
////    public void setSearchInTransactionReferenceId(String transactionReferenceId) {
////        this.transactionReferenceId = transactionReferenceId;
////    }
////
////    public String getSearchInApplicationName() {
////        return this.applicationName;
////    }
////
////    public void setSearchInApplicationName(String applicationName) {
////        this.applicationName = applicationName;
////    }
////
////    public List<String> completeApplicationName(String startsWithName) {
////        return getLogMessageFacade().getApplicationNames(fromDate.getTime(), toDate.getTime(), startsWithName);
////    }
////
////    public String getSearchInFlowName() {
////        return this.flowName;
////    }
////
////    public void setSearchInFlowName(String flowName) {
////        this.flowName = flowName;
////    }
////
////    public List<String> completeFlowName(String startsWithName) {
////        return getLogMessageFacade().getFlowNames(fromDate.getTime(), toDate.getTime(), startsWithName);
////    }
////
////    public String getSearchInFlowPointName() {
////        return this.flowPointName;
////    }
////
////    public void setSearchInFlowPointName(String flowPointName) {
////        this.flowPointName = flowPointName;
////    }
////
////    public List<String> completeFlowPointName(String startsWithName) {
////        return getLogMessageFacade().getFlowPointNames(fromDate.getTime(), toDate.getTime(), startsWithName);
////    }
////
////    public FreeTextSearchController getFreeTextSearch() {
////        return freeTextSearch;
////    }
////
////    public void setFreeTextSearch(FreeTextSearchController freeTextSearch) {
////        this.freeTextSearch = freeTextSearch;
////    }
////
////    public DataBaseSearchController getDataBaseSearchController() {
////        return dataBaseSearchController;
////    }
////
////    public void setDataBaseSearchController(DataBaseSearchController dataBaseSearchController) {
////        this.dataBaseSearchController = dataBaseSearchController;
////    }
////
////    public void clearSearchFields() {
////        applicationName = "";
////        flowName = "";
////        flowPointName = "";
////        transactionReferenceId = "";
////        this.freeTextSearch = new FreeTextSearchController();
////        this.search();
////    }
////
////    public Integer getSelectedOption() {
////        return selectedOption;
////    }
////
////    public void setSelectedOption(Integer selectedOption) {
////        this.selectedOption = selectedOption;
////        this.items = null;
////        this.pagination = null;
////    }
////
////    public void allItems() {
////        this.selectedOption = 1;
////        this.search();
////    }
////
////    public void errorItems() {
////        this.selectedOption = 2;
////        this.search();
////    }
////
////    public void successItems() {
////        this.selectedOption = 3;
////        this.search();
////    }
////
////    public void search() {
//////        this.getLogger().log(Level.SEVERE, "search()");
////        logMsgDetailView = false;
////        render_response_done = false;
////        recreateModel();
////    }

}
