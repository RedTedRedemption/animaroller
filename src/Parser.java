import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by teddy on 10/16/17.
 */


public class Parser {

    public static JSONParser parser;
    public static JSONObject characterData;
    public static JSONArray characters;
    public static JSONArray armors;
    public static JSONArray weapons;

    public static void parse() {

        Character.characters.clear();
        Armor.armors.clear();
        Weapon.weapons.clear();

        parser = new JSONParser();


        //PARSE WEAPONS

        try {
            weapons = (JSONArray) parser.parse(new FileReader(new File("dungeon/weapons.json").getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Object weap : weapons) {

            JSONObject weapon = (JSONObject) weap;

            int attack = Integer.parseInt(weapon.get("attack").toString());
            int defense = Integer.parseInt(weapon.get("defense").toString());
            int baseDamage = Integer.parseInt(weapon.get("baseDamage").toString());
            int speed = Integer.parseInt(weapon.get("speed").toString());
            int fortitude = Integer.parseInt(weapon.get("fortitude").toString());
            int breakage = Integer.parseInt(weapon.get("breakage").toString());
            int presence = Integer.parseInt(weapon.get("presence").toString());
            int attackType_primary = Integer.parseInt(weapon.get("attackType_primary").toString());
            int attackType_secondary = Integer.parseInt(weapon.get("attackType_secondary").toString());
            int quality = Integer.parseInt(weapon.get("quality").toString());
            boolean twoHanded;
            try {
                twoHanded = Boolean.parseBoolean(weapon.get("twoHanded").toString());
            } catch (java.lang.NullPointerException e) {
                twoHanded = false;
            }
            int armorTypeReduction;
            try {
                armorTypeReduction = Integer.parseInt(weapon.get("armorTypeReduction").toString());
            } catch (java.lang.NullPointerException e) {
                armorTypeReduction = 0;
            }
            int minimumStrength = Integer.parseInt(weapon.get("minimumStrength").toString());
            String name = (String) weapon.get("name");

            new Weapon(name, attack, defense, baseDamage, speed, fortitude, breakage, presence, attackType_primary, attackType_secondary, minimumStrength, quality);



        }

        //PARSE ARMOR

//        try {
//            characters = (JSONArray) parser.parse(new FileReader(new File("dungeon/armor.json").getAbsolutePath()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        //PARSE CHARACTERS

        try {
            characters = (JSONArray) parser.parse(new FileReader(new File("dungeon/characters.json").getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Object chara : characters) {

            JSONObject character = (JSONObject) chara;


            int static_attack = Integer.parseInt(character.get("attack").toString());
            int static_block = Integer.parseInt(character.get("block").toString());
            int static_dodge = Integer.parseInt(character.get("dodge").toString());
            int static_initiative = Integer.parseInt(character.get("initiative").toString());
            int static_strength = Integer.parseInt(character.get("strength").toString());
            int static_wearArmor = Integer.parseInt(character.get("wearArmor").toString());
            int static_movement = Integer.parseInt(character.get("movement").toString());

            int static_resist_presence = Integer.parseInt(character.get("resist_Presence").toString());
            int static_resist_physical = Integer.parseInt(character.get("resist_Physical").toString());
            int static_resist_disease = Integer.parseInt(character.get("resist_Disease").toString());
            int static_resist_poison = Integer.parseInt(character.get("resist_Poison").toString());
            int static_resist_magic = Integer.parseInt(character.get("resist_Magic").toString());
            int static_resist_psychic = Integer.parseInt(character.get("resist_Psychic").toString());
            int static_lifePoints = Integer.parseInt(character.get("lifePoints").toString());

            String name = (String) character.get("name");
            String weaponName = (String) character.get("weapon");
            String armorName = (String) character.get("armor");


            new Character(name, static_attack, static_block, static_dodge, static_initiative, static_strength, static_wearArmor, static_movement, static_resist_presence, static_resist_physical, static_resist_disease, static_resist_poison, static_resist_magic, static_resist_psychic, static_lifePoints, weaponName, armorName);


        }


    }

}
