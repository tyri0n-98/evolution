package agh.cs.evolution;

public enum Direction {
    NORTH,NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST;

    public Direction next(){
        switch(this){
            case NORTH: return NORTHEAST;
            case NORTHEAST: return EAST;
            case EAST: return SOUTHEAST;
            case SOUTHEAST: return SOUTH;
            case SOUTH: return SOUTHWEST;
            case SOUTHWEST: return WEST;
            case WEST: return NORTHWEST;
            case NORTHWEST: return NORTH;
        }
        return this;
    }

    public Direction multipleNext(int x){
        Direction result = this;
        for(int i = 0; i < x; i++){
            result = result.next();
        }
        return result;
    }

    public Position getVector(){
        switch(this){
            case NORTH: return new Position(0,1);
            case NORTHEAST: return new Position(1,1);
            case EAST: return new Position(1,0);
            case SOUTHEAST: new Position(1,-1);
            case SOUTH: return new Position(0,-1);
            case SOUTHWEST: return new Position(-1,-1);
            case WEST: return new Position(-1,0);
            case NORTHWEST: return new Position(-1,1);
        }
        return new Position(0,0);
    }
}