package tamagochi;

import scripts.Storage;

public class Tamago extends Tamagochi {

    public Tamago(int prop){
        this.prop = prop;
        switch(prop){
            case WATER: name = "WaterEgg"; imagePath += "TamagoWater.png"; break;
            case FIRE: name = "FireEgg"; imagePath += "TamagoFire.png"; break;
            case GRASS: name = "GrassEgg"; imagePath += "TamagoGrass.png";
        }
    }

    public String toString() {
        String str = "";
        str += "알(Lv."+level+") ";
        switch(prop){
            case WATER: str += "속성: 물 "; break;
            case FIRE: str += "속성: 불 "; break;
            case GRASS: str += "속성: 풀 ";
        }
        str += "부화: 전투에서 1회 승리";
        return str;
    }

    public void hatch(){
        Storage.tamagochis.remove(this);
        Storage.add(Tamagochi.getRandom(prop));
    }

}
