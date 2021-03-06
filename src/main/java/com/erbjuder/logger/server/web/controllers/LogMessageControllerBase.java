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

import com.erbjuder.logger.server.common.helper.DataBaseSearchController;
import com.erbjuder.logger.server.common.helper.FreeTextSearchController;
import com.erbjuder.logger.server.common.services.LogMessageQueries;
import com.erbjuder.logger.server.common.services.ResultSetConverter;
import com.erbjuder.logger.server.entity.impl.LogMessage;
import com.erbjuder.logger.server.entity.interfaces.LogMessageData;
import com.erbjuder.logger.server.facade.interfaces.LogMessageFacade;
import com.erbjuder.logger.server.web.helper.CommonWebUtil;
import com.erbjuder.logger.server.web.helper.PaginationHelper;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Stefan Andersson
 */
public abstract class LogMessageControllerBase extends ControllerBase implements Serializable {

    private static final int pageSize = 50;
    private static final int subtractNumberOfHour = -2;
    //
    // Search
    private boolean render_response_done = false;
    private boolean logMsgDetailView = false;
    private Date fromDate;
    private Date toDate;
    //private String freeText;
    private String applicationName;
    private String flowName;
    private String flowPointName;
    private String transactionReferenceId;
    private int selectedOption = 1; // 1=all 2=error, 3=Success 
    private FreeTextSearchController freeTextSearch = new FreeTextSearchController();
    private DataBaseSearchController dataBaseSearchController = new DataBaseSearchController();

    //
    // 
    private int selectedItemIndex;
    private LogMessage current;

    @Override
    public abstract String getReturnPage();

    @Override
    public abstract Logger getLogger();

    public abstract LogMessageFacade getLogMessageFacade();

    public LogMessageControllerBase() {
        super();
        init();
    }

    private void init() {
        dataBaseSearchController.setTreatAsSelectedDatabases(getDefaultSearchableDatabases());
    }

    public LogMessage getCurrent() {
        return current;
    }

    public void setCurrent(LogMessage current) {
        this.current = current;
    }

    @Override
    public DataModel getItems() {
//        this.getLogger().log(Level.SEVERE, "getItems() 'items == null'=[" + (items == null) + "]");

        if (items == null) {

            items = getPagination().createPageDataModel();

        }
        return items;
    }

