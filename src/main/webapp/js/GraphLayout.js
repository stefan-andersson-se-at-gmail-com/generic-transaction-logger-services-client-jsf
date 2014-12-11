/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


Graph.Layout = {};
Graph.Layout.FlowTree = function(graph) {
    this.graph = graph;
    this.layout();
};
Graph.Layout.FlowTree.prototype = {
    layout: function() {
        this.layoutPrepare();
    },
    layoutPrepare: function() {
        this.calculatePositions(this.graph);
    },
    calculatePositions: function(graph) {

        var posY = 30;     // init origo Y             
        var posX = 40;     // init origo X 
        var offset_X = 85; // shape + edge space
        var offset_Y = 65; // shape + edge space + text space
        var roots = graph.getRoots();

        for (var i = 0; i < roots.length; i++) {
            this.calculateNodePositions(roots[i], posX, offset_X, posY, offset_Y);
            posY = posY + roots[i].getPoint().getPosY();
        }

    },
    calculateNodePositions: function(node, posX, offset_X, posY, offset_Y) {

        var childs = this.getChildNodes(node);

        // isLeaf
        if (childs.length === 0) {
            node.setPoint(new Point(posX, posY));
            // Childs    
        } else {

            var tmp_posX = posX + offset_X;
            for (var i = 0; i < childs.length; i++) {
                var child = childs[i];
                this.calculateNodePositions(child, tmp_posX, offset_X, posY, offset_Y);
                posY = child.getPoint().getPosY() + offset_Y;
            }

            // Calc parent posistion
            var firstChild = childs[0];
            var lastChild = childs[(childs.length - 1)];
            node.setPoint(new Point(posX, ((firstChild.getPoint().getPosY() + lastChild.getPoint().getPosY()) / 2)));
        }
    },
    getChildNodes: function(node) {
        var childs = [];
        var outgoingEdges = node.getOutgoingEdges();
        for (var i = 0; i < outgoingEdges.length; i++) {
            var equal = outgoingEdges[i].getFromNode().getId() === node.getId();
            if (equal) {
                childs.push(outgoingEdges[i].getToNode());
            }
        }
        return childs;
    }
};