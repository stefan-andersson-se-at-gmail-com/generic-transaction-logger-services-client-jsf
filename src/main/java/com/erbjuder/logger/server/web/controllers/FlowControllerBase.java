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
public abstract class FlowControllerBase extends ControllerBase implements Serializable {

//    //
//    // Search
//    private Date fromDate;
//    private Date toDate;
//    private String freeText;
//    private String applicationName;
//    private String flowName;
//    private String flowPointName;
//    private String transactionReferenceId;
//    private int selectedOption = 1; // 1=all 2=error, 3=Success 
//    private DataBaseSearchController dataBaseSearchController = new DataBaseSearchController();
//
////
//    // 
//    private LogMessage current;
//
//    public abstract LogMessageFacade getLogMessageFacade();
//
//    public abstract ApplicationFlowConfigurationFacade getFlowConfigurationFacade();
//
//    public FlowControllerBase() {
//        super();
//        init();
//    }
//
//    private void init() {
//        dataBaseSearchController.setTreatAsSelectedDatabases(getDefaultSearchableDatabases());
//    }
//
//    @Override
//    public DataModel getItems() {
//        if (items == null) { //&& FacesContext.getCurrentInstance().getRenderResponse()) {
//            items = getPagination().createPageDataModel();
//        }
//        return items;
//    }
//
//    @Override
//    public PaginationHelper getPagination() {
//        if (pagination == null) { //&& FacesContext.getCurrentInstance().getRenderResponse()) {
//
//            pagination = new PaginationHelper(10) {
//                @Override
//                public int getItemsCount() {
//                    return getLogMessageFacade().countFlowAndTransactionId();
//                }
//
//                @Override
//                public DataModel createPageDataModel() {
//
//                    //
//                    // Wrapped Data
//                    List<TransactionFlowWrapper> dataList = new ArrayList<TransactionFlowWrapper>();
//
//                    //
//                    // Filter the list
//                    List<FlowAndTransactionId> flowAndTransactionIdList = getLogMessageFacade().getFlowAndTransactionId(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()});
//                    if (flowAndTransactionIdList.isEmpty()) {
//                        return new ListDataModel(dataList);
//                    }
//
//                    // 
//                    // Get real data
//                    List<LogMessage> logMessages = getLogMessageFacade().getFlowLogMessages(flowAndTransactionIdList);
//                    if (logMessages.isEmpty()) {
//                        return new ListDataModel(dataList);
//                    }
//
//                    Set<String> flowNames = new HashSet<String>();
//                    for (FlowAndTransactionId flowAndTransactionId : flowAndTransactionIdList) {
//                        flowNames.add(flowAndTransactionId.getFlowName());
//                    }
//
//                    List<ApplicationFlowConfiguration> flowConfigurations = getFlowConfigurationFacade().findFlowConfigurations(flowNames);
//                    if (flowConfigurations.isEmpty()) {
//                        return new ListDataModel(dataList);
//                    }
//
//                    // 
//                    // Create lookup tabeles
//                    Map<String, ArrayList<LogMessage>> logMessageMap = new HashMap<String, ArrayList<LogMessage>>();
//                    for (LogMessage entity : logMessages) {
//
//                        if (logMessageMap.containsKey(entity.getTransactionReferenceID())) {
//                            logMessageMap.get(entity.getTransactionReferenceID()).add(entity);
//                        } else {
//                            ArrayList list = new ArrayList<LogMessage>();
//                            list.add(entity);
//                            logMessageMap.put(entity.getTransactionReferenceID(), list);
//                        }
//                    }
//
//                    Map<String, ApplicationFlowConfiguration> flowConfigurationMap = new HashMap<String, ApplicationFlowConfiguration>();
//                    for (ApplicationFlowConfiguration flowConfiguration : flowConfigurations) {
//                        flowConfigurationMap.put(flowConfiguration.getName(), flowConfiguration);
//                    }
//
//                    //
//                    // Create wrapped object
//                    int canvas_suffix = 0;
//                    for (FlowAndTransactionId flowAndTransactionId : flowAndTransactionIdList) {
//
//                        String flowName = flowAndTransactionId.getFlowName();
//                        String transactionReferenceId = flowAndTransactionId.getTransactionReferenceId();
//                        if (flowConfigurationMap.containsKey(flowName)
//                                && logMessageMap.containsKey(transactionReferenceId)) {
//
//                            ArrayList<LogMessage> list = logMessageMap.get(transactionReferenceId);
//                            ApplicationFlowConfiguration flowConfiguration = flowConfigurationMap.get(flowName);
//                            dataList.add(new TransactionFlowWrapper(canvas_suffix, flowConfiguration, list));
//                        }
//
//                        canvas_suffix++;
//
//                    }
//                    return new ListDataModel(dataList);
//                }
//            };
//        }
//        return pagination;
//    }
//
//    public boolean isSelected(long entityId) {
//        return current != null && current.getId().equals(entityId);
//    }
//
//    private Date getDefaultSearchFromDate() {
//
//        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//        calendar.set(
//                calendar.get(Calendar.YEAR),
//                calendar.get(Calendar.MONTH),
//                calendar.get(Calendar.DATE),
//                0,
//                0,
//                0);
//
//        return calendar.getTime();
//    }
//
//    private Date getDefaultSearchToDate() {
//        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//
//        calendar.set(
//                calendar.get(Calendar.YEAR),
//                calendar.get(Calendar.MONTH),
//                calendar.get(Calendar.DATE),
//                23,
//                59,
//                59);
//
//        return calendar.getTime();
//    }
//
//    public String getSearchFromDate() {
//        if (this.fromDate == null) {
//            this.fromDate = this.getDefaultSearchFromDate();
//        }
//
//        SimpleDateFormat format = getDateFormater();
//        return format.format(this.fromDate).toString();
//
//    }
//
//    public void setSearchFromDate(String fromDate) {
//        SimpleDateFormat format = getDateFormater();
//        try {
//
//            this.fromDate = format.parse(fromDate);
//
//        } catch (ParseException e) {
//            // Add output message
//            this.fromDate = null;
//        }
//
//    }
//
//    public String getSearchToDate() {
//        if (this.toDate == null) {
//            this.toDate = this.getDefaultSearchToDate();
//        }
//
//        SimpleDateFormat format = getDateFormater();
//        return format.format(this.toDate).toString();
//    }
//
//    public void setSearchToDate(String toDate) {
//        SimpleDateFormat format = getDateFormater();
//        try {
//
//            this.toDate = format.parse(toDate);
//
//        } catch (ParseException e) {
//            // Add output message 
//            this.fromDate = null;
//        }
//
//    }
//
//    public String getSearchInTransactionReferenceId() {
//        return this.transactionReferenceId;
//    }
//
//    public void setSearchInTransactionReferenceId(String transactionReferenceId) {
//        this.transactionReferenceId = transactionReferenceId;
//    }
//
//    public String getSearchInApplicationName() {
//        return this.applicationName;
//    }
//
//    public void setSearchInApplicationName(String applicationName) {
//        this.applicationName = applicationName;
//    }
//
//    public List<String> completeApplicationName(String startsWithName) {
//        return getLogMessageFacade().getApplicationNames(fromDate.getTime(), toDate.getTime(), startsWithName);
//    }
//
//    public String getSearchInFlowName() {
//        return this.flowName;
//    }
//
//    public void setSearchInFlowName(String flowName) {
//        this.flowName = flowName;
//    }
//
//    public List<String> completeFlowName(String startsWithName) {
//        return getLogMessageFacade().getFlowNames(fromDate.getTime(), toDate.getTime(), startsWithName);
//    }
//
//    public String getSearchInFlowPointName() {
//        return this.flowPointName;
//    }
//
//    public void setSearchInFlowPointName(String flowPointName) {
//        this.flowPointName = flowPointName;
//    }
//
//    public List<String> completeFlowPointName(String startsWithName) {
//        return getLogMessageFacade().getFlowPointNames(fromDate.getTime(), toDate.getTime(), startsWithName);
//    }
//
//    public String getSearchFreeText() {
//        return this.freeText;
//    }
//
//    public void setSearchFreeText(String freeText) {
//        this.freeText = freeText;
//
//    }
//
//    public void clearSearchFields() {
//        freeText = "";
//        applicationName = "";
//        flowName = "";
//        flowPointName = "";
//        transactionReferenceId = "";
//        this.search();
//    }
//
//    public Integer getSelectedOption() {
//        return selectedOption;
//    }
//
//    public void setSelectedOption(Integer selectedOption) {
//        this.selectedOption = selectedOption;
//        this.items = null;
//        this.pagination = null;
//    }
//
//    public void allItems() {
//        this.selectedOption = 1;
//        this.search();
//    }
//
//    public void errorItems() {
//        this.selectedOption = 2;
//        this.search();
//    }
//
//    public void successItems() {
//        this.selectedOption = 3;
//        this.search();
//    }
//
//    public void search() {
////        System.err.println("Search called");
//
//        recreateModel();
//    }
//
//    //
//    // Used by Dialoge to display selected Node data
//    public void setSelected(LogMessage item) {
//        this.current = item;
//    }
//
//    public LogMessage getSelected() {
//        return this.current;
//    }
//
//    //
//    // Used by javascript to sublit selected Node
//    private String logMessageId = "";
//
//    public void submitNodeClick() {
//        // System.err.println("SubmitNodeClick");
//    }
//
//    public String getLogMessageId() {
//        return logMessageId;
//    }
//
//    public void setLogMessageId(String logMessageId) {
//        //System.err.println("SetLogMessageId [ " + logMessageId + "]");
//        try {
//            this.logMessageId = logMessageId;
//            long logMsgPk = Long.valueOf(logMessageId);
//            this.current = getLogMessageFacade().find(logMsgPk);
//        } catch (Exception e) {
//            this.current = null;
//        }
//    }

}
