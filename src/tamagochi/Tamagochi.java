package tamagochi;

import javax.swing.*;
import java.awt.*;
import java.lang.Math;

public class Tamagochi{
    //속성들
    public static final int WATER = 1;
    public static final int FIRE = 2;
    public static final int GRASS = 3;

    //공통 스탯들
    public String name;
    public int prop;
    public int maxHealth;
    public int health;
    public int attack;
    public int speed;
    public int level = 1;
    String imagePath = "./src/tamagochi/img/";

    public static Tamagochi getRandom(){
        //noinspection EnhancedSwitchMigration
        switch((int)(Math.random()*9)+1){
            case 1: return new TamagochiArmadillo();
            case 2: return new TamagochiBat();
            case 3: return new TamagochiBird();
            case 4: return new TamagochiCat();
            case 5: return new TamagochiHedgehog();
            case 6: return new TamagochiRat();
            case 7: return new TamagochiPanda();
            case 8: return new TamagochiSeal();
            case 9: return new TamagochiTurtle();
            default:
                throw new IllegalStateException("Unexpected value: unable to find associated Tamagochi subclass");
        }
    }

    public static Tamagochi getRandom(int prop){
        //noinspection EnhancedSwitchMigration
        switch((int)(Math.random()*3)+1+3*(prop-1)){
            case 1: return new TamagochiSeal();
            case 2: return new TamagochiCat();
            case 3: return new TamagochiBird();
            case 4: return new TamagochiRat();
            case 5: return new TamagochiBat();
            case 6: return new TamagochiTurtle();
            case 7: return new TamagochiPanda();
            case 8: return new TamagochiHedgehog();
            case 9: return new TamagochiArmadillo();
            default:
                throw new IllegalStateException("Unexpected value: unable to find associated Tamagochi subclass");
        }
    }

    public String toString(){
        String str = "";
        str += this.name + "(Lv. " + this.level + ") \n";
        switch(this.prop){
            case 1: str += "속성: 물 \n"; break;
            case 2: str += "속성: 불 \n"; break;
            case 3: str += "속성: 풀 \n";
        }
        str += "체력: " + this.health + "/" + this.maxHealth + " \n";
        str += "공격력: " + this.attack + " \n";
        str += "속도: " + this.speed + " \n";
        return str;
    }

    public String toString(boolean isEnemy){
        if(!isEnemy) return toString();
        String str = "";
        str += "??: (Lv. " + this.level + ") \n";
        switch(this.prop){
            case 1: str += "속성: 물 \n"; break;
            case 2: str += "속성: 불 \n"; break;
            case 3: str += "속성: 풀 \n";
        }
        str += "체력: " + this.health + "/" + this.maxHealth + " \n";
        str += "공격력: ?? \n";
        str += "속도: ?? \n";

        //이름 가리기
        this.name = "??";
        return str;
    }

    public static int propCompare(Tamagochi tamagochi1, Tamagochi tamagochi2){
        switch(tamagochi1.prop){
            case WATER:
                if(tamagochi2.prop == WATER){
                    return 0;
                }else if(tamagochi2.prop == FIRE){
                    return 1;
                }else{
                    return -1;
                }
            case FIRE:
                if(tamagochi2.prop == WATER){
                    return -1;
                }else if(tamagochi2.prop == FIRE){
                    return 0;
                }else{
                    return 1;
                }
            case GRASS:
                if(tamagochi2.prop == WATER){
                    return 1;
                }else if(tamagochi2.prop == FIRE){
                    return -1;
                }else{
                    return 0;
                }
            default:
                throw new IllegalStateException("Unexpected value: " + tamagochi1.prop);
        }
    }

    public boolean isFaster(Tamagochi tamagochi2){
        return this.speed > tamagochi2.speed;
    }

    public void attack(Tamagochi tamagochi){
        if(Tamagochi.propCompare(this, tamagochi) == 1) {
            tamagochi.health -= 2* this.attack;
            System.out.printf("%s(이)가 %s에게 %d의 피해를 입혔습니다.\n",this.name,tamagochi.name,2*this.attack);
        }else{
            tamagochi.health -= this.attack;
            System.out.printf("%s(이)가 %s에게 %d의 피해를 입혔습니다.\n",this.name,tamagochi.name,this.attack);
        }
    }

    public ImageIcon getScaledImage(int width, int height){
        return new ImageIcon(new ImageIcon(this.imagePath).getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
    }

    private static class TamagochiSeal extends Tamagochi{
        public TamagochiSeal(){
            this.name = "스노하푸";
            this.prop = WATER;
            this.maxHealth = 48;
            this.health = maxHealth;
            this.attack = 4;
            this.speed = 5;
            this.imagePath += "Harp.gif";
        }
    }

    private static class TamagochiCat extends Tamagochi{
        public TamagochiCat(){
            this.name = "워터붐냥이";
            this.prop = WATER;
            this.maxHealth = 56;
            this.health = maxHealth;
            this.attack = 4;
            this.speed = 4;
            this.imagePath += "OceanCat.gif";
        }
    }

    private static class TamagochiBird extends Tamagochi{
        public TamagochiBird(){
            this.name = "정은챤";
            this.prop = WATER;
            this.maxHealth = 40;
            this.health = maxHealth;
            this.attack = 4;
            this.speed = 6;
            this.imagePath += "Water.gif";
        }
    }

    private static class TamagochiRat extends Tamagochi{
        public TamagochiRat(){
            this.name = "뜨겁쥐";
            this.prop = FIRE;
            this.maxHealth = 12;
            this.health = maxHealth;
            this.attack = 12;
            this.speed = 5;
            this.imagePath += "FireRat.gif";
        }
    }

    private static class TamagochiBat extends Tamagochi{
        public TamagochiBat(){
            this.name = "열받쥐";
            this.prop = FIRE;
            this.maxHealth = 10;
            this.health = maxHealth;
            this.attack = 12;
            this.speed = 6;
            this.imagePath += "LavaBat.gif";
        }
    }

    private static class TamagochiTurtle extends Tamagochi{
        public TamagochiTurtle(){
            this.name = "아뜨거부기";
            this.prop = FIRE;
            this.maxHealth = 14;
            this.health = maxHealth;
            this.attack = 12;
            this.speed = 4;
            this.imagePath += "VolTurtle.gif";
        }
    }

    private static class TamagochiPanda extends Tamagochi{
        public TamagochiPanda(){
            this.name = "풀바오";
            this.prop = GRASS;
            this.maxHealth = 24;
            this.health = maxHealth;
            this.attack = 8;
            this.speed = 5;
            this.imagePath += "Panda.gif";
        }
    }

    private static class TamagochiHedgehog extends Tamagochi{
        public TamagochiHedgehog(){
            this.name = "꽃슴도치";
            this.prop = GRASS;
            this.maxHealth = 28;
            this.health = maxHealth;
            this.attack = 8;
            this.speed = 6;
            this.imagePath += "Shaymi.gif";
        }
    }

    private static class TamagochiArmadillo extends Tamagochi{
        public TamagochiArmadillo(){
            this.name = "덩굴마딜로";
            this.prop = GRASS;
            this.maxHealth = 20;
            this.health = maxHealth;
            this.attack = 8;
            this.speed = 4;
            this.imagePath += "Grass.gif";
        }

    }

}