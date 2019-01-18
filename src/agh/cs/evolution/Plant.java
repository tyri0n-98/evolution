package agh.cs.evolution;

public class Plant implements IMapElement {
    private Position position;

    public Plant(Position pos){
        this.position = pos;
    }

    public Position getPosition(){
        return this.position;
    }
}
