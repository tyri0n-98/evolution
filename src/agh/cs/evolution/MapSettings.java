package agh.cs.evolution;

public class MapSettings {
    private static final int DEFAULT_MAP_WIDTH = 60;
    private static final int DEFAULT_MAP_HEIGHT = 40;
    private static final int DEFAULT_JUNGLE_WIDTH = 20;
    private static final int DEFAULT_JUNGLE_HEIGHT = 15;

    private int mapWidth;
    private int mapHeight;
    private int jungleWidth;
    private int jungleHeight;

    public MapSettings(){
        setDefaults();
    }

    public void setDefaults(){
        mapWidth = DEFAULT_MAP_WIDTH;
        mapHeight = DEFAULT_MAP_HEIGHT;
        jungleWidth = DEFAULT_JUNGLE_WIDTH;
        jungleHeight = DEFAULT_JUNGLE_HEIGHT;
    }

    public void setMapSize(int mapWidth, int mapHeight, int jungleWidth, int jungleHeight){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.jungleWidth = jungleWidth;
        this.jungleHeight = jungleHeight;
    }

    public String toString(){
        String result = "Map settings:\n";
        result += "Width: " + this.mapWidth + '\n';
        result += "Height: " + this.mapHeight + '\n';
        result += "Jungle width: " + this.jungleWidth + '\n';
        result += "Jungle height: " + this.jungleHeight +'\n';
        return result;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getJungleWidth() {
        return jungleWidth;
    }

    public int getJungleHeight() {
        return jungleHeight;
    }

    public static int getDefaultMapWidth() {
        return DEFAULT_MAP_WIDTH;
    }

    public static int getDefaultMapHeight() {
        return DEFAULT_MAP_HEIGHT;
    }

    public static int getDefaultJungleWidth() {
        return DEFAULT_JUNGLE_WIDTH;
    }

    public static int getDefaultJungleHeight() {
        return DEFAULT_JUNGLE_HEIGHT;
    }
}
