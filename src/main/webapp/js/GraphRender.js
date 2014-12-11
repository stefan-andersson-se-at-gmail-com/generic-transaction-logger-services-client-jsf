/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Graph.Renderer = {};

Graph.Renderer.FlowTree = function(inPaper, graph) {
    this.graph = graph;
    this.paper = inPaper;
    /* TODO default node rendering function */
    if (!this.graph.render) {
        this.graph.render = function() {
            return;
        };
    }

    try {
        this.draw();
    } catch (error) {
        log('Error ' + error);
    }
};



Graph.Renderer.FlowTree.prototype = {
    draw: function() {
        var nodes = this.graph.getNodes();
        for (var i = 0; i < nodes.length; i++) {
            this.drawNode(nodes[i]);
        }

        var edges = this.graph.getEdges();
        for (var i = 0; i < edges.length; i++) {
            this.drawEdge(edges[i]);
        }

    },
    drawNode: function(node) {


        node.draw(this.paper);
        node.getShapeItems().click(function() {
            document.getElementById("node_click_form:selected_logMessage_id").setAttribute("value", node.getLogMessageId());
            // submit the form
            document.getElementById("node_click_form:submit_node_data").click();

        });

    },
    drawEdge: function(edge) {
        edge.draw(this.paper);
    }
};