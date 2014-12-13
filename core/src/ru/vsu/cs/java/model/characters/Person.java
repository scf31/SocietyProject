package ru.vsu.cs.java.model.characters;

import ru.vsu.cs.java.model.characters.decisions.Decision;
import ru.vsu.cs.java.model.characters.strategies.*;
import ru.vsu.cs.java.model.enviroment.*;

import java.awt.*;

import static ru.vsu.cs.java.model.characters.Profession.*;
import static ru.vsu.cs.java.model.characters.Profession.Warrior;

/**
 * Фундментальый класс человека
 **/
public class Person implements IPersonToStrategy,IPersonToView {

    private int health;
    private int maxHealth;
    private int experience;
    private int level;
    private int speed;
    private int review;
    private int bag;
    private PersonState status;
    private IStrategy strategy;
    private int id;
    private Point location;
    private Profession profession;
    private Equipment equipment;
    private Decision decision;
    private Person opponent;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health<=getMaxHealth())
            this.health = health;
        else
            this.health = getMaxHealth();
    }

    private int getMaxHealth() {
        return maxHealth;
    }

    private void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getExperience() {
        return experience;
    }

    private void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    private void setLevel(int level) {
        this.level = level;
        if (this.level>=1)
            setMaxHealth(getMaxHealth()+25);
    }

    @Override
    public int getSpeed() {
        return speed - (int)(getBag()*0.3);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getBag() {
        return bag;
    }

    private void setBag(int bag) {
        this.bag = bag;
    }

    @Override
    public PersonState getStatus() {
        return status;
    }

    public void setStatus(PersonState status) {
        this.status = status;
    }

    @Override
    public IStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public Profession getProfession() {
        return profession;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Decision getDecision() {
        return decision;
    }

    @Override
    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    @Override
    public Person getOpponent() {
        return opponent;
    }

    @Override
    public void setOpponent(Person opponent) {
        this.opponent = opponent;
    }

    public Person(int id){
        setExperience(0);
        setLevel(0);
        setMaxHealth(90 + (int)(Math.random() * ((110 - 90) + 1)));
        setEquipment(new Equipment(Equipment.StandartEquipment.Weapons.Hands, Equipment.StandartEquipment.Armors.NoneArmor, 0));
        setHealth(getMaxHealth());
        setBag(0);
        setId(id);
        setStatus(PersonState.LastActionCompleted);
        setDecision(Actions.free());
    }

    public void setEqip(Weapon newWeapon, Armor newArmor, int newCash){
        getEquipment().сhangeWeapon(newWeapon);
        getEquipment().сhangeArmor(newArmor);
        getEquipment().newCash(newCash);
    }

    public void setFillingBag(int newbag)
    {
        setBag(newbag);
    }

    public void setProfession(Profession newProf){
        resetExp();
        switch (newProf)
        {
            case Warrior:
                getEquipment().сhangeWeapon(Equipment.StandartEquipment.Weapons.Sword);
                getEquipment().сhangeArmor(Equipment.StandartEquipment.Armors.MediumArmor);
                getEquipment().сhangeCash(10);
                setStrategy(new WarriorStrategy());
                setReview(90);
                setSpeed(80);
                profession = Warrior;
                setStatus(PersonState.LastActionCompleted);
                break;
            case Trader:
                setEquipment(new Equipment(Equipment.StandartEquipment.Weapons.Hands, Equipment.StandartEquipment.Armors.NoneArmor, 100));
                setStrategy(new TraderStrategy());
                setReview(100);
                setSpeed(100);
                profession = Trader;
                setStatus(PersonState.Moving);
                break;
            case Robber:
                getEquipment().newCash(10);
                setReview(110);
                setSpeed( 125);
                setStatus(PersonState.LastActionCompleted);
                setStrategy(new RobberStrategy());
                profession = Robber;
                break;
            case Peasant:
                getEquipment().сhangeWeapon(Equipment.StandartEquipment.Weapons.Hands);
                getEquipment().сhangeArmor(Equipment.StandartEquipment.Armors.NoneArmor);
                getEquipment().newCash(10);
                setStatus(PersonState.LastActionCompleted);
                setReview( 100);
                setSpeed( 100);
                setStrategy(new PeasantStrategy());
                profession =Peasant;
                break;
            case Craftsman:
                setSpeed(80);
                getEquipment().сhangeWeapon(Equipment.StandartEquipment.Weapons.Hands);
                getEquipment().сhangeArmor(Equipment.StandartEquipment.Armors.UsualArmor);
                getEquipment().newCash(10);
                setReview(100);
                setStatus(PersonState.LastActionCompleted);
                setStrategy(new CraftsmanStrategy());
                profession = Craftsman;
                break;
        }
    }

    public int getLvlBonus()
    {
        return getLevel() > 0 ? getLevel() : 1;
    }

    public Decision getLastDecision(){
       return getDecision();
    }

    public int getFillingBag()
    {
        return getBag();
    }

    public void resetExp(){
        setExperience(0);
        setLevel(0);
    }

    public void changeHp(int delta){
        if (getHealth() + delta <= 0)
            setHealth(0);
        else
        {
            setHealth(getHealth()+delta);
        }
    }

    public void addMoney(int delta)
    {
        getEquipment().сhangeCash(delta);
    }

    public int takeMoney(int delta)
    {
        if (delta <= getEquipment().howMoneyInCash())
        {
            getEquipment().сhangeCash(-delta);
            return delta;
        }
        else
            return 0;
    }

    public int takeAllMoney()
    {
        int n = getEquipment().howMoneyInCash();
        getEquipment().сhangeCash(-n);
        return n;
    }

    public void addInBag(int adding)
    {
        setBag(getBag()+adding);
    }

    public void changeStrategy(IStrategy newStrategy)
    {
        setStrategy(newStrategy);
    }

    public void addExp(int x)
    {
        if (getExperience() + x > 1000)
        {
            setLevel(getLevel()+1);
            switch (getProfession())
            {
                case Trader:
                    if (getLevel() == 5)
                    {
                        getEquipment().сhangeArmor(Equipment.StandartEquipment.Armors.UsualArmor);
                        setSpeed(getSpeed()-1);
                    }
                    if (getLevel() == 8)
                    {
                        getEquipment().сhangeArmor(Equipment.StandartEquipment.Armors.MediumArmor);
                        setSpeed(getSpeed()-1);
                    }
                    addMoney(1000);
                    break;
                case Warrior:
                    if (getLevel() == 7 && getEquipment().equipCompare(Equipment.StandartEquipment.Weapons.Axe))
                    {
                        getEquipment().сhangeWeapon(Equipment.StandartEquipment.Weapons.Axe);
                        setSpeed(getSpeed()-2);
                    }
                    if (getLevel() == 5 && getEquipment().equipCompare(Equipment.StandartEquipment.Armors.HardArmor))
                    {
                        getEquipment().сhangeArmor(Equipment.StandartEquipment.Armors.HardArmor);
                        setSpeed(getSpeed()-2);
                    }
                    setMaxHealth(getMaxHealth()+20);
                    break;
                case Robber:
                    if (getLevel() == 7 && getEquipment().equipCompare(Equipment.StandartEquipment.Weapons.Sword))
                    {
                        setSpeed(getSpeed()-2);
                        getEquipment().сhangeWeapon(Equipment.StandartEquipment.Weapons.Sword);
                    }
                    if (getLevel() == 5 && getEquipment().equipCompare(Equipment.StandartEquipment.Armors.MediumArmor))
                    {
                        getEquipment().сhangeArmor(Equipment.StandartEquipment.Armors.MediumArmor);
                        setSpeed(getSpeed()-2);
                    }
                    setSpeed(getSpeed()+1);
                    setMaxHealth(getMaxHealth()+5);
                    break;
                case Peasant:
                    if (getLevel() == 5 && getEquipment().equipCompare(Equipment.StandartEquipment.Armors.UsualArmor))
                    {
                        getEquipment().сhangeArmor(Equipment.StandartEquipment.Armors.UsualArmor);
                        setSpeed(getSpeed()-1);
                    }
                    if (getLevel() == 7 && getEquipment().equipCompare(Equipment.StandartEquipment.Weapons.Dagger))
                    {
                        getEquipment().сhangeWeapon(Equipment.StandartEquipment.Weapons.Dagger);
                        setSpeed(getSpeed()-1);
                    }
                    setMaxHealth(getMaxHealth()+3);
                    break;
                case Craftsman:
                    if (getLevel() == 5 && getEquipment().equipCompare(Equipment.StandartEquipment.Armors.MediumArmor))
                    {
                        getEquipment().сhangeArmor(Equipment.StandartEquipment.Armors.MediumArmor);
                        setSpeed(getSpeed()-1);
                    }
                    if (getLevel() == 7 && getEquipment().equipCompare(Equipment.StandartEquipment.Weapons.Sword))
                    {
                        getEquipment().сhangeWeapon(Equipment.StandartEquipment.Weapons.Sword);
                        setSpeed(getSpeed()-1);
                    }
                    break;
            }
            setExperience(getExperience()+x-1000);
        }
        setExperience(getExperience()+x);
    }
}