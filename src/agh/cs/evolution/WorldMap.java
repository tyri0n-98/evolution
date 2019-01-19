package agh.cs.evolution;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

public class WorldMap {
    private Position lowerLeft;
    private Position upperRight;
    private Position jungleLowerLeft;
    private Position jungleUpperRight;
    private HashMap<Position, IMapElement> elements = new HashMap<>();
    private MapVisualizer mapVisualizer;

    public HashMap<Position, IMapElement> getElements(){
        return this.elements;
    }

    public WorldMap(int width, int height, int jungleWidth, int jungleHeight){
        if(width < jungleWidth || height < jungleHeight) throw new IllegalArgumentException("Jungle size cannot be larger than size of the map");
        this.lowerLeft = new Position(0, 0);
        this.upperRight = new Position(width -1, height -1);
        int x = width/2;
        int y = height/2;
        this.jungleLowerLeft = new Position(x - jungleWidth/2, y - jungleHeight/2);
        this.jungleUpperRight = new Position(x + jungleWidth - jungleWidth/2 - 1 , y + jungleHeight - jungleHeight/2 - 1);
        this.mapVisualizer = new MapVisualizer(this);
    }

    public boolean isOccupied(Position position){
        this.elements.forEach((k,v) -> System.out.println(k));
        return objectAt(position) != null;
    }

    public boolean canMoveTo(Position position){
        return (!isOccupied(position) || objectAt(position) instanceof Plant) && position.smaller(this.upperRight) && position.larger(this.lowerLeft);
    }

    public IMapElement objectAt(Position position){
        return elements.get(position);
    }

    public boolean place(IMapElement element){
        if(isOccupied(element.getPosition())) return false;
        elements.put(element.getPosition(), element);
        return true;
    }
    //TODO Possibly unnecessary method
    public boolean remove(IMapElement element){
        if(!isOccupied(element.getPosition())) return false;
        elements.remove(element.getPosition());
        return true;
    }

    public void spawnPlants(){
        Random randomGenerator = new Random();
        boolean planted = false;
        while(!planted) {
            int plantX = randomGenerator.nextInt(jungleUpperRight.x - jungleLowerLeft.x + 1) + jungleLowerLeft.x;
            int plantY = randomGenerator.nextInt(jungleLowerLeft.y - jungleUpperRight.y + 1) + jungleLowerLeft.y;
            planted = place(new Plant(new Position(plantX, plantY)));
        }
        planted = false;
        while(!planted){
            int plantX = randomGenerator.nextInt(upperRight.x + 1);
            int plantY;
            if(plantX < jungleLowerLeft.x || plantX > jungleUpperRight.x) plantY = randomGenerator.nextInt(upperRight.y + 1);
            else{
                int section = randomGenerator.nextInt(2);
                if(section == 0) plantY = randomGenerator.nextInt(jungleLowerLeft.y);
                else plantY = randomGenerator.nextInt(upperRight.y - jungleUpperRight.y) + jungleUpperRight.y + 1;
            }
            planted = place(new Plant(new Position(plantX, plantY)));
        }
    }

    public void positionChanged(Position beforeMove, Position afterMove){
        Animal animal = (Animal)elements.remove(beforeMove);
        if(objectAt(afterMove) instanceof Plant){
            elements.remove(afterMove);
            animal.eat();
        }
        elements.put(animal.getPosition(), animal);
    }

    public String toString(){return this.mapVisualizer.draw(this.lowerLeft, this.upperRight);}
}