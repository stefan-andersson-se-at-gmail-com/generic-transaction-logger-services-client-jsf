/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var Edge = (function() {

    //
    // Private static data
    // var nextId = 1;
    // 

    //
    // constructor
    var edgeClass = function(id, fromNode, toNode) {
        //
        // private data
        var id = id;
        var fromNode = fromNode;
        var toNode = toNode;


        //
        // public methods on this instance

        this.getId = function() {
            return id;
        };

        this.getFromNode = function() {
            return fromNode;
        };

        this.getToNode = function() {
            return toNode;
        };

        this.toString = function() {
            var str = '{ ';

            str = str + ' } ';
            return str;
        };

        this.draw = function(paper) {

            var style = new Object();
            style.directed = true;

//    var style = edge.style && edge.style.callback && edge.style.callback(edge); // TODO move this somewhere else
            var connection = paper.connection(this.getFromNode().getShape(), this.getToNode().getShape(), style);
            connection.draw();
        };



//
        // public static
//        edgeClass.get_nextId = function() {
//            return nextId;
//        };


        // public (shared across instances)
        edgeClass.prototype = {
            announce: function() {
                log('Edge ' + this.getId() + ' label "' + this.getLabel() + '"!\r\n');
            }
        };

    };
    return edgeClass;
})();



//
//
//function Edge(id, fromNode, toNode) {
//    this.id = id;
//    this.fromNode = fromNode;
//    this.toNode = toNode;
//}
//;
//
//Edge.prototype.getId = function() {
//    return this.id;
//};
//
//Edge.prototype.getFromNode = function() {
//    return this.fromNode;
//};
//
//Edge.prototype.getToNode = function() {
//    return this.toNode;
//};
//
//Edge.prototype.toString = function() {
//    var str = '{ ';
//
//    str = str + ' } ';
//    return str;
//};
//
//Edge.prototype.draw = function(paper) {
//
//    var style = new Object();
//    style.directed = true;
//
////    var style = edge.style && edge.style.callback && edge.style.callback(edge); // TODO move this somewhere else
//    var connection = paper.connection(this.fromNode.getShape(), this.toNode.getShape(), style);
//    connection.draw();
//    //return;
//
//
//};