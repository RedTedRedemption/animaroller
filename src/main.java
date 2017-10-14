import java.util.Scanner;

/**
 * Created by teddy on 10/13/2017.
 */
public class main {

    public static final int ARMOR_DAMAGE_TYPE_CUT = 0;
    public static final int ARMOR_DAMAGE_TYPE_IMPACT = 1;
    public static final int ARMOR_DAMAGE_TYPE_THRUST = 2;
    public static final int ARMOR_DAMAGE_TYPE_HEAT = 3;
    public static final int ARMOR_DAMAGE_TYPE_COLD = 4;
    public static final int ARMOR_DAMAGE_TYPE_ELECTRICITY = 5;
    public static final int ARMOR_DAMAGE_TYPE_ENERGY = 6;
    public static final int ARMOR_DAMAGE_TYPE_NONE = 7;

    static Scanner scanner = new Scanner(System.in);

    public static Character attacker = new Character(130, 90, 0, 90, 5, 0, 7, 40, 50, 50, 50, 40, 35, 130);
    public static Character jesuskun = new Character(50, 0, 100, 95, 5, 10, 8, 30, 30, 30, 30, 30, 30, 80);
    public static Weapon falchion = new Weapon(0, 0, 50, 0, 13, 3, 25, ARMOR_DAMAGE_TYPE_CUT, ARMOR_DAMAGE_TYPE_NONE, 5, 0);
    public static Weapon katana = new Weapon(0, 0, 50, 0, 11, 3, 40, ARMOR_DAMAGE_TYPE_CUT, ARMOR_DAMAGE_TYPE_NONE, 5, 0);
    public static Armor attacker_armor = new Armor(1, 0, 2, 1, 2, 1, 0, 10, 0, 0, 25, 12, 1, 0);
    public static Armor longcoat = new Armor(1, 0, 2, 1, 2, 2, 0, 0, -5, 0, 25, 10, 0, 5);


    //IF COMBAT SITUATION ATT == NULL, PROBABLY MEANS NOT POSSIBLE OR SOME SHIT

    public static void main(String[] args) {
        Weapon attacking_weapon = falchion;
        Armor defender_armor = longcoat;
        Character defender = jesuskun;
        System.out.print("attack roll: ");
        int attackRoll = scanner.nextInt();
        System.out.print("defend roll: ");
        int defendRoll = scanner.nextInt();

        int finalAttack = attacker.static_attack + attackRoll;

        int finalDefense = jesuskun.static_dodge + defendRoll;

        int combatResult = finalAttack - finalDefense;

        boolean reportHit;

        boolean counterAttack = false;

//        if (combatResult > 0 && combatResult < 30) {
//            reportHit = true;
//            counterAttack = false;
//        }

        if (combatResult > 30) {
            reportHit = true;
            counterAttack = false;
            if (attacking_weapon.attackType_primary == ARMOR_DAMAGE_TYPE_CUT) {
                combatResult = combatResult - (10 * defender_armor.armorType_cut);
                combatResult = (combatResult / 10) * 10;

            }
            int weapon_damage = attacking_weapon.baseDamage + attacker.static_strength;
            int damage = weapon_damage * combatResult;
        }



        if (combatResult < 0) {
            int counterAttackModifier = Math.abs(combatResult) / 10;
            counterAttackModifier = (counterAttack / 10) * 10;
            counterAttackModifier = counterAttackModifier * 5;
            System.out.print("roll again for counter: ");
            int defAttackRoll = scanner.nextInt();
            finalAttack = defAttackRoll + defender.static_attack + counterAttackModifier;
            System.out.print("and again for attacker (now defender): ");
            int attackerReroll = scanner.nextInt();
            finalDefense = attackerReroll + attacker.static_block;
            combatResult = finalAttack - finalDefense;
            System.out.println(combatResult);
        }

        System.out.println();



    }

    public int additionalDefensePenalty(int numberOfAttacks) {
        if (numberOfAttacks >= 5) {
            return -90;
        } else if (numberOfAttacks == 2) {
            return -30;
        } else if (numberOfAttacks == 3) {
            return -50;
        } else if (numberOfAttacks == 4) {
            return -70;
        }
        return 0;
    }



}
