import java.util.Scanner;

/**
 * Created by teddy on 10/13/2017.
 */
public class application {

    public static final int ARMOR_DAMAGE_TYPE_CUT = 0;
    public static final int ARMOR_DAMAGE_TYPE_IMPACT = 1;
    public static final int ARMOR_DAMAGE_TYPE_THRUST = 2;
    public static final int ARMOR_DAMAGE_TYPE_HEAT = 3;
    public static final int ARMOR_DAMAGE_TYPE_COLD = 4;
    public static final int ARMOR_DAMAGE_TYPE_ELECTRICITY = 5;
    public static final int ARMOR_DAMAGE_TYPE_ENERGY = 6;
    public static final int ARMOR_DAMAGE_TYPE_NONE = 7;



    static Scanner scanner = new Scanner(System.in);


    public static Weapon falchion = new Weapon("falchion", 0, 0, 50, 0, 13, 3, 25, ARMOR_DAMAGE_TYPE_CUT, ARMOR_DAMAGE_TYPE_NONE, 5, 0);
    public static Weapon katana = new Weapon("katana", 0, 0, 50, 0, 11, 3, 40, ARMOR_DAMAGE_TYPE_CUT, ARMOR_DAMAGE_TYPE_NONE, 5, 0);
    public static Armor attacker_armor = new Armor("attacker armor", 1, 0, 2, 1, 2, 1, 0, 10, 0, 0, 25, 12, 1, 0);
    public static Armor longcoat = new Armor("longcoat", 1, 0, 2, 1, 2, 2, 0, 0, -5, 0, 25, 10, 0, 5);

    static Weapon attacker_weapon = falchion;
    static Armor defender_armor = longcoat;


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

    static boolean running = true;

    //IF COMBAT SITUATION ATT == NULL, PROBABLY MEANS NOT POSSIBLE OR SOME SHIT

    public static void run(String[] args) {

        System.out.println("RedTedRedemption's Anima attack tool");
        System.out.println("enter 'help' for helptext");

        Parser.parse();

        while (running) {
            System.out.print("> ");
            switch (scanner.next()) {
                case "listcharacters":
                    System.out.println("Characters currently loaded into the tool:");
                    for (Character player : Character.characters) {
                        System.out.println(player.name);
                    }
                    break;

                case "reload":
                    System.out.print("reloading armor, weapons, and characters from dungeon json files...");
                    Parser.parse();
                    System.out.println("done");
                    break;

                case "help":
                    System.out.println("listcharacters - output a list of all characters loaded into the tool");
                    System.out.println("reload - reload characters, armor, and weapons from the json files stored in ./dungeon");
                    System.out.println("attack - start an attack sequence");
                    System.out.println("help - show this message");
                    System.out.println("exit - exit the program, obviously");
                    break;

                case "exit":
                    System.exit(0);
                    break;

                case "attack":
                    Boolean validCharacter = false;
                    String attackerName;
                    String defenderName;
                    Character attacker = new Character();
                    Character defender = new Character();

                    while (!validCharacter) {
                        System.out.print("Enter attacking character's name: ");
                        attackerName = scanner.next();
                        for (Character character : Character.characters) {
                            if (character.name.equals(attackerName)) {
                                attacker = Character.getCharacter(attackerName);
                                validCharacter = true;
                                break;
                            }
                            System.out.println(attackerName + " does not appear to be loaded into the tool. Check the characters.json file");

                        }
                    }



                    validCharacter = false;

                    while (!validCharacter) {
                        System.out.print("Enter attacking character's name: ");
                        defenderName = scanner.next();
                        for (Character character : Character.characters) {
                            if (character.name.equals(defenderName)) {
                                defender = Character.getCharacter(defenderName);
                                validCharacter = true;
                                break;
                            }
                            System.out.println(defenderName + " does not appear to be loaded into the tool. Check the characters.json file");
                        }

                    }




                    System.out.print("attack roll: ");
                    int attackRoll = scanner.nextInt();
                    System.out.print("defend roll: ");
                    int defendRoll = scanner.nextInt();

                    finalAttack = attacker.static_attack + attackRoll;

                    System.out.print("dodge or block? ");
                    boolean choseDodge = scanner.next().toLowerCase().contains("dodge");


                    if (choseDodge) {
                        finalDefense = defender.static_dodge + defendRoll;
                       // System.out.print("final defense: ");
                       // System.out.println(finalDefense);
                    } else {
                        finalDefense = defender.static_block + defendRoll;
                       // System.out.print("final defense: ");
                       // System.out.println(finalDefense);
                    }

                    combatResult = finalAttack - finalDefense;
                   // System.out.print("combatResult: ");
                   // System.out.println(combatResult);

                    //determine if we should counterattack

                    if (combatResult < 0) {
                        //counterattack
                        System.out.println("counterattack: SURPRISE MOTHERFUCKA!!!");

                        counterAttackModifier =  roundDownTen(Math.abs(combatResult) / 10) * 5;

                      //  System.out.print("counterAttackModifier ");
                      //  System.out.println(counterAttackModifier);

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
                           // System.out.print("combatResult ");
                           // System.out.println(counterCombatResult);
                            System.out.print("deal damage ");
                            System.out.println(calculateDamage(defender, attacker, defender_weapon, attacker_armor, counterCombatResult));
                        }

                    } else if (combatResult < 30) {
                        System.out.println("you managed to hit them, but didn't do any damage");
                    } else {
                        System.out.println(combatResult);
                        System.out.print("deal ");
                        System.out.println(calculateDamage(attacker, defender, attacker_weapon, defender_armor, combatResult));
                    }

                    break;

                default:
                    System.out.print("Did not recognize command");

            }



        }




//        boolean counterAttack = false;
//
////        if (combatResult > 0 && combatResult < 30) {
////            reportHit = true;
////            counterAttack = false;
////        }
//
//        if (combatResult > 30) {
//            reportHit = true;
//            counterAttack = false;
//            if (attacker_weapon.attackType_primary == ARMOR_DAMAGE_TYPE_CUT) {
//                combatResult = combatResult - (10 * defender_armor.armorType_cut);
//                combatResult = (combatResult / 10) * 10;
//
//            }
//            int weapon_damage = attacker_weapon.baseDamage + attacker.static_strength;
//            int damage = weapon_damage * combatResult;
//        }
//
////
////
////        if (combatResult < 0) {
////            int counterAttackModifier = Math.abs(combatResult) / 10;
////            counterAttackModifier = (counterAttack / 10) * 10;
////            counterAttackModifier = counterAttackModifier * 5;
////            System.out.print("roll again for counter: ");
////            int defAttackRoll = scanner.nextInt();
////            finalAttack = defAttackRoll + defender.static_attack + counterAttackModifier;
////            System.out.print("and again for attacker (now defender): ");
////            int attackerReroll = scanner.nextInt();
////            finalDefense = attackerReroll + attacker.static_block;
////            combatResult = finalAttack - finalDefense;
////            System.out.println(combatResult);
////        }
////
////        System.out.println();



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
        if (CombatResult < 0) {
            CombatResult = 0;
        }
       // System.out.print("new combat result");
       // System.out.println(CombatResult);
        int damageModifier = CombatResult;
        double damgmdfr = ((double) damageModifier);
        damgmdfr = damgmdfr / 100;
        return ((int) (damgmdfr * (attackWeapon.baseDamage + Attacker.static_strength)));
    }




}
