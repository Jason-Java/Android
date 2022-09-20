package com.jason.controlers;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;

@ConfigurationProperties(prefix = "person")
@Controller
public class Person {

    public Person() {
        System.out.println("初始化......");
    }






    private String userName;
    private Boolean boss;
    private Date birth;
    private Integer age;
    private Pet pet;
    private String [] interests;
    private List<String> animal;
    private Map<String,Object> score;
    private Set<Double> salarys;
    private Map<String,List<Pet>> allPets;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public List<String> getAnimal() {
        return animal;
    }

    public void setAnimal(List<String> animal) {
        this.animal = animal;
    }

    public Map<String, Object> getScore() {
        return score;
    }

    public void setScore(Map<String, Object> score) {
        this.score = score;
    }

    public Set<Double> getSalarys() {
        return salarys;
    }

    public void setSalarys(Set<Double> salarys) {
        this.salarys = salarys;
    }

    public Map<String, List<Pet>> getAllPets() {
        return allPets;
    }

    public void setAllPets(Map<String, List<Pet>> allPets) {
        this.allPets = allPets;
    }

    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", boss=" + boss +
                ", birth=" + birth +
                ", age=" + age +
                ", pet=" + pet +
                ", interests=" + Arrays.toString(interests) +
                ", animal=" + animal +
                ", score=" + score +
                ", salarys=" + salarys +
                ", allPets=" + allPets +
                '}';
    }
}
