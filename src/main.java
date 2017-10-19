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


   // public static Weapon katana = new Weapon("katana", 0, 0, 50, 0, 11, 3, 40, ARMOR_DAMAGE_TYPE_CUT, ARMOR_DAMAGE_TYPE_NONE, 5, 0);
   // public static Armor attacker_armor = new Armor("attacker armor", 1, 0, 2, 1, 2, 1, 0, 10, 0, 0, 25, 12, 1, 0);
   // public static Armor longcoat = new Armor("longcoat", 1, 0, 2, 1, 2, 2, 0, 0, -5, 0, 25, 10, 0, 5);


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

    public static void main(String[] args) {

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

                case "listweapons":
                    System.out.println("Weapons currently loaded into the tool:");
                    for (Weapon weapon : Weapon.weapons) {
                        System.out.println(weapon.name);
                    }
                    break;

                case "listarmor":
                    System.out.println("Armor currently loaded into the tool:");
                    for (Armor armor : Armor.armors) {
                        System.out.println(armor.name);
                    }

                case "reload":
                    System.out.print("reloading armor, weapons, and characters from dungeon json files...");
                    Parser.parse();
                    System.out.println("done");
                    break;

                case "help":
                    System.out.println("listcharacters - output a list of all characters loaded into the tool");
                    System.out.println("listweapons - output a list of all weapons loaded into the tool");
                    System.out.println("listarmor - output a list of all armor loaded into the tool");
                    System.out.println("reload - reload characters, armor, and weapons from the json files stored in ./dungeon");
                    System.out.println("attack - start an attack sequence");
                    System.out.println("about - search for information about a weapon, armor, or character -- NOT FULLY IMPLEMENTED YET");
                    System.out.println("help - show this message");
                    System.out.println("exit - exit the program, obviously");
                    break;


//                ublic int attack;
//                public int defense;
//                public int baseDamage;
//                public int speed;
//                public int fortitude;
//                public int breakage;
//                public int presence;
//                public int attackType_primary;
//                public int attackType_secondary;
//                public int quality;
//                public boolean twoHanded = false;
//                public int armorTypeReduction;
//                public int minimumStrength;
//                public String comments_and_additional_effects;
//                public String name;

                case "about":
                    System.out.print("name: ");
                    String searchTerm = scanner.next();
                    for (Weapon weapon: Weapon.weapons) {
                        if (weapon.name.contains(searchTerm)) {
                            System.out.println("Weapon " + searchTerm + ":");
                            System.out.print("Attack: ");
                            System.out.println(weapon.attack);
                            System.out.print("Defense");
                            System.out.println(weapon.defense);
                            System.out.print("Base Damage: ");
                            System.out.println(weapon.baseDamage);
                            System.out.print("Speed: ");
                            System.out.println(weapon.speed);
                            System.out.print("Fortitude: ");
                            System.out.println(weapon.fortitude);
                            System.out.print("Breakage: ");
                            System.out.println(weapon.breakage);
                            System.out.print("Presence: ");
                            System.out.println(weapon.presence);
                            System.out.println("I'm to lazy to write the code to make this more readable 'cause it involves a lot of typing. Here are the integer ids for the damage types:");
                            System.out.print("Primary Damage Type: ");
                            System.out.println(weapon.attackType_primary);
                            System.out.print("Secondary Damage Type: ");
                            System.out.println(weapon.attackType_secondary);
                            System.out.print("Quality: ");
                            System.out.println(weapon.quality);
                            System.out.print("Two Handed: ");
                            System.out.println(weapon.twoHanded);
                            System.out.print("Armor Type Reduction: ");
                            System.out.println(weapon.armorTypeReduction);
                            System.out.print("Minimum Strength: ");
                            System.out.println(weapon.minimumStrength);
                            System.out.println("Comments and additional effects: ");
                            System.out.println(weapon.comments_and_additional_effects);



                        }
                    }

                    break;


                case "exit":
                    System.exit(0);
                    break;

                case "attack":
                    Boolean validCharacter = false;
                    String attackerName = "";
                    String defenderName = "";

                    //suppresses a "may not be initialized" error
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
                        }

                        if (validCharacter) {
                            break;
                        }

                        System.out.println(attackerName + " does not appear to be loaded into the tool. Check the characters.json file");

                    }


                    validCharacter = false;

                    while (!validCharacter) {
                        System.out.print("Enter defending character's name: ");
                        defenderName = scanner.next();
                        for (Character character : Character.characters) {
                            if (character.name.equals(defenderName)) {
                                defender = Character.getCharacter(defenderName);
                                validCharacter = true;
                                break;
                            }
                        }

                        if (validCharacter) {
                            break;
                        }
                        System.out.println(defenderName + " does not appear to be loaded into the tool. Check the characters.json file");
                    }



//                    while (!validCharacter) {
//                        System.out.print("Enter defending character's name: ");
//                        defenderName = scanner.next();
//                        for (Character character : Character.characters) {
//                            if (character.name.equals(defenderName)) {
//                                defender = Character.getCharacter(defenderName);
//                                validCharacter = true;
//                                break;
//                            }
//                            System.out.println(defenderName + " does not appear to be loaded into the tool. Check the characters.json file");
//                        }
//                    }




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
                            System.out.print("deal ");
                            System.out.print(calculateDamage(defender, attacker, defender.weapon, attacker.armor, counterCombatResult));
                            System.out.print(" damage to ");
                            System.out.println(attackerName);
                        }

                    } else if (combatResult < 30) {
                        System.out.println("you managed to hit them, but didn't do any damage");
                    } else {
                        System.out.println(combatResult);
                        System.out.print("deal ");
                        System.out.print(calculateDamage(attacker, defender, attacker.weapon, defender.armor, combatResult));
                        System.out.print(" damage to ");
                        System.out.println(defenderName);
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

    public static int additionalDefensePenalty(int numberOfAttacks) {
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
