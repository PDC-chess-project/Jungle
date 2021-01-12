package com.chess.jungle.logic;


import java.util.ArrayList;
import java.util.List;


public class JungleGame {

    protected Board board;
    protected List<Piece> pieceList = new ArrayList<>();

    public JungleGame() {
        board = Board.getInstance();
        pieceList.add(new Piece(Piece.Type.LION,0,0));
        pieceList.add(new Piece(Piece.Type.TIGER,6,0));
        pieceList.add(new Piece(Piece.Type.DOG,1,1));
        pieceList.add(new Piece(Piece.Type.CAT,5,1));
        pieceList.add(new Piece(Piece.Type.MOUSE,0,2));
        pieceList.add(new Piece(Piece.Type.LEOPARD,2,2));
        pieceList.add(new Piece(Piece.Type.WOLF,4,2));
        pieceList.add(new Piece(Piece.Type.ELEPHANT,6,2));
        pieceList.add(new Piece(Piece.Type.ELEPHANT,0,6));
        pieceList.add(new Piece(Piece.Type.WOLF,2,6));
        pieceList.add(new Piece(Piece.Type.LEOPARD,4,6));
        pieceList.add(new Piece(Piece.Type.MOUSE,6,6));
        pieceList.add(new Piece(Piece.Type.CAT,1,7));
        pieceList.add(new Piece(Piece.Type.DOG,5,7));
        pieceList.add(new Piece(Piece.Type.TIGER,0,8));
        pieceList.add(new Piece(Piece.Type.LION,6,8));
    }

    public List<Piece> getPieceList() {
        return pieceList;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * 当前棋子所有可移动到的坐标
     * @param piece 棋子
     * @return 所有可移动到的坐标
     */
    public Coordinate[] getPossibleMove(Piece piece) {
        ArrayList<Coordinate> res = new ArrayList<>();
        Coordinate tmp;
        if((tmp = isMovable(piece,Direction.UP)) != null){
            res.add(tmp);
        }
        if((tmp = isMovable(piece,Direction.DOWN)) != null){
            res.add(tmp);
        }
        if((tmp = isMovable(piece,Direction.LEFT)) != null){
            res.add(tmp);
        }
        if((tmp = isMovable(piece,Direction.RIGHT)) != null){
            res.add(tmp);
        }

        return res.toArray(new Coordinate[0]);
    }

    public void movePiece(Piece piece,int x,int y){
        
    }

    /**
     * 判断这个棋子是否可以朝一个方向移动
     * @param piece 棋子
     * @param direction 方向
     * @return 是否可以朝这个方向移动，是则返回移动后的坐标，否则返回null
     */
    private Coordinate isMovable(Piece piece, Direction direction){
        Board board = Board.getInstance();
        Coordinate next = new Coordinate(piece.x,piece.y).nextPace(direction);
        int nextX = next.x;
        int nextY = next.y;

        if(nextX < 0 || nextX > board.getWidth() - 1){
            //如果超出宽度
            return null;
        }
        if(nextY < 0 || nextY > board.getHeight() - 1){
            //如果超出长度
            return null;
        }
        if(board.isRiver(nextX,nextY)){
            //如果是河流
            if(piece.type == Piece.Type.LION || piece.type == Piece.Type.TIGER){
                //如果是狮子或老虎
                if(hasBlockInRiver(piece.x, piece.y, direction)){
                    //如果河中间有老鼠阻挡，无法跳跃
                    return null;
                }
                //获取河对岸坐标
                Coordinate coordinate = board.getOppositeShore(piece.x,piece.y,direction);
                nextX = coordinate.x;
                nextY = coordinate.y;
            }else if(piece.type != Piece.Type.MOUSE){
                //如果不是老鼠、狮子、老虎
                return null;
            }
        }

        Piece target = getPieceByPos(nextX,nextY);
        if(target != null && !piece.isEdible(target)){
            //如果对手存在并且无法打败
            return null;
        }

        return new Coordinate(nextX,nextY);
    }

    /**
     * 根据坐标查找动物
     * @param x x坐标
     * @param y y坐标
     * @return 动物，没有则返回null
     */
    public Piece getPieceByPos(int x,int y){
        for(Piece p : pieceList){
            if(p.getX() == x && p.getY() == y){
                return p;
            }
        }
        return null;
    }

    /**
     * 根据动物类型查找动物
     * @param type 动物类型
     * @return 动物（数组），没有则返回空数组
     */
    public Piece[] getPiecesByType(Piece.Type type){
        ArrayList<Piece> res = new ArrayList<>();
        for(Piece p : pieceList){
            if(p.type == type){
                res.add(p);
            }
        }
        return res.toArray(new Piece[0]);
    }

    /**
     * 判断河中间是否老鼠阻挡狮子或老虎的跳跃
     * @param x 老虎或狮子的x坐标
     * @param y 老虎或狮子的y坐标
     * @param direction 方向
     * @return 是否有老鼠阻挡
     */
    public boolean hasBlockInRiver(int x,int y,Direction direction){
        Coordinate c = getBoard().getOppositeShore(x,y,direction);
        Piece[] pieces = getPiecesByType(Piece.Type.MOUSE);
        if(pieces.length == 0){
            return false;
        }

        for(Piece mouse : pieces){
            if(direction == Direction.UP || direction == Direction.DOWN){
                //判断垂直方向
                if(mouse.x == x &&
                        mouse.y > Math.min(y,c.y) &&
                        mouse.y < Math.max(y,c.y)){
                    return true;
                }
            }else {
                //判断水平方向
                if(mouse.y == y &&
                        mouse.x > Math.min(x,c.x) &&
                        mouse.x < Math.max(x,c.x)){
                    return true;
                }
            }
        }

        return false;

    }


}
