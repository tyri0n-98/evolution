package agh.cs.evolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Animal implements IMapElement{
    private AnimalSettings settings;

    private Position position;
    private int[] genes = new int[8];
    private int energy;
    private int maxEnergy = 100;
    private Direction orientation;
    private WorldMap map;
    private int age;

    public Animal(Position pos, Direction orientation, WorldMap map, AnimalSettings settings){
        this.settings = settings;
        this.position = pos;
        this.age = 0;
        this.energy = this.maxEnergy;
        this.orientation = orientation;
        this.map = map;
        for(int i = 0; i<8; i++){
            this.genes[i] = 1;
        }
    }

    public Animal(Position pos, Direction orientation, int[] genes, WorldMap map, AnimalSettings settings){
        this.settings = settings;
        this.position = pos;
        this.orientation = orientation;
        this.age = 0;
        this.genes = genes;
        this.energy = this.maxEnergy;
        this.map = map;
    }

    public boolean canReproduce(){
        return this.energy >= settings.getEnergyToReproduce() && this.age >= settings.getAgeToReproduce();
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
        if(this.energy + settings.getEnergyGain() > this.maxEnergy) this.energy = this.maxEnergy;
        else this.energy += settings.getEnergyGain();
    }

    public void dayPassed(){
        this.age += 1;
        this.energy -= settings.getDailyEnergyLoss();
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
        Direction spawnDirection = this.orientation;
        for(int i = 0; i<8; i++){
            Position spawnVector = spawnDirection.getVector();
            Position spawnPosition = this.position.add(spawnVector);
            if(map.canSpawn(spawnPosition)){
                int newOrientation = randomGenerator.nextInt(8);
                this.energy /=2;
                return new Animal(spawnPosition, this.orientation.multipleNext(newOrientation), this.mutate(), this.map, this.settings);
            }else
                spawnDirection = spawnDirection.next();
        }
        return null;
    }

    public Position getPosition(){
        return this.position;
    }

    public String toString(){
        return "A ";
    }
}
