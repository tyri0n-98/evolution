package agh.cs.evolution;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private static final int DEFAULT_MAP_WIDTH = 50;
    private static final int DEFAULT_MAP_HEIGHT = 30;
    private static final int DEFAULT_JUNGLE_WIDTH = 10;
    private static final int DEFAULT_JUNGLE_HEIGHT = 10;
    private WorldMap map;
    private List<Animal> animalsList;

    public Game(){
        this.map = new WorldMap(DEFAULT_MAP_WIDTH, DEFAULT_MAP_HEIGHT, DEFAULT_JUNGLE_WIDTH, DEFAULT_JUNGLE_HEIGHT);
        Animal animal = new Animal(new Position(DEFAULT_MAP_WIDTH/2, DEFAULT_MAP_HEIGHT/2), Direction.NORTH, map);
        this.map.place(animal);
        this.animalsList = new LinkedList<>();
        this.animalsList.add(animal);
    }

    public Game(int mapWidth, int mapHeight, int jungleWidth, int jungleHeight){
        this.map = new WorldMap(mapWidth, mapHeight, jungleWidth, jungleHeight);
        Animal animal = new Animal(new Position(mapWidth/2, mapHeight/2), Direction.NORTH, map);
        this.map.place(animal);
        this.animalsList = new LinkedList<>();
        this.animalsList.add(animal);
    }

    public void runDay(){
        List<Animal> newAnimals = new LinkedList<>();
        List<Animal> deadAnimals = new LinkedList<>();
        for(Animal animal: animalsList){
            animal.dayPassed();
            if(animal.isDead()) deadAnimals.add(animal);
            else {
                animal.chooseDirection();
                animal.moveForward();
                Animal animal1 = animal.reproduce();
                if (animal1 != null && map.place(animal1)) {
                    newAnimals.add(animal1);
                }
            }
        }
        this.animalsList.removeAll(deadAnimals);
        this.animalsList.addAll(newAnimals);
        if(this.animalsList.isEmpty()) return;
        map.spawnPlants();
    }

    public void runDays(int numberOfDays){
        for(int i = 0; i < numberOfDays; i++){
            runDay();
        }
        if(numberOfDays != 0)
            System.out.print(map);
/*            for(Animal animal: animalsList){
                for(int i = 0; i<8; i++) System.out.print(animal.genes[i] + " ");
                System.out.print(animal.orientation);
                System.out.println(animal.getPosition());
                System.out.println();
        }*/
    }
}
