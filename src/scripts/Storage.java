package scripts;

import tamagochi.Tamago;
import tamagochi.Tamagochi;

import java.util.ArrayList;

public class Storage {
    public static ArrayList<Tamagochi> tamagochis = new ArrayList<>();
    public static ArrayList<Tamago> tamagos = new ArrayList<>();
    public static ArrayList<Tamagochi> fighters = new ArrayList<>();
    public static ArrayList<Tamagochi> deads = new ArrayList<>();

    public static void add(Tamagochi tamagochi){
        tamagochis.add(tamagochi);
        if(fighters.size() < 3){
            fighters.add(tamagochi);
        }
        System.out.print("Tamagochi added. Size: "+tamagochis.size()+"\n");
    }

    public static void add(Tamago tamago){
        tamagochis.add(tamago);
        System.out.print("Tamago added. Size: "+tamagos.size()+"\n");
    }

    public static void setDead(Tamagochi deadTamagochi){
        tamagochis.remove(deadTamagochi);
        deads.add(deadTamagochi);
    }
}
