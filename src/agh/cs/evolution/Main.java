package agh.cs.evolution;

public class Main {
    public static void main(String[] args){
        WorldMap map = new WorldMap(50,20,5,4);
        Animal animal = new Animal(new Position(10,15), Direction.NORTH, map);
        Plant plant = new Plant(new Position(21,11));
        map.place(plant);
        map.place(animal);
        System.out.println(map);


    }
}