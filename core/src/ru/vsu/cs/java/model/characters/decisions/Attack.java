package ru.vsu.cs.java.model.characters.decisions;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.PersonState;
import ru.vsu.cs.java.model.characters.Profession;

/**
 * Created by serebryanskiysergei on 07-Oct-14.
 */
public class Attack extends Decision {
    public Person opponent;
    @Override
    public void apply(Person person)
    {
        if (opponent != null && opponent.getStatus()!=PersonState.Died)
        {
            if (opponent.getHealth() < person.getEquipment().getDamage()*person.getLvlBonus() -
                    opponent.getEquipment().getStandUp()*opponent.getLvlBonus())
            {
                opponent.setHealth(0);
                opponent.setStatus(PersonState.Died);
                person.addExp(100);
                if (person.getProfession() == Profession.Robber)
                {
                    person.addMoney(opponent.takeAllMoney());
                    if (person.getEquipment().equipCompare(opponent.getEquipment().getWeapon()))
                        person.getEquipment().сhangeWeapon(opponent.getEquipment().getWeapon());
                    if (person.getEquipment().equipCompare(opponent.getEquipment().getArmor()))
                        person.getEquipment().сhangeArmor(opponent.getEquipment().getArmor());
                }
                person.setStatus(PersonState.LastActionCompleted);
                person.setOpponent(null);
                opponent.setOpponent(null);
            }
            else
            {
                opponent.changeHp(-(person.getEquipment().getDamage() * person.getLvlBonus() -
                        opponent.getEquipment().getStandUp() * opponent.getLvlBonus()));
                person.getEquipment().weaponUsed();
                opponent.getEquipment().armorUsed();
            }
        }
        else{
            person.setStatus(PersonState.LastActionCompleted);
            person.setOpponent(null);
        }

    }
}
