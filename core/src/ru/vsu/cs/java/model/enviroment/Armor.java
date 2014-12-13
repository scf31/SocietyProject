package ru.vsu.cs.java.model.enviroment;

/**
 * Броня персонажей.
 */
public class Armor {
    private int standUp;
    private int estatement;

    public int getStandUp() {
        return standUp;
    }

    public void setStandUp(int standUp) {
        this.standUp = standUp;
    }

    public int getEstatement() {
        return estatement;
    }

    public void setEstatement(int estatement) {
        this.estatement = estatement;
    }
    public Armor(int degree)
    {
        switch (degree)
        {
            case 0:
                setStandUp(0);
                setEstatement(0);
                break;
            case 1:
                setStandUp(1);
                setEstatement(10);
                break;
            case 2:
                setStandUp(2);
                setEstatement(20);
                break;
            case 3:
                setStandUp(3);
                setEstatement(3);
                break;
        }
    }
    /** Лучше ли передаваемая броня ? **/
    public boolean Compare(Armor arm)
    {
        if (this.getStandUp() < arm.getStandUp())
            return true;
        else
        if (this.getStandUp() == arm.getStandUp() && this.getEstatement() < arm.getEstatement())
            return true;
        return false;
    }
}
