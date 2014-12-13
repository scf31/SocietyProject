package ru.vsu.cs.java.model.enviroment;

/**
 * Кошелек персонажа.
 */
public class Cash {
    private int count;
    public Cash(int n)
    {
        count = n;
    }
    /** Добавить монеты в кошелек **/
    public void add(int n)
    {
        count += n;
    }
    /** Взять из кошелька монеты.**/
    public int get(int n)
    {
        if (count - n >= 0)
        {
            count -= n;
            return n;
        }
        else
        {
            return 0;
        }
    }
    /** Сколько всего денег? **/
    public int quantity()
    {
        return count;
    }
}
