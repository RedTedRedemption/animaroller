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

    static Weapon attacking_weapon = falchion;
    static Armor defender_armor = longcoat;
    static Character defender = jesuskun;
    static Weapon defender_weapon = katana;
    static Armor attackerArmor = attacker_armor;
    static int counterAttackModifier;
    static int finalDefense;
    static boolean reportHit;
    static int counterAttackRoll;
    static int counterDefendRoll;
    static int finalCounterAttack;
    static int finalCounterDefense;
    static int counterCombatResult;
    static int combatResult;
    static int finalAttack;

    //IF COMBAT SITUATION ATT == NULL, PROBABLY MEANS NOT POSSIBLE OR SOME SHIT

    public static void main(String[] args) {


        System.out.print("attack roll: ");
        int attackRoll = scanner.nextInt();
        System.out.print("defend roll: ");
        int defendRoll = scanner.nextInt();

        finalAttack = attacker.static_attack + attackRoll;

        System.out.print("dodge or block? ");
        boolean choseDodge = scanner.next().toLowerCase().contains("dodge");


        if (choseDodge) {
            finalDefense = defender.static_dodge + defendRoll;
            System.out.print("final defense: ");
            System.out.println(finalDefense);
        } else {
            finalDefense = defender.static_block + defendRoll;
            System.out.print("final defense: ");
            System.out.println(finalDefense);
        }

        combatResult = finalAttack - finalDefense;
        System.out.print("combatResult: ");
        System.out.println(combatResult);

        //determine if we should counterattack

        if (combatResult < 0) {
            //counterattack
            System.out.println("counterattack: SURPRISE MOTHERFUCKA!!!");

            counterAttackModifier =  roundDownTen(Math.abs(combatResult) / 10) * 5;

            System.out.print("counterAttackModifier ");
            System.out.println(counterAttackModifier);

            System.out.print("dodge or block? ");
            choseDodge = scanner.next().contains("dodge");

            System.out.print("roll again for defender's counterattack: ");
            counterAttackRoll = scanner.nextInt();

            finalCounterAttack = counterAttackRoll + defender.static_attack + counterAttackModifier;

            System.out.print("roll again for attacker's defense against the counterattack: ");
            counterDefendRoll = scanner.nextInt();

            if (choseDodge) {
                finalCounterDefense = counterDefendRoll + attacker.static_dodge;
            } else {
                finalCounterDefense = counterDefendRoll + attacker.static_block;
            }

            counterCombatResult = finalCounterAttack - finalCounterDefense;

            if (counterCombatResult < 0) {
                System.out.println("YOU FAIL BITCH!");
            } else if (counterCombatResult < 30) {
                reportHit = true;
                System.out.println("you managed to hit them, but didn't do any damage");
                System.out.println("YOU FAIL BITCH!...slightly less");
            } else {
                System.out.print("deal ");
                System.out.println(calculateDamage(defender, attacker, defender_weapon, attacker_armor, counterCombatResult));
            }




        }

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

//
//
//        if (combatResult < 0) {
//            int counterAttackModifier = Math.abs(combatResult) / 10;
//            counterAttackModifier = (counterAttack / 10) * 10;
//            counterAttackModifier = counterAttackModifier * 5;
//            System.out.print("roll again for counter: ");
//            int defAttackRoll = scanner.nextInt();
//            finalAttack = defAttackRoll + defender.static_attack + counterAttackModifier;
//            System.out.print("and again for attacker (now defender): ");
//            int attackerReroll = scanner.nextInt();
//            finalDefense = attackerReroll + attacker.static_block;
//            combatResult = finalAttack - finalDefense;
//            System.out.println(combatResult);
//        }
//
//        System.out.println();



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

    public static int roundDownTen(int val) {
        String valString = Integer.toString(val);
        char[] chars = valString.toCharArray();
        chars[chars.length - 1] = '0';
        return Integer.parseInt(valString);
    }

    public static int calculateDamage(Character Attacker, Character Defender, Weapon attackWeapon, Armor defendArmor, int CombatResult) {
        CombatResult = roundDownTen(CombatResult - (10 * defendArmor.getDefenceByType(attackWeapon.attackType_primary)));
        System.out.println(combatResult);
        if (CombatResult < 0) {
            CombatResult = 0;
        }
        int damageModifier = combatResult;
        return ((int) (((double) damageModifier) * (attackWeapon.baseDamage + Attacker.static_strength)));
    }




}
