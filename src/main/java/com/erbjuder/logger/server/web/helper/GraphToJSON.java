/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erbjuder.logger.server.web.helper;


/**
 *
 * @author Stefan Andersson
 */
public class GraphToJSON {

//    protected static int numberOfNodes = 10;
//    protected Graph graph;
//
// 
//    protected void setUp() {
//
//        // 
//        // Init new graph && flowConfiguraiton
//        this.graph = new Graph();
//        this.graph.setId(Long.MIN_VALUE);
//        FlowConfiguration flowConfiguration = this.creatFlowConfiguration();
//
//        //
//        // Bind Graph && flow configuration
//        flowConfiguration.setGraph(graph);
//        graph.setFlowConfiguration(flowConfiguration);
//
//        //
//        // Use all available nodes in configuration to exist in graph
//        graph.setNodes(flowConfiguration.getAllDefinedNodes());
//
//        //
//        // Create silly node binding <fromNode, ToNode >
//        List<Node> evenNodes = new ArrayList<Node>();
//        List<Node> oddNodes = new ArrayList<Node>();
//        for (Node node : graph.getNodes()) {
//
//            // 
//            // Split intoodd, even nodes
//            int id = node.getId().intValue();
//            if ((id & 1) == 0) {
//                evenNodes.add(node);
//            } else {
//                oddNodes.add(node);
//            }
//        }
//
//
//        // 
//        // Silly bind operation: even (parent) to odd (child) 
//        for (Node fromNode : evenNodes) {
//            for (Node toNode : oddNodes) {
//                graph.insertUnEdge(fromNode, toNode); // One way binding direction
//            }
//        }
//
//    }
//
//    protected FlowConfiguration creatFlowConfiguration() {
//
//        FlowConfiguration flowConfiguration = new FlowConfiguration();
//        flowConfiguration.setId(Long.MIN_VALUE);
//        flowConfiguration.setFlowName("Flow name");
//        flowConfiguration.setFlowDescription("Flow description");
//
//
//        for (int i = 0; i < numberOfNodes; i++) {
//
//            Node node = new com.erbjuder.logger.server.entity.impl.Node();
//            node.setId((long) i);
//            node.setName("Node name=" + i);
//            node.setDescription("Node description=" + i);
//            node.setFlowConfiguration(flowConfiguration);
//            flowConfiguration.addToAllDefinedNode(node);    // All available nodes 
//
//        }
//
//        return flowConfiguration;
//    }
//
// 
//    public void testGetJSON() {
//        String jsonString = graph.toString();
// 
//    }
//    
//    
//    public static void main(String[] args){
////        Gson gson = new Gson();
////        GraphToJSON graphToJSON = new com.erbjuder.logger.server.web.helper.GraphToJSON();
////        graphToJSON.setUp();
//        //gson.toJson(graphToJSON.graph);
//        //System.err.println(gson.toJson(graphToJSON.graph))
//        
//    }
}