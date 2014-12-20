package ru.vsu.cs.java.model;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.enviroment.Habitat;

import java.util.Hashtable;

/**
 * Государство . Весь мир собственно.
 */
public class Kingdom
{
    private Habitat _settlement;
    private Hashtable<Integer, Person> _settlers = new Hashtable<Integer, Person>();

    public Kingdom(int countOfSettlers, int drawAreaWidht, int drawAreaHeight)
    {

        _settlement = new Habitat(drawAreaWidht, drawAreaHeight);
        PersonFactory factory = new PersonFactory(_settlement);
        for (int i = 0; i <countOfSettlers; i++)
            _settlers.put(i,factory.getMan());
    }
    public Hashtable<Integer,Person> getCharacters(){
        return _settlers;
    }
    public Habitat getHabitat()
    {
        return _settlement;
    }
}


