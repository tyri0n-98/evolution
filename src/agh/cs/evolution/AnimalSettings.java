package agh.cs.evolution;

public class AnimalSettings {
    private static final int DEFAULT_DAILY_ENERGY_LOSS = 3;
    private static final int DEFAULT_ENERGY_TO_REPRODUCE = 95;
    private static final int DEFAULT_ENERGY_GAIN = 90;
    private static final int DEFAULT_AGE_TO_REPRODUCE = 2;

    private int dailyEnergyLoss;
    private int energyToReproduce;
    private int energyGain;
    private int ageToReproduce;

    public AnimalSettings(){
        setDefaults();
    }

    public void setDefaults(){
        this.dailyEnergyLoss = DEFAULT_DAILY_ENERGY_LOSS;
        this.energyToReproduce = DEFAULT_ENERGY_TO_REPRODUCE;
        this.energyGain = DEFAULT_ENERGY_GAIN;
        this.ageToReproduce = DEFAULT_AGE_TO_REPRODUCE;
    }

    public String toString(){
        String result = "Animals settings:\n";
        result += "dailyEnergyLoss: " + this.dailyEnergyLoss + '\n';
        result += "energyToReproduce: " + this.energyToReproduce + '\n';
        result += "energyGain: " + this.energyGain + '\n';
        result += "ageToReproduce: " + this.ageToReproduce + '\n';
        return result;
    }

    public void setDailyEnergyLoss(int dailyEnergyLoss) {
        if(dailyEnergyLoss <= 100 && dailyEnergyLoss >= 0)
            this.dailyEnergyLoss = dailyEnergyLoss;
    }

    public void setEnergyToReproduce(int energyToReproduce) {
        if(energyToReproduce >= 0 && energyToReproduce <= 100)
            this.energyToReproduce = energyToReproduce;
    }

    public void setEnergyGain(int energyGain) {
        if(energyGain >= 0 && energyGain <= 100)
            this.energyGain = energyGain;
    }

    public void setAgeToReproduce(int ageToReproduce) {
        if(ageToReproduce >= 0)
            this.ageToReproduce = ageToReproduce;
    }

    public int getDailyEnergyLoss() {
        return dailyEnergyLoss;
    }

    public int getEnergyToReproduce() {
        return energyToReproduce;
    }

    public int getEnergyGain() {
        return energyGain;
    }

    public int getAgeToReproduce() {
        return ageToReproduce;
    }

    public static int getDefaultDailyEnergyLoss() {
        return DEFAULT_DAILY_ENERGY_LOSS;
    }

    public static int getDefaultEnergyToReproduce() {
        return DEFAULT_ENERGY_TO_REPRODUCE;
    }

    public static int getDefaultEnergyGain() {
        return DEFAULT_ENERGY_GAIN;
    }

    public static int getDefaultAgeToReproduce() {
        return DEFAULT_AGE_TO_REPRODUCE;
    }
}
