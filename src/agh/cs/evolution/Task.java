package agh.cs.evolution;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private String input;
    private Command command;
    private List<Integer> args = new ArrayList<>();

    public Task(String input){
        this.input = input + ' ';
        setTask();
    }

    private void setTask(){
        int i;
        for(i = 0; i < this.input.length(); i++){
            if(this.input.charAt(i) == ' ') break;
        }
        this.command = toEnum(this.input.substring(0, i));

        int j;
        Integer arg = 0;
        boolean correctChar = false;
        for(j = i; j < this.input.length(); j++){
            if(this.input.charAt(j) >= 48 && this.input.charAt(j) <= 57) {
                arg = arg * 10 + Character.getNumericValue(this.input.charAt(j));
                correctChar = true;
            }else if(this.input.charAt(j) != ' '){
                correctChar = false;
            } else if(correctChar){
                args.add(arg);
                arg = 0;
            }
        }
    }

    private Command toEnum(String input){
        switch (input){
            case "run": return Command.RUN;
            case "": return Command.RUN;
            case "exit": return Command.EXIT;
            case "help": return Command.HELP;
            case "reset": return Command.RESET;
            case "setMapSize": return Command.SETMAPSIZE;
            case "resetSettings": return Command.RESETSETTINGS;
            case "setAnimalEnergyLoss": return Command.SET_ANIMAL_ENERGY_LOSS;
            case "setAnimalEnergyGain": return Command.SET_ANIMAL_ENERGY_GAIN;
            case "setAnimalEnergyToReproduce": return Command.SET_ANIMAL_AGE_TO_REPRODUCE;
            case "setAnimalAgeToReproduce": return Command.SET_ANIMAL_AGE_TO_REPRODUCE;
            case "resetAnimalSettings": return Command.RESET_ANIMAL_SETTINGS;
            case "resetMapSettings": return Command.RESET_MAP_SETTINGS;
            default: return Command.ERROR;
        }
    }
    public Command getCommand(){
        return this.command;
    }
    public List<Integer> getArgs(){
        return this.args;
    }



}
