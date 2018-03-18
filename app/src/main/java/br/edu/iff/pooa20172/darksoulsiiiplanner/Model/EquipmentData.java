package br.edu.iff.pooa20172.darksoulsiiiplanner.Model;

public class EquipmentData {
    static boolean connected = false;

    private EquipmentData(){
        //no instance
    }

    public static void connect(){
        connected = true;
    }

    public static Weapon getWeapon(int index, int infusion){
        infusion = Weapon.REGULAR;
        switch (index) {
            case 0:
                return new Weapon(0, infusion, 0,0,new SpecialEffect(),
                        0,0,0,0,0,0,
                        0,0,0, 0,0,0,0,0,
                        0,0,0,0,0,
                        0,0,0,0,0,
                        0,0,0,0,0,
                        false,false,false, false);
            case 1:
                return new Weapon(index, infusion, 3,30, new SpecialEffect(),
                        10,10,0,0,30,234,
                        0,0,0, 0,0,0,0,100,
                        45,30,25,25,30,
                        71.4,42,0,0,0,
                        0,0,0,0,0,
                        false,false,false, false);
            case 2:
                return new Weapon(index, infusion, 4.5,80, new SpecialEffect(),
                        12,0,0,0,57,160,
                        0,0,0, 0,0,0,0,100,
                        100,50,52,33,50,
                        42,0,0,0,0,
                        0,0,0,0,0,
                        false,false,false, false);
            case 3:
                return new Weapon(index, infusion, 0.5,50, new SpecialEffect(),
                        4,0,10,0,0,0,
                        0,0,0, 0,0,0,0,0,
                        0,0,0,0,0,
                        0,0,0,0,0,
                        0,0,0,0,0,
                        false,false,true, false);

            default: throw new IndexOutOfBoundsException();
        }
    }

    public static Armor getArmor(int index, int type){
        switch (type){
            case Armor.TYPE_HELM:
                switch (index){
                    case 0: return new Armor(0,0, 0, new SpecialEffect() , Armor.TYPE_HELM, 0, 0, 0, 0, 0,0,0,0,0,0,0,0,0);
                    case 1: return new Armor(index, 5.2, 380, new SpecialEffect() , Armor.TYPE_HELM, 4.5, 3.8, 4.7, 4.2, 2.7,3.8,2.4,3.0,22,17,18,12,4.4);
                    case 2: return new Armor(index,3.7, 350, new SpecialEffect() , Armor.TYPE_HELM, 4.2, 2.5, 4.2, 4.0, 3.5,3.2,1.6,3.2,24,14,17,10,3.4);
                    default: throw new IndexOutOfBoundsException();
                }
            case Armor.TYPE_CHESTPIECE:
                switch (index){
                    case 0: return new Armor(0,0, 0, new SpecialEffect() , Armor.TYPE_CHESTPIECE, 0, 0, 0, 0, 0,0,0,0,0,0,0,0,0);
                    case 1: return new Armor(index, 10.8, 360, new SpecialEffect() , Armor.TYPE_CHESTPIECE, 13.1, 11.0, 13.0, 13.4, 7.6,11.0,6.7,8.4,45,36,38,24,12.4);
                    case 2: return new Armor(index,8.6, 350, new SpecialEffect() , Armor.TYPE_CHESTPIECE, 12.4, 8.0, 12.6, 11.9, 9.2,8.4,3.8,8.4,52,30,36,19,9.5);
                    default: throw new IndexOutOfBoundsException();
                }
            case Armor.TYPE_GAUNTLETS:
                switch (index){
                    case 0: return new Armor(0,0, 0, new SpecialEffect() , Armor.TYPE_GAUNTLETS, 0, 0, 0, 0, 0,0,0,0,0,0,0,0,0);
                    case 1: return new Armor(index,3.6, 370, new SpecialEffect() , Armor.TYPE_GAUNTLETS, 3.6, 3.0, 3.5, 3.4, 2.3,2.9,2.1,2.5,14,12,11,7,2.5);
                    case 2: return new Armor(index,2.9, 350, new SpecialEffect() , Armor.TYPE_GAUNTLETS, 3.1, 2.1, 3.1, 2.9, 1.7,2.1,0.7,1.5,16,12,12,8,1.6);
                    default: throw new IndexOutOfBoundsException();
                }
            case Armor.TYPE_LEGGINGS:
                switch (index){
                    case 0: return new Armor(0,0, 0, new SpecialEffect() , Armor.TYPE_LEGGINGS, 0, 0, 0, 0, 0,0,0,0,0,0,0,0,0);
                    case 1: return new Armor(index,6.7, 370, new SpecialEffect() , Armor.TYPE_LEGGINGS, 8.1, 6.9, 7.4, 7.7, 4.9,6.2,4.4,5.4,26,23,22,13,7.4);
                    case 2: return new Armor(index,5.3, 350, new SpecialEffect() , Armor.TYPE_LEGGINGS, 7.1, 6.5, 6.8, 6.5, 4.6,4.6,1.8,4.1,26,20,20,11,5.1);
                    default: throw new IndexOutOfBoundsException();
                }
            default: throw new IllegalArgumentException();
        }
    }

    public static Ring getRing(int index){
        switch (index) {
            case 0:
                return new Ring(0,0, getSEffect(0));
            case 1:
                return new Ring(1.5, index, getSEffect(1));
            case 2:
                return new Ring(0.8, index, getSEffect(2));
            case 3:
                return new Ring(0.6, index, getSEffect(3));
            case 4:
                return new Ring(1.2, index, getSEffect(4));

            default:
                throw new IndexOutOfBoundsException();

        }
    }

    public static SpecialEffect getSEffect(int index){
        SpecialEffect se = new SpecialEffect(index);
        switch (index) {
            case 0:
                break;
            case 1:
                se.setHpPercent(1.03);
                se.setStaminaPercent(1.085);
                se.setEquipLoadPercent(1.05);
                se.setDescription("Increases HP by 3%, Stamina by 8.5%, and Equipment Load by 5%.");
                break;
            case 2:
                se.setPhysicalAbsorption(1.10);
                se.setStrikeAbsorption(1.10);
                se.setSlashAbsorption(1.10);
                se.setThrustAbsorption(1.10);
                se.setDescription("Increases Physical Absorptions by 10%.");
                break;
            case 3:
                se.setFaith(5);
                se.setDescription("Increases Faith by 5 points.");
                break;
            case 4:
                se.setDescription("Increases souls yield by 10%.");
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
        return se;
    }
}
