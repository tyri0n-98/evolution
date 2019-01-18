package agh.cs.evolution;


import java.util.HashMap;

public class WorldMap {
    private Position lowerLeft;
    private Position upperRight;
    private Position jungleLowerLeft;
    private Position jungleUpperRight;
    HashMap<Position, IMapElement> elements = new HashMap<>();

    public WorldMap(int width, int height, int jungleWidth, int jungleHeight){
        if(width < jungleWidth || height < jungleHeight) throw new IllegalArgumentException("Jungle size cannot be larger than size of the map");
        this.lowerLeft = new Position(0, 0);
        this.upperRight = new Position(width -1, height -1);
        int x = width/2;
        int y = height/2;
        this.jungleLowerLeft = new Position(x - jungleWidth/2, y - jungleHeight/2);
        this.jungleUpperRight = new Position(x + jungleWidth - jungleWidth/2 - 1 , y + jungleHeight - jungleHeight/2 - 1);
    }

    public boolean isOccupied(Position position){
        return objectAt(position) != null;
    }

    public IMapElement objectAt(Position position){
        return elements.get(position);
    }

    public boolean place(IMapElement element){
        if(isOccupied(element.getPosition())) return false;
        elements.put(element.getPosition(), element);
        return true;
    }

    public boolean remove(IMapElement element){
        if(!isOccupied(element.getPosition())) return false;
        elements.remove(element.getPosition());
        return true;
    }
}
