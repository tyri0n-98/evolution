package agh.cs.evolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Animal implements IMapElement{
    private static final int DAILY_ENERGY_LOSS = 10;
    private static final int ENERGY_TO_REPRODUCE = 50;
    private static final int ENERGY_GAIN = 20;
    //private static Random randomGenerator = new Random();
    private Position position;
    int[] genes = new int[8];
    private int energy;
    private int maxEnergy = 100;
    private Direction orientation;
    private WorldMap map;
    private int age;

    public Animal(Position pos, Direction orientation, WorldMap map){
        this.position = pos;
        this.age = 0;
        this.energy = this.maxEnergy;
        this.orientation = orientation;
        this.map = map;
        for(int i = 0; i<8; i++){
            this.genes[i] = 1;
        }
    }

    public Animal(Position pos, Direction orientation, int[] genes, WorldMap map){
        this.position = pos;
        this.orientation = orientation;
        this.age = 0;
        this.genes = genes;
        this.energy = this.maxEnergy;
        this.map = map;
    }

    public boolean canReproduce(){
        return this.energy >= ENERGY_TO_REPRODUCE /*&& this.age >= 5*/;
    }

    public int[] mutate(){
        Random randomGenerator = new Random();
        int[] mutatedGenes = new int[8];
        for(int i = 0; i < 8; i++) mutatedGenes[i] = this.genes[i];
        int geneToMutate = randomGenerator.nextInt(8);
        int mutationValue = randomGenerator.nextInt(3) - 1;
        if(mutatedGenes[geneToMutate] == 1 && mutationValue == -1) return mutatedGenes;
        mutatedGenes[geneToMutate] += mutationValue;
        return mutatedGenes;
    }

    public void chooseDirection(){
        List<Integer> genesList = new ArrayList<>();
        int sum = 0;
        for(int i = 0; i < this.genes.length; i++){
            for(int j = 0; j < this.genes[i]; j++){
                genesList.add(i);
                sum += 1;
            }
        }

        Random randomGenerator = new Random();
        int chosenDirection = genesList.get(Math.abs(randomGenerator.nextInt())%sum);
        this.orientation = this.orientation.multipleNext(chosenDirection);
    }

    public void eat(){
        if(this.energy + ENERGY_GAIN > this.maxEnergy) this.energy = this.maxEnergy;
        else this.energy += ENERGY_GAIN;
    }

    public void dayPassed(){
        this.age += 1;
        this.energy -= DAILY_ENERGY_LOSS;
        if(this.isDead()) map.remove(this);
    }

    public boolean isDead(){
        return this.energy <= 0;
    }

    public void moveForward(){
        Position movePosition = this.position.add(this.orientation.getVector());
        if(map.canMoveTo(movePosition)){
            Position beforeMove = this.position;
            this.position = movePosition;
            map.positionChanged(beforeMove, movePosition);
        }
    }

    public Animal reproduce(){
        if(!this.canReproduce()) return null;
        Random randomGenerator = new Random();
        for(int i = -1; i<=1; i++){
            for(int j= -1; j<=1; j++){
                if((i != 0 || j!=0) && map.canSpawn(this.position.add(new Position(i,j)))){
                    int direction = randomGenerator.nextInt(8);
                    this.energy /= 2;
                    return new Animal(this.position.add(new Position(i,j)), this.orientation.multipleNext(direction), this.mutate(), this.map);
                }
            }
        }
        return null;
    }

    public Position getPosition(){
        return this.position;
    }

    public String toString(){
        return "A";
    }
}
