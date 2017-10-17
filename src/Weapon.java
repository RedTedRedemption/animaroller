import java.util.ArrayList;

/**
 * Created by teddy on 10/13/2017.
 */
public class Weapon {

    public int attack;
    public int defense;
    public int baseDamage;
    public int speed;
    public int fortitude;
    public int breakage;
    public int presence;
    public int attackType_primary; //use armor_damage_type from Character class (the final vars)
    public int attackType_secondary;
    public int quality;
    public boolean twoHanded = false;
    public int armorTypeReduction;
    public int minimumStrength;
    public String comments_and_additional_effects;
    public String name;


    public static ArrayList<Weapon> weapons = new ArrayList<>();


    public Weapon(String Name, int Attack, int Defense, int BaseDamage, int Speed, int Fortitude, int Breakage, int Presence, int AttackType_Primary, int AttackType_Secondary, int MinimumStrength, int Quality) {

        weapons.add(this);

        name = Name;
        attack = Attack;
        defense = Defense;
        baseDamage = BaseDamage;
        speed = Speed;
        fortitude = Fortitude;
        breakage = Breakage;
        presence = Presence;
        attackType_primary = AttackType_Primary;
        attackType_secondary = AttackType_Secondary;
        quality = Quality;
        minimumStrength = MinimumStrength;

        attack = attack + quality;
        defense = defense + quality;
        speed = speed + quality;
        baseDamage = baseDamage + (quality * 2);
        presence = presence + (quality * 10);
        breakage = breakage + quality;
        fortitude = fortitude + (quality * 2);
        armorTypeReduction = -quality / 5;


    }

    public Weapon(int Attack, int Defense, int BaseDamage, int Speed, int Fortitude, int Breakage, int Presence, int AttackType_Primary, int AttackType_Secondary, int MinimumStrength, int Quality, String comments) {
        attack = Attack;
        defense = Defense;
        baseDamage = BaseDamage;
        speed = Speed;
        fortitude = Fortitude;
        breakage = Breakage;
        presence = Presence;
        attackType_primary = AttackType_Primary;
        attackType_secondary = AttackType_Secondary;
        quality = Quality;
        minimumStrength = MinimumStrength;

        comments_and_additional_effects = comments;

        attack = attack + quality;
        defense = defense + quality;
        speed = speed + quality;
        baseDamage = baseDamage + (quality * 2);
        presence = presence + (quality * 10);
        breakage = breakage + quality;
        fortitude = fortitude + (quality * 2);
        armorTypeReduction = -quality / 5;

    }

    public Weapon(int Attack, int Defense, int BaseDamage, int Speed, int Fortitude, int Breakage, int Presence, int AttackType_Primary, int AttackType_Secondary, int MinimumStrength, int Quality, boolean TwoHanded) {
        attack = Attack;
        defense = Defense;
        baseDamage = BaseDamage;
        speed = Speed;
        fortitude = Fortitude;
        breakage = Breakage;
        presence = Presence;
        attackType_primary = AttackType_Primary;
        attackType_secondary = AttackType_Secondary;
        quality = Quality;
        minimumStrength = MinimumStrength;
        twoHanded = TwoHanded;

        attack = attack + quality;
        defense = defense + quality;
        speed = speed + quality;
        baseDamage = baseDamage + (quality * 2);
        presence = presence + (quality * 10);
        breakage = breakage + quality;
        fortitude = fortitude + (quality * 2);
        armorTypeReduction = -quality / 5;

    }

    public static Weapon getWeapon(String Name) {
        for (Weapon weapon : weapons) {
            if (weapon.name.equals(Name)) {
                return weapon;
            }
        }
        return null;
    }


}
