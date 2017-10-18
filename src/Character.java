import java.util.ArrayList;

/**
 * Created by teddy on 10/13/2017.
 */
public class Character {


    //static means on character sheet




    public int static_attack;
    public int static_block;
    public int static_dodge;
    public int static_initiative;
    public int static_strength;
    public int static_wearArmor;
    public int static_movement;

    //calculated values
    public int movementSpeed;

    //resistances
    public int static_resist_presence;
    public int static_resist_physical;
    public int static_resist_disease;
    public int static_resist_poison;
    public int static_resist_magic;
    public int static_resist_psychic;
    public int static_lifePoints;
    public String name;
    public Weapon weapon;
    public Armor armor;



    public int life;
    public int armorType;
    public int attackDamage;
    public int timesAttacked;
    public int timesRecievedAttack;
    public ArrayList<CombatSituation> combatSituations = new ArrayList<>();

    public static ArrayList<Character> characters = new ArrayList<>();

    public static final CombatSituation none = new CombatSituation(0, 0, 0, 0, 0);
    public static final CombatSituation flanked = new CombatSituation(-10, -30, -30, 0, 0);
    public static final CombatSituation doggyStyle = new CombatSituation(-30, -80, -80, 0, 0);
    public static final CombatSituation surprised = new CombatSituation(null, -90, -90, null, -90);
    public static final CombatSituation visionObscured_partially = new CombatSituation(-30, -30, -15, 0, -30);
    public static final CombatSituation visionObscured_totally = new CombatSituation(- 100, -80, -80, 0,-90);
    public static final CombatSituation higherGround = new CombatSituation(20, 0, 0, 0,0);
    public static final CombatSituation fromGround = new CombatSituation(-30, -30, -30, -10, -30);
    public static final CombatSituation immobalized_partially = new CombatSituation(-20, -20, -40, -20, -40);
    public static final CombatSituation immobalized_mostly = new CombatSituation(-80, -80, -80, -30, -60);
    public static final CombatSituation immobalized_totally = new CombatSituation(-200, -200, -200, -100, -200);
    public static final CombatSituation atWeaponsPoint = new CombatSituation(-20, -120, -120, -50, -100);
    public static final CombatSituation levitating = new CombatSituation(-20, -20, -40, 0, -60);
    public static final CombatSituation flightType_10to14 = new CombatSituation(10, 10, 10, 10, 0);
    public static final CombatSituation flightType_15greater = new CombatSituation(15, 10, 20, 10, 0);
    public static final CombatSituation charging = new CombatSituation(10, -10, -20, 0, 0);
    public static final CombatSituation drawingWeapon = new CombatSituation(-25, -25, 0, 0, -25);
    public static final CombatSituation crowdedSpace_largeWeapon = new CombatSituation(-40, -40, -40, 0, -20); //only applies to large weapons
    public static final CombatSituation crowdedSpace_smallWeapon = new CombatSituation(0, 0, -40, 0, -20); //only applies to small weapons
    public static final CombatSituation adversary_small = new CombatSituation(-10, 0, 0, 0, 0);
    public static final CombatSituation adversary_tiny = new CombatSituation(-20, -10, 0, 0, 0);
    public static final CombatSituation adversary_mediumOrLarger = new CombatSituation(0, 0, 0, 0, 0);


    public Character() { //empty constructor used to suppress "may not have been initialized errors"

    }


    public Character(String Name, int attack, int block, int dodge, int initiative, int strength, int wearArmor, int movement, int resist_presence, int resist_physical, int resist_disease, int resist_poison, int resist_magic, int resist_psychic, int lifePoints, String weaponName, String armorName) {

        name = Name;
        characters.add(this);
        static_attack = attack;
        static_block = block;
        static_dodge = dodge;
        static_initiative = initiative;
        static_strength = strength;
        static_wearArmor = wearArmor;
        static_movement = movement;
        static_resist_presence = resist_presence;
        static_resist_physical = resist_physical;
        static_resist_disease = resist_disease;
        static_resist_poison = resist_poison;
        static_resist_magic = resist_magic;
        static_resist_psychic = resist_psychic;
        static_lifePoints = lifePoints;

        life = lifePoints;

        weapon = Weapon.getWeapon(weaponName);
        armor = Armor.getArmor(armorName);

    }

    public void damage(int damage) {
        life = life - damage;
    }

    public static Character getCharacter(String Name) {
        for (Character character : characters) {
            if (character.name.equals(Name)) {
                return character;
            }
        }
        return null;
    }


}
