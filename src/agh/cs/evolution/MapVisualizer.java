package agh.cs.evolution;

public class MapVisualizer {
    private final String FRAME_ELEMENT = "# ";
    private WorldMap map;

    public MapVisualizer(WorldMap map){
        this.map = map;
    }

    public String draw(Position lowerLeft, Position upperRight){
        StringBuilder stringBuilder = new StringBuilder();
        for(int y= lowerLeft.y -1; y <= upperRight.y + 1; y++){
            for(int x=lowerLeft.x - 1; x <= upperRight.x + 1; x++){
                if(x == lowerLeft.x -1 || x == upperRight.x +1 || y == lowerLeft.y - 1 || y == upperRight.y + 1)
                    stringBuilder.append(FRAME_ELEMENT);
                else{
                    if(this.map.isOccupied(new Position(x,y))){
                        stringBuilder.append(this.map.objectAt(new Position(x,y)).toString());
                    }
                    else {
                        stringBuilder.append("  ");
                    }
                }
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
