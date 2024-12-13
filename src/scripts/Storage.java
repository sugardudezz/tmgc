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
        if (!(tamagochi instanceof Tamago)) {
            if (fighters.size() < 3) {
                fighters.add(tamagochi);
            }
            System.out.print("Tamagochi added. Size: "+tamagochis.size()+"\n");
        }else{
            System.out.print("Tamago added. Size: "+tamagochis.size()+"\n");
        }
    }

    public static boolean isStorageFull(){
        return (tamagochis.size() >= 5);
    }

    public static boolean isGameOver(){
        for(Tamagochi tamagochi : tamagochis){
            if(!(tamagochi instanceof Tamago)){
                return false;
            }
        }
        return true;
    }

    public static void setDead(Tamagochi deadTamagochi){
        tamagochis.remove(deadTamagochi);
        fighters.remove(deadTamagochi);
        deads.add(deadTamagochi);
    }

    public static void clear(){
        tamagochis.clear();
        fighters.clear();
        deads.clear();
    }

    //알 빼고 받는 함수
    public static ArrayList<Tamagochi> getTamagochisOnly(){
        ArrayList<Tamagochi> arr = new ArrayList<>();
        for (Tamagochi tamagochi : tamagochis){
            if(tamagochi instanceof Tamago) { continue; }
            arr.add(tamagochi);
        }
        return arr;
    }
}
