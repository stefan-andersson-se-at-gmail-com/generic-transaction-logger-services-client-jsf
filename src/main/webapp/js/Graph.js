/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var Graph = (function() {



    //
    // Private static data
    // var nextId = 1;
    // 

    //
    // constructor
    var graphClass = function(id) {

        //
        // private data
        var id = id;
        var nodes = [];
        var edges = [];



        //
        // public methods on this instance
        this.getId = function() {
            return id;
        };


        this.getRoots = function() {
            var roots = [];
            for (var i = 0; i < nodes.length; i++) {
                if (nodes[i].isRoot()) {
                    roots.push(nodes[i]);
                }
            }

            return roots;
        };

        this.isEmpty = function() {
            return nodes.length === 0 && edges.length === 0 ? true : false;
        };

        this.getNode = function(node_id) {
            var node;
            for (var i = 0; i < nodes.length; i++) {
                var equal = nodes[i].getId() === node_id;
                if (equal) {
                    node = nodes[i];
                    break;
                }
            }

            return node;
        };


        this.getNodes = function() {
            return nodes;
        };


        this.getEdges = function() {
            return edges;
        };

        this.addNode = function(node) {
            if (this.existNode(node) === false) {
                nodes.push(node);

                // Syncronize nodes && edges
                for (var i = 0; i < edges.length; i++) {

                    // Add incomming edges to this node
                    var equalFrom = edges[i].getFromNode().getId() === node.getId();
                    if (equalFrom) {
                        node.addIncommingEdge(edges[i]);
                    }

                    // Add outgoing edges to this node
                    var equalTo = edges[i].getToNode().getId() === node.getId();
                    if (equalTo) {
                        node.addOutgoingEdge(edges[i]);
                    }
                }

            }
        };

        this.addEdge = function(edge) {
            if (this.existEdge(edge) === false) {
                edges.push(edge);

                // Node will test 'exist' before store
                edge.getFromNode().addOutgoingEdge(edge);
                edge.getToNode().addIncommingEdge(edge);
            }
        };


        this.existNode = function(node) {
            var exist = false;
            for (var i = 0; i < nodes.length; i++) {
                var equal = nodes[i].getId() === node.getId();
                if (equal) {
                    exist = true;
                    break;
                }
            }

            return exist;

        };
        this.existEdge = function(edge) {
            var exist = false;
            for (var i = 0; i < edges.length; i++) {
                var equal = edges[i].getId() === edge.getId();
                if (equal) {
                    exist = true;
                    break;
                }
            }

            return exist;
        };

        this.deletNode = function(node) {
            for (var i = 0; i < nodes.length; i++) {
                var equal = nodes[i].getId() === node.getId();
                if (equal) {
                    nodes = nodes.splice(i, 1);
                    break;
                }
            }
        };

        this.deleteEdge = function(edge) {
            for (var i = 0; i < edges.length; i++) {
                var equal = edges[i].getId() === edge.getId();
                if (equal) {
                    edges = edges.splice(i, 1);
                    break;
                }
            }
        };

        
        //
        // public static
//        graphClass.get_nextId = function() {
//            return nextId;
//        };


        // public (shared across instances)
        graphClass.prototype = {
            announce: function() {
                log('Graph ' + this.getId() + ' isEmpty ' + this.isEmpty() + ' \r\n');
            }
        };

    };
    return graphClass;
})();
//
//
//function Graph(id, nodes, edges) {
//    this.id = id;
//    this.nodes = nodes || [];
//    this.edges = edges || [];
//
//    // Bounds
//    this.minBound = new Point(0, 0);
//    this.maxBound = new Point(0, 0);
//
//}
//Graph.prototype.getId = function() {
//    return this.id;
//};
//
//Graph.prototype.getMinBound = function() {
//    return this.minBound;
//};
//Graph.prototype.getMaxBound = function() {
//    return this.maxBound;
//};
//Graph.prototype.setMinBound = function(point) {
//    this.minBound = point;
//};
//Graph.prototype.setMaxBound = function(point) {
//    this.maxBound = point;
//};
//
//Graph.prototype.getRoots = function() {
//    var roots = [];
//    for (var i = 0; i < this.nodes.length; i++) {
//        if (this.nodes[i].isRoot()) {
//            roots.push(this.nodes[i]);
//        }
//    }
//
//    return roots;
//};
//
//Graph.prototype.isEmpty = function() {
//    return this.nodes.length > 0 || this.edges.length > 0 ? true : false;
//};
//
//
//
//Graph.prototype.getNodes = function() {
//    return this.nodes;
//};
//Graph.prototype.getNode = function(node_id) {
//    var node;
//    for (var i = 0; i < this.nodes.length; i++) {
//        if (this.nodes[i].getId() == node_id) {
//            node = this.nodes[i];
//            break;
//        }
//    }
//
//    return node;
//};
//
//
//Graph.prototype.getEdges = function() {
//    return this.edges;
//};
//
//Graph.prototype.addNode = function(node) {
//    if (this.existNode(node) === false) {
//        this.nodes.push(node);
//
//        // Syncronize nodes && edges
//        for (var i = 0; i < this.edges.length; i++) {
//
//            // Add incomming edges to this node
//            if (this.edges[i].getFromNode().getId() === node.getId()) {
//                node.addIncommingEdge(this.edges[i]);
//            }
//
//            // Add outgoing edges to this node
//            if (this.edges[i].getToNode().getId() === node.getId()) {
//                node.addOutgoingEdge(this.edges[i]);
//            }
//        }
//
//    }
//};
//
//Graph.prototype.addEdge = function(edge) {
//    if (this.existEdge(edge) === false) {
//        this.edges.push(edge);
//
//        // Node will test 'exist' before store
//        edge.getFromNode().addOutgoingEdge(edge);
//        edge.getToNode().addIncommingEdge(edge);
//    }
//};
//
//
//Graph.prototype.existNode = function(node) {
//    var exist = false;
//    for (var i = 0; i < this.nodes.length; i++) {
//        if (this.nodes[i].id === node.id) {
//            exist = true;
//            break;
//        }
//    }
//
//    return exist;
//
//};
//Graph.prototype.existEdge = function(edge) {
//    var exist = false;
//    for (var i = 0; i < this.edges.length; i++) {
//        if (this.edges[i].id === edge.id) {
//            exist = true;
//            break;
//        }
//    }
//
//    return exist;
//};
//
//Graph.prototype.deletNode = function(node) {
//    for (var i = 0; i < this.nodes.length; i++) {
//        if (this.nodes[i].id === node.id) {
//            this.nodes = this.nodes.splice(i, 1);
//            break;
//        }
//    }
//};
//
//Graph.prototype.deleteEdge = function(edge) {
//    for (var i = 0; i < this.edges.length; i++) {
//        if (this.edges[i].id === edge.id) {
//            this.edges = this.edges.splice(i, 1);
//            break;
//        }
//    }
//};