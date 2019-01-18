package agh.cs.evolution;

public class Position {
    public final int x;
    public final int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Position))
            return false;
        Position that = (Position) other;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int hashCode(){
        int hash = 17;
        hash += this.x * 23;
        hash += this.y * 11;
        return hash;
    }
    public boolean smaller(Position pos){
        return this.x <= pos.x && this.y <= pos.y;
    }

    public boolean larger(Position pos){
        return this.x >= pos.x && this.y >= pos.y;
    }

    public Position add(Position pos){
        return new Position(this.x+pos.x, this.y + pos.y);
    }
    public Position subtract(Position pos){
        return new Position(this.x - pos.x, this.y - pos.y);
    }
}
