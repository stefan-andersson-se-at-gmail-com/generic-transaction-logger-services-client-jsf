/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function log(a) {
    //console.log && console.log(a);
}

var Point = (function() {

    //
    // Private static data
    // var nextId = 1;
    // 

    //
    // constructor
    var pointClass = function(posX, posY) {
        //
        // private data
        var posX = posX;
        var posY = posY;


        this.getPosX = function() {
            return posX;
        };

        this.getPosY = function() {
            return posY;
        };

        this.setPosX = function(inPosX) {
            posX = inPosX;
        };

        this.setPosY = function(inPosY) {
            posY = inPosY;
        };

        this.setPos = function(inPosX, inPosY) {
            posX = inPosX;
            posY = inPosY;
        };

        this.toString = function() {
            var str = '{ ';
            str = str + ' "posX" : "' + this.getPosX() + '"';
            str = str + ' "posY" : "' + this.getPosY() + '"';
            str = str + ' }';
            return str;
        };



//
        // public static
//        edgeClass.get_nextId = function() {
//            return nextId;
//        };


        // public (shared across instances)
        pointClass.prototype = {
            announce: function() {
                log('Point ' + this.getPosX() + ' , ' + this.getPosY());
            }
        };

    };
    return pointClass;
})();

//
//function Point(posX, posY) {
//    this.posX = posX;
//    this.posY = posY;
//}
//;
//
//Point.prototype.getPosX = function() {
//    return this.posX;
//};
//
//Point.prototype.getPosY = function() {
//    return this.posY;
//};
//
//Point.prototype.setPosX = function(posX) {
//    this.posX = posX;
//};
//
//Point.prototype.setPosY = function(posY) {
//    this.posY = posY;
//};
//
//Point.prototype.setPos = function(posX, posY) {
//    this.posX = posX;
//    this.posY = posY;
//};
//
//Point.prototype.toString = function() {
//    var str = '{ ';
//    str = str + ' "posX" : "' + this.posX + '"';
//    str = str + ' "posY" : "' + this.posY + '"';
//    str = str + ' }';
//    return str;
//};
//
//
//Point.prototype.transform = function(layoutMinX, factorX, layoutMinY, factorY) {
//    var tmpPosX = (this.getPosX() - layoutMinX) * factorX;
//    var tmpPosY = (this.getPosY() - layoutMinY) * factorY;
//    return new Point(tmpPosX, tmpPosY);
//};
//
//
//function log(a) {
//    console.log && console.log(a);
//}
//
