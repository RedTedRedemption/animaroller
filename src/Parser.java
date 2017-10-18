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

    public static void parse() {

        parser = new JSONParser();


        //PARSE WEAPONS

        try {
            characters = (JSONArray) parser.parse(new FileReader(new File("dungeon/characters.json").getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //PARSE ARMOR

        try {
            characters = (JSONArray) parser.parse(new FileReader(new File("dungeon/characters.json").getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
