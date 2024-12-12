package tamagochi;

import scripts.Storage;

public class Tamago extends Tamagochi {

    public Tamago(int prop){
        this.prop = prop;
        switch(prop){
            case WATER: this.imagePath += "TamagoWater.png"; break;
            case FIRE: this.imagePath += "TamagoFire.png"; break;
            case GRASS: this.imagePath += "TamagoGrass.png";
        }
    }

    public String toString() {
        String str = "";
        switch(this.prop){
            case 1: str += "[물 속성 알] \n"; break;
            case 2: str += "[불 속성 알] \n"; break;
            case 3: str += "[풀 속성 알] \n";
        }
        str += "부화: 전투에서 1회 승리";
        return str;
    }

    public void hatch(){
        Storage.tamagochis.remove(this);
        Storage.add(Tamagochi.getRandom(this.prop));
    }

}