    @Override
    public PaginationHelper getPagination() {

//       this.getLogger().log(Level.SEVERE, "getPagination() 'pagination == null'=[" + (pagination == null) + "]");
//       this.getLogger().log(Level.SEVERE, "getPagination() 'pagination == null'=[" + (pagination == null) + "]");
//         PhaseId phaseId = FacesContext.getCurrentInstance().getCurrentPhaseId();
/*         if (PhaseId.ANY_PHASE.equals(phaseId)) {
         } else if (PhaseId.ANY_PHASE.equals(phaseId)) {
         this.getLogger().log(Level.SEVERE, "ANY_PHASE");
         }
         if (PhaseId.APPLY_REQUEST_VALUES.equals(phaseId)) {
         this.getLogger().log(Level.SEVERE, "APPLY_REQUEST_VALUES");
         }
         if (PhaseId.INVOKE_APPLICATION.equals(phaseId)) {
         this.getLogger().log(Level.SEVERE, "INVOKE_APPLICATION");
         }
         if (PhaseId.INVOKE_APPLICATION.equals(phaseId)) {
         this.getLogger().log(Level.SEVERE, "INVOKE_APPLICATION");
         }
         if (PhaseId.RENDER_RESPONSE.equals(phaseId)) {
         this.getLogger().log(Level.SEVERE, "RENDER_RESPONSE");
         }
         if (PhaseId.RESTORE_VIEW.equals(phaseId)) {
         this.getLogger().log(Level.SEVERE, "RESTORE_VIEW");
         }*/
        PhaseId phaseId = FacesContext.getCurrentInstance().getCurrentPhaseId();
        if (pagination == null && PhaseId.RENDER_RESPONSE.equals(phaseId)) {

            pagination = new PaginationHelper(pageSize) {

                @Override
                public DataModel createPageDataModel() {

                    //getLogger().log(Level.SEVERE, "createPageDataModel()");
                    Integer viewError = null;
                    if (selectedOption == 2) {
                        viewError = 1;
                    } else if (selectedOption == 3) {
                        viewError = 0;
                    }

                    LogMessageQueries logMessageQueries = new LogMessageQueries();
                    ResultSetConverter converter = new ResultSetConverter();

                    String inFromDate = new java.sql.Timestamp(fromDate.getTime()).toString();
                    String inToDate = new java.sql.Timestamp(toDate.getTime()).toString();
                    Integer page = getPageNumber();
                    Integer pageSize = getPageSize();
                    List<String> viewApplicationNames = new ArrayList<>();

                    if (getSearchInApplicationName() != null && !getSearchInApplicationName().isEmpty()) {
                        viewApplicationNames.add(getSearchInApplicationName());
                    }
                    List<String> viewFlowNames = new ArrayList<>();
                    List<String> viewFlowPointName = new ArrayList<>();
                    List<String> notViewApplicationNames = new ArrayList<>();
                    List<String> notViewFlowNames = new ArrayList<>();
                    List<String> notViewFlowPointName = new ArrayList<>();
                    List<String> freeTextSearchList = freeTextSearch.getValidQueryList();
                    List<String> dataBaseSearchList = dataBaseSearchController.getDataBaseSelectedList();
                    ListDataModel list = new ListDataModel();
  
                    try {
                        list = new ListDataModel(converter.toLogMessages(
                                logMessageQueries.fetch_logMessageList(
                                        inFromDate,
                                        inToDate,
                                        page,
                                        pageSize,
                                        transactionReferenceId,
                                        viewError,
                                        viewApplicationNames,
                                        viewFlowNames,
                                        viewFlowPointName,
                                        notViewApplicationNames,
                                        notViewFlowNames,
                                        notViewFlowPointName,
                                        freeTextSearchList,
                                        dataBaseSearchList
                                )));



                    } catch (Exception ex) {
                        Logger.getLogger(LogMessageControllerBase.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return list;

                }
            };
        }
        return pagination;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public String getLogPointName() {
        return flowPointName;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setTransactionReferenceId(String TransactionReferenceId) {
        this.transactionReferenceId = TransactionReferenceId;
    }

    public String getTransactionReferenceId() {
        return transactionReferenceId;
    }

    public String hideLogDataView() {
        this.getLogger().log(Level.SEVERE, "hideLogDataView()");
        current = null;
        selectedItemIndex = -1;
        return getReturnPage();
    }

    public String prepareLogDataView() {
        this.getLogger().log(Level.SEVERE, "prepareLogDataView()");
        current = (LogMessage) getItems().getRowData();
//        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return getReturnPage();
    }

    public String prepareLogView(LogMessage item) {
//        this.getLogger().log(Level.SEVERE, "prepareLogView(LogMessage item)");

        this.setSearchInTransactionReferenceId(item.getTransactionReferenceID());
        this.current = item;
        this.items = null;
        this.pagination = null;
        this.logMsgDetailView = false;
        this.render_response_done = false;
        this.search();
        return getReturnPage();
    }

    public String prepareLogMsgDetailView() {
//        this.getLogger().log(Level.SEVERE, "prepareLogMsgDetailView()");
        this.logMsgDetailView = true;
        return getReturnPage();
    }

    public boolean isSelected(long entityId) {
        return current != null && current.getId().equals(entityId);
    }

    public void setSelected(LogMessage item) {
        this.current = item;
    }

    public LogMessage getSelected() {
        return this.current;
    }

    public void setLogMessageData(List<LogMessageData> data) {
    }

    public List<LogMessageData> getLogMessageData() {

        LogMessageQueries logMessageQueries = new LogMessageQueries();
        ResultSetConverter converter = new ResultSetConverter();
        List<LogMessageData> logMessageData = new ArrayList<LogMessageData>();

        try {
            PhaseId phaseId = FacesContext.getCurrentInstance().getCurrentPhaseId();

            if (!logMsgDetailView && PhaseId.RENDER_RESPONSE.equals(phaseId) && !render_response_done) {
                logMessageData = converter.toLogMessageData(logMessageQueries.fetch_LogMessageData(
                        current.getId().toString(),
                        current.getPartitionId(),
                        dataBaseSearchController.getDataBaseSelectedList()));
            }

        } catch (Exception ex) {
            this.getLogger().log(Level.SEVERE, null, ex);
        }

        return logMessageData;
    }

    private Date getDefaultSearchFromDate() {

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.add(Calendar.HOUR, subtractNumberOfHour);
        return CommonWebUtil.convertLocalDateToDateTimezone(calendar.getTime(), "UTC");
    }

    private Date getDefaultSearchToDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);

        return CommonWebUtil.convertLocalDateToDateTimezone(calendar.getTime(), "UTC");
    }

    public String getSearchFromDate() {
//        System.err.println("GetSearchFromDate called");

        if (this.fromDate == null) {
            this.fromDate = this.getDefaultSearchFromDate();
        }

        SimpleDateFormat format = getDateFormater();
        return format.format(this.fromDate).toString();

    }

    public void setSearchFromDate(String fromDate) {
//        System.err.println("SetSearchFromDate called");

        SimpleDateFormat format = getDateFormater();
        try {

            this.fromDate = format.parse(fromDate);

        } catch (ParseException e) {
            // Add output message
            this.fromDate = null;
        }
    }

    public String getSearchToDate() {
//        System.err.println("GetSearchToDate called");

        if (this.toDate == null) {
            this.toDate = this.getDefaultSearchToDate();
        }

        SimpleDateFormat format = getDateFormater();
        return format.format(this.toDate).toString();
    }

    public void setSearchToDate(String toDate) {
//        System.err.println("SetSearchToDate called");

        SimpleDateFormat format = getDateFormater();
        try {

            this.toDate = format.parse(toDate);

        } catch (ParseException e) {
            // Add output message 
            this.fromDate = null;
        }

    }

    public String getSearchInTransactionReferenceId() {
        return this.transactionReferenceId;
    }

    public void setSearchInTransactionReferenceId(String transactionReferenceId) {
        this.transactionReferenceId = transactionReferenceId;
    }

    public String getSearchInApplicationName() {
        return this.applicationName;
    }

    public void setSearchInApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public List<String> completeApplicationName(String startsWithName) {
        List<String> results = new ArrayList<>();

        LogMessageQueries logMessageQueries = new LogMessageQueries();
        ResultSetConverter converter = new ResultSetConverter();
        List<LogMessage> logMessages;
        List<String> applicationNames = new ArrayList<>();
        applicationNames.add(startsWithName);
        String inFromDate = new java.sql.Timestamp(fromDate.getTime()).toString();
        String inToDate = new java.sql.Timestamp(toDate.getTime()).toString();

        try {
//            PhaseId phaseId = FacesContext.getCurrentInstance().getCurrentPhaseId();
//
//            if (!logMsgDetailView && PhaseId.RENDER_RESPONSE.equals(phaseId) && !render_response_done) {

            logMessages = converter.toLogMessages(logMessageQueries.fetch_ApplicationNames(
                    inFromDate,
                    inToDate,
                    applicationNames));

            for (LogMessage logMessage : logMessages) {
                results.add(logMessage.getApplicationName());
            }

//            }
        } catch (Exception ex) {
            this.getLogger().log(Level.SEVERE, null, ex);
        }

        return results;
    }

    public String getSearchInFlowName() {
        return this.flowName;
    }

    public void setSearchInFlowName(String flowName) {
        this.flowName = flowName;
    }

    public List<String> completeFlowName(String startsWithName) {
        List<String> results = new ArrayList<>();

        LogMessageQueries logMessageQueries = new LogMessageQueries();
        ResultSetConverter converter = new ResultSetConverter();
        List<String> flowNames = new ArrayList<>();
        flowNames.add(startsWithName);
        String inFromDate = new java.sql.Timestamp(fromDate.getTime()).toString();
        String inToDate = new java.sql.Timestamp(toDate.getTime()).toString();

        try {
            results = converter.toStringList(logMessageQueries.fetch_FlowNames(
                    inFromDate,
                    inToDate,
                    flowNames));
        } catch (Exception ex) {
            this.getLogger().log(Level.SEVERE, null, ex);
        }

        return results;
    }

    public String getSearchInFlowPointName() {
        return this.flowPointName;
    }

    public void setSearchInFlowPointName(String flowPointName) {
        this.flowPointName = flowPointName;
    }

    public List<String> completeFlowPointName(String startsWithName) {
        List<String> results = new ArrayList<>();

        LogMessageQueries logMessageQueries = new LogMessageQueries();
        ResultSetConverter converter = new ResultSetConverter();
        List<String> flowPointNames = new ArrayList<>();
        flowPointNames.add(startsWithName);
        String inFromDate = new java.sql.Timestamp(fromDate.getTime()).toString();
        String inToDate = new java.sql.Timestamp(toDate.getTime()).toString();

        try {

            results = converter.toStringList(logMessageQueries.fetch_FlowPointNames(
                    inFromDate,
                    inToDate,
                    flowPointNames));

        } catch (Exception ex) {
            this.getLogger().log(Level.SEVERE, null, ex);
        }

        return results;
    }

    public FreeTextSearchController getFreeTextSearch() {
        return freeTextSearch;
    }

    public void setFreeTextSearch(FreeTextSearchController freeTextSearch) {
        this.freeTextSearch = freeTextSearch;
    }

    public DataBaseSearchController getDataBaseSearchController() {
        return dataBaseSearchController;
    }

    public void setDataBaseSearchController(DataBaseSearchController dataBaseSearchController) {
        this.dataBaseSearchController = dataBaseSearchController;
    }

    public void clearSearchFields() {

        flowName = "";
        flowPointName = "";
        applicationName = "";
        transactionReferenceId = "";
        this.freeTextSearch = new FreeTextSearchController();
        this.search();
    }

    public Integer getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(Integer selectedOption) {
        this.selectedOption = selectedOption;
        this.search();
    }

    public void allItems() {
        this.selectedOption = 1;
        this.search();
    }

    public void errorItems() {
        this.selectedOption = 2;
        this.search();
    }

    public void successItems() {
        this.selectedOption = 3;
        this.search();
    }

    public void search() {

        current = null;
        logMsgDetailView = false;
        render_response_done = false;
        recreateModel();
    }

}
