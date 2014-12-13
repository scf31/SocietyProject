package ru.vsu.cs.java.model;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.enviroment.Habitat;

import java.util.Hashtable;

/**
 * Государство . Весь мир собственно.
 */
public class Kingdom
{
    Habitat _settlement;

    private Hashtable<Integer, Person> settlers = new Hashtable<Integer, Person>();
    public Kingdom(int countOfSettlers, int drawAreaWidht, int drawAreaHeight)
    {

        _settlement = new Habitat(settlers, drawAreaWidht, drawAreaHeight);
        PersonFactory factory = new PersonFactory(_settlement);
        for (int i = 0; i <= countOfSettlers; i++)
        {
            Person man = null;
            try {
                man = factory.getMan();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            settlers.put(i, man);
        }
        _settlement.refreshLocation(settlers);
    }
    public Person getCharacter(int id)
    {
        return settlers.get(id);
    }
    /** Получить словарь ID-IPerson всех персонажей мира.**/
    public Hashtable<Integer, Person> getDictionaryOfCharacters()
    {
        return settlers;
    }
    /** Получить контейнер окружающей среды. **/
    public Habitat getHabitat()
    {
        return _settlement;
    }
}


