package agh.cs.evolution;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position pos){
        return this.x == pos.x && this.y == pos.y;
    }
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
