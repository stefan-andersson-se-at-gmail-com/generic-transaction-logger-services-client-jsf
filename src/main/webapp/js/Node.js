/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var Node = (function() {

    //
    // Private static data
    // var nextId = 1;
    // 

    //
    // constructor
    var nodeClass = function(inId, inLabel, inLogMessageId, inIsError) {
        //
        // private data
        var id = inId;
        var label = inLabel;
        var isError = inIsError;
        var logMessageId = inLogMessageId;
        var incommingEdges = [];
        var outgoingEdges = [];

        // Draw
        var point = new Point(0, 0);
        var shapeItems;
        var shape;
        var shapeText;


        //
        // public methods on this instance

        this.getId = function() {
            return id;
        };
        this.isError = function(){
          return isError;  
        };
        this.isRoot = function() {
            return incommingEdges.length === 0 ? true : false;
        };

        this.getLabel = function() {
            return logMessageId;
        };
        this.setLable = function(inLabel) {
            label = inLabel;
        };
        this.setLogMessageId = function(inLogMessageId) {
            logMessageId = inLogMessageId;
        };
        this.getLogMessageId = function() {
            return logMessageId;
        };
        this.getOutgoingEdges = function() {
            return outgoingEdges;
        };
        this.getIncomminEdges = function() {
            return incommingEdges;
        };
        this.addIncommingEdge = function(edge) {
            if (!this.existIncommingEdge(edge)) {
                this.getIncomminEdges().push(edge);
            }
        };
        this.addOutgoingEdge = function(edge) {
            if (!this.existOutgoingEdge(edge)) {
                this.getOutgoingEdges().push(edge);
            }
        };
        this.existIncommingEdge = function(edge) {
            var exist = false;
            for (var i = 0; i < incommingEdges.length; i++) {
                var equal = incommingEdges[i].getId() === edge.getId();
                if (equal) {
                    exist = true;
                    break;
                }
            }
            return exist;
        };
        this.existOutgoingEdge = function(edge) {
            var exist = false;
            for (var i = 0; i < outgoingEdges.length; i++) {
                var equal = outgoingEdges[i].getId() === edge.getId();
                if (equal) {
                    exist = true;
                    break;
                }
            }
            return exist;

        };

        this.setPoint = function(inPoint) {
            point = inPoint;
        };
        this.getPoint = function() {
            return point;
        };
        this.setShape = function(inShape) {
            shape = inShape;
        };
        this.getShape = function() {
            return shape;
        };
        this.setShapeItems = function(inShapeItems) {
            shapeItems = inShapeItems;
        };
        this.getShapeItems = function() {
            return shapeItems;
        };


        this.toString = function() {
            var str = '{ ';
            str = str + '"id" : "' + id + '" ';
            str = str + '"label" : "' + label + '" ';
            str = str + '"isError" : "' + isError + '" ';
            str = str + '"logMessageId" : "' + logMessageId + '" ';
            str = str + '"point" : "' + point.toString() + '" ';
            str = str + ' }';
            return str;
        };

        this.draw = function(paper) {


            // Init values
            var diameter = 20;
            var radius = 20;

            // All shapes
            shapeItems = paper.set();

            // Create ellipse
            var color = Raphael.getColor();
            var green = '#26bf00';
            var red = '#bf0000'
            var blue = '#0900bf'
            
            //log('Node ' + this.toString());
            
            if (this.isError() == undefined) {
                color = blue;
            } else if (this.isError() === true) {
                color = red;
            } else if (this.isError() === false) {
                color = green;
            } else {
                color = blue;
            }


            shape = paper.ellipse(this.getPoint().getPosX(), this.getPoint().getPosY(), diameter, radius);
            shape.attr({fill: color, stroke: color, "stroke-width": 1.5, "fill-opacity": .5});
            shapeItems.push(shape);


            // Create text
            var text = label;
            var offset_Y = 20 + 10; // shape diameter + padding
            shapeText = paper.text(this.getPoint().getPosX(), this.getPoint().getPosY() + offset_Y, text);
            shapeItems.push(shapeText);

            // Change mouse iff mouse over
            shapeItems.forEach(function(item) {
                item.set = shapeItems;
                item.node.style.cursor = "pointer";
            });

//            shapeItems.click(function() {
//                document.getElementById("node_click_form:selected_logMessage_id").setAttribute("value", logMessageId);
//                // submit the form
//                document.getElementById("node_click_form:submit_node_data").click();
//
//            });
        };

        //
        // public static
//        nodeClass.get_nextId = function() {
//            return nextId;
//        };


        // public (shared across instances)
        nodeClass.prototype = {
            announce: function() {
                log('Node ' + this.getId() + ' Label "' + this.getLabel() + '"!\r\n');
            }
        };

    };
    return nodeClass;
})();

