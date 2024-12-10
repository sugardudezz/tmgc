package scripts;

import tamagochi.Tamago;
import tamagochi.Tamagochi;

import java.util.ArrayList;

public class Storage {
    public static ArrayList<Tamagochi> tamagochis = new ArrayList<>();
    public static ArrayList<Tamagochi> fighters = new ArrayList<>();
    public static ArrayList<Tamagochi> deads = new ArrayList<>();

    public static void add(Tamagochi tamagochi){
        if(tamagochis.size() >= 5){
            throw new ArrayIndexOutOfBoundsException();
        }
        tamagochis.add(tamagochi);
        if(fighters.size() < 3){
            fighters.add(tamagochi);
        }
        System.out.print("Tamagochi added. Size: "+tamagochis.size()+"\n");
    }

    public static boolean isStorageFull(){
        return (tamagochis.size() >= 5);
    }

    public static void setDead(Tamagochi deadTamagochi){
        tamagochis.remove(deadTamagochi);
        deads.add(deadTamagochi);
    }

    public static void clear(){
        tamagochis.clear();
        fighters.clear();
        deads.clear();
    }
}
