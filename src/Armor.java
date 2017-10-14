/**
 * Created by teddy on 10/13/2017.
 */
public class Armor {

    public int coverageType;

    public final int SHIRT = 0;
    public final int BREAST_PLATE = 1;
    public final int FULL_PLATE = 2;
    public final int COMPLETE = 3;
    public final int HEAD = 4;

    public int armorType_cut;
    public int armorType_impact;
    public int armorType_thrust;
    public int armorType_heat;
    public int armorType_electricity;
    public int armorType_cold;
    public int armorType_energy;
    public int armorRequirement;
    public int naturalPenalty;
    public int initiative;
    public int presence;
    public int fortitude;
    public int movementPenalty;
    public int quality;
    public String comments_and_additional_effects;

    public int[] defenses = new int[7];

    public Armor(int ArmorType_cut, int ArmorType_impact, int ArmorType_thrust, int ArmorType_heat, int ArmorType_electricity, int ArmorType_cold, int ArmorType_energy, int ArmorRequirement, int NaturalPenalty, int Initiative, int Presence, int Fortitude, int MovementPenalty, int Quality) {

        armorType_cut = ArmorType_cut + (quality / 5);
        armorType_impact = ArmorType_impact + (quality / 5);
        armorType_thrust = ArmorType_thrust + (quality / 5);
        armorType_heat = ArmorType_heat + (quality / 5);
        armorType_electricity = ArmorType_electricity + (quality / 5);
        armorType_cold = ArmorType_cold + (quality / 5);
        armorType_energy = ArmorType_energy + (quality / 5);

        defenses[main.ARMOR_DAMAGE_TYPE_CUT] = armorType_cut;
        defenses[main.ARMOR_DAMAGE_TYPE_IMPACT] = armorType_impact;
        defenses[main.ARMOR_DAMAGE_TYPE_THRUST] = armorType_thrust;
        defenses[main.ARMOR_DAMAGE_TYPE_HEAT] = armorType_heat;
        defenses[main.ARMOR_DAMAGE_TYPE_ELECTRICITY] = armorType_electricity;
        defenses[main.ARMOR_DAMAGE_TYPE_COLD] = armorType_cold;
        defenses[main.ARMOR_DAMAGE_TYPE_ENERGY] = armorType_energy;

        armorRequirement = ArmorRequirement;
        naturalPenalty = NaturalPenalty;
        initiative = Initiative;
        presence = Presence;
        fortitude = Fortitude;
        movementPenalty = MovementPenalty;
        quality = Quality;

        armorRequirement = armorRequirement - quality;
        naturalPenalty = naturalPenalty - quality;
        initiative = initiative + quality;
        presence = presence + (quality * 10);
        fortitude = fortitude + (quality * 2);
        movementPenalty = movementPenalty - (quality / 5);



    }

    public Armor(int ArmorType, int ArmorRequirement, int NaturalPenalty, int Initiative, int Presence, int Fortitude, int MovementPenalty, int Quality, String Comments) {



        armorRequirement = ArmorRequirement;
        naturalPenalty = NaturalPenalty;
        initiative = Initiative;
        presence = Presence;
        fortitude = Fortitude;
        movementPenalty = MovementPenalty;
        quality = Quality;
        comments_and_additional_effects = Comments;

        armorRequirement = armorRequirement - quality;
        naturalPenalty = naturalPenalty - quality;
        initiative = initiative + quality;
        presence = presence + (quality * 10);
        fortitude = fortitude + (quality * 2);
        movementPenalty = movementPenalty - (quality / 5);



    }

    public int getDefenceByType(int damageType) {
        return defenses[damageType];
    }



}
