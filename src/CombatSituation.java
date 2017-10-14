/**
 * Created by teddy on 10/13/2017.
 */
public class CombatSituation {

    public Object attack;
    public Object block;
    public Object dodge;
    public Object initiative;
    public Object physicalAction;

    public Object[] attributes = {attack, block, dodge, initiative, physicalAction};

    public static final int ATTACK = 0;
    public static final int BLOCK = 1;
    public static final int DODGE = 2;
    public static final int INITIATIVE = 3;
    public static final int PHYSICAL_ACTION = 4;



    public CombatSituation(Object Attack, Object Block, Object Dodge, Object Initiative, Object PhysicalAction) {
        attack = Attack;
        dodge = Dodge;
        block = Block;
        initiative = Initiative;
        physicalAction = PhysicalAction;
    }

    public int getAttribute(int tag) throws CombatSituationNull {
        Object tout = attributes[tag];
        if (tout != null) {
            return ((int) tout);
        }
        throw new CombatSituationNull("Attribute is NotPossible or null");
    }

}
