/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erbjuder.logger.server.web.helper;

import java.io.Serializable;

/**
 *
 * @author Stefan Andersson
 */
public class TransactionFlowWrapper implements Serializable {

//
//    private String canvasId = "canvas_";
//    private FlowConfiguration flowConfiguration;
//    private List<LogMessage> logMessages = new ArrayList<LogMessage>();
//
//    public TransactionFlowWrapper(int canvas_suffix, FlowConfiguration flowConfiguration, ArrayList<LogMessage> logMessages) {
//        this.flowConfiguration = flowConfiguration;
//        this.logMessages = logMessages;
//        this.canvasId = this.canvasId + canvas_suffix;
//
//    }
//
//    
//    public String getCanvasId() {
//        return canvasId;
//    }
//
//    public void setCanvasId(String canvasId) {
//        this.canvasId = canvasId;
//    }
//
//    public FlowConfiguration getFlowConfiguration() {
//        return flowConfiguration;
//    }
//
//    public void setFlowConfiguration(FlowConfiguration flowConfiguration) {
//        this.flowConfiguration = flowConfiguration;
//    }
//
//    public List<LogMessage> getLogMessages() {
//        return logMessages;
//    }
//
//    public void setLogMessages(List<LogMessage> logMessages) {
//        this.logMessages = logMessages;
//    }
//
//    @Override
//    public String toString() {
//        return toJSONString();
//    }
//
//    public String toJSONString() {
//        return toJSON().toString();
//    }
//
//    public String toJSONPrettyString() {
//        String jsonString = "";
//        try {
//            JSONPrettyPrintWriter writer = new JSONPrettyPrintWriter();
//            toJSON().writeJSONString(writer);
//            jsonString = writer.toString();
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
//
//        return jsonString;
//    }
//
//    public JSONObject toJSON() {
//        //
//        // Must make fow grap nodes unique to ensure valid rendering!
//        // Add transactionReferenseId before graph <items> id
//        // 
//        JSONObject json = null;
//        JSONArray nodeList = new JSONArray();
//        JSONArray edgeList = new JSONArray();
//        Map lookUpTable = this.createLookupTable(this.getLogMessages());
//
//        try {
//            for (Node node : this.getFlowConfiguration().getGraph().getNodes()) {
//
//                // Bind flowNode and logMessage
//                if (lookUpTable.containsKey(node.getName())) {
//                    node.setLogMessages((ArrayList<LogMessage>) lookUpTable.get(node.getName()));
//                } else {
//                    node.setLogMessages(new ArrayList<LogMessage>());
//                }
//                JSONObject jsonNode = node.toJSON();
//                jsonNode.put("id", node.getId());
//                nodeList.add(jsonNode);
//            }
//
//            for (Edge edge : this.getFlowConfiguration().getGraph().getEdges()) {
//
//                JSONObject jsonEdge = edge.toJSON();
//                jsonEdge.put("id", edge.getId());
//                jsonEdge.put("from", edge.getFromNode().getId());
//                jsonEdge.put("to", edge.getToNode().getId());
//                edgeList.add(jsonEdge);
//            }
//
//            json = new JSONObject();
//            json.put("nodes", nodeList);
//            json.put("edges", edgeList);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//
//        }
//        return json;
//    }
//
//    private Map<String, ArrayList<LogMessage>> createLookupTable(List<LogMessage> logMessages) {
//        Map<String, ArrayList<LogMessage>> map = new HashMap<String, ArrayList<LogMessage>>();
//
//        for (LogMessage logMessage : logMessages) {
//            String flowPointName = logMessage.getFlowPointName();
//            if (map.containsKey(flowPointName)) {
//                map.get(flowPointName).add(logMessage);
//            } else {
//                ArrayList<LogMessage> list = new ArrayList<LogMessage>();
//                list.add(logMessage);
//                map.put(flowPointName, list);
//            }
//        }
//
//        return map;
//    }
}
