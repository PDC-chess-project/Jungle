package com.chess.jungle.logic;

/**
 *
 * @author CommA
 */
public class Piece {


    public enum Type {
        MOUSE,
        CAT,
        DOG,
        WOLF,
        LEOPARD,
        TIGER,
        LION,
        ELEPHANT
    }

    protected Type type;
    protected int x;
    protected int y;

    public Piece(Type type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public Type getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoordinate(Coordinate coordinate){
        this.x = coordinate.x;
        this.y = coordinate.y;
    }

    public Coordinate getCoordinate(){
        return new Coordinate(x,y);
    }

    /**
     * 判断这个动物是否比另一个动物大（动物相同时，认为这个动物大）
     * @param target 另一个动物
     * @return 是否比另一个动物更大
     */
    public boolean isBiggerThan(Piece target){
        if(this.type == Type.MOUSE && target.type == Type.ELEPHANT){
            return true;
        } else if (this.type == Type.ELEPHANT && target.type == Type.MOUSE) {
            return false;
        }else {
            return this.type.compareTo(target.type) >= 0;
        }
    }

    /**
     * 判断是否可以吃掉对方，这里特殊处理了老鼠
     * @param target 另一个动物
     * @return 是否可以吃掉对方
     */
    public boolean isEdible(Piece target){
        if(Board.getInstance().isRiver(this.x,this.y) ^ Board.getInstance().isRiver(target.x,target.y)){
            //如果双方分别在河流中和陆地上
            return false;
        }
        return isBiggerThan(target);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "type=" + type +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