//function Node(id, logMessageId, label, isError) {
//
//    // data
//    this.id = id;
//    this.label = label;
//    this.isError = isError;
//    this.logMessageId = logMessageId;
//    this.incommingEdges = [];
//    this.outgoingEdges = [];
//
//    // Draw
//    this.point = new Point(0, 0);
//    this.items;
//    this.shape;
//    this.shapeText;
//}
//Node.prototype.getId = function() {
//    return this.id;
//};
//
//Node.prototype.isRoot = function() {
//    return this.incommingEdges.length === 0 ? true : false;
//};
//
//Node.prototype.getLabel = function() {
//    return this.logMessageId;
//};
//Node.prototype.setLable = function(label) {
//    this.label = label;
//};
//Node.prototype.setLogMessageId = function(logMessageId) {
//    this.logMessageId = logMessageId;
//};
//Node.prototype.getLogMessageId = function() {
//    return this.logMessageId;
//};
//Node.prototype.getOutgoingEdges = function() {
//    return this.outgoingEdges;
//};
//Node.prototype.getIncomminEdges = function() {
//    return this.incommingEdges;
//};
//Node.prototype.addIncommingEdge = function(edge) {
//    if (!this.existIncommingEdge(edge)) {
//        this.getIncomminEdges().push(edge);
//    }
//};
//Node.prototype.addOutgoingEdge = function(edge) {
//    if (!this.existOutgoingEdge(edge)) {
//        this.getOutgoingEdges().push(edge);
//    }
//};
//Node.prototype.existIncommingEdge = function(edge) {
//    var exist = false;
//    for (var i = 0; i < this.incommingEdges.length; i++) {
//        if (this.incommingEdges[i].getId() === edge.getId()) {
//            exist = true;
//            break;
//        }
//    }
//    return exist;
//};
//Node.prototype.existOutgoingEdge = function(edge) {
//    var exist = false;
//    for (var i = 0; i < this.outgoingEdges.length; i++) {
//        if (this.outgoingEdges[i].getId() === edge.getId()) {
//            exist = true;
//            break;
//        }
//    }
//    return exist;
//
//};
//
//Node.prototype.setPoint = function(point) {
//    this.point = point;
//};
//Node.prototype.getPoint = function() {
//    return this.point;
//};
//Node.prototype.setShape = function(shape) {
//    this.shape = shape;
//};
//Node.prototype.getShape = function() {
//    return this.shape;
//};
//
//Node.prototype.toString = function() {
//    var str = '{ ';
//    str = str + '"id" : "' + this.id + '"';
//    str = str + '"logMessageId" : "' + this.logMessageId + '"';
//    str = str + '"point" : "' + this.getPoint().toString() + '"';
//    str = str + ' }';
//    return str;
//};
//
//Node.prototype.draw = function(paper) {
//
//
//    // Init values
//    var diameter = 20;
//    var radius = 20;
//
//    // All shapes
//    this.items = paper.set();
//
//    // Create ellipse
//    var color = Raphael.getColor();
//    var green = '#26bf00';
//    var red = '#bf0000'
//    var blue = '#0900bf'
//    if (this.isError == undefined) {
//        color = blue;
//    } else if (this.isError === true) {
//        color = red;
//    } else if (this.isError === false) {
//        color = green;
//    } else {
//        color = blue;
//    }
//
//
//    this.shape = paper.ellipse(this.point.getPosX(), this.point.getPosY(), diameter, radius);
//    this.shape.attr({fill: color, stroke: color, "stroke-width": 1.5, "fill-opacity": .5});
//    this.items.push(this.shape);
//
//
//    // Create text
//    var text = this.label;
//    var offset_Y = 20 + 10; // shape diameter + padding
//    this.shapeText = paper.text(this.point.getPosX(), this.point.getPosY() + offset_Y, text);
//    this.items.push(this.shapeText);
//
//
//
//    // Change mouse iff mouse over
//    this.items.forEach(function(item) {
//        item.set = this.items;
//        item.node.style.cursor = "pointer";
//    });
//
//
////    // callback function 
////    this.items.click(function() {
////        if ( this.logMessageId != undefined && this.id != null) {
////            document.getElementById("node_click_form:selected_node_id").setAttribute("value", this.logMessageId);
////            // submit the form                                                                      
////            document.getElementById("node_click_form:submit_node_data").click();
////        };
////    });
//};
