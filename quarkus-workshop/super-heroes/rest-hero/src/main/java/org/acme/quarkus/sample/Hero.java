package org.acme.quarkus.sample;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@XmlRootElement
@Entity
public class Hero extends PanacheEntity{

    @NotNull
    @Size(min=3,max=50)
    public String name;
    public String otherName;
    @NotNull
    @Min(1)
    public int level;
    public String picture;
    @Column(columnDefinition = "TEXT")
    public String powers;

    public static Hero findRandom() {
        long count=Hero.count();
        Random random=new Random();
        int idx=random.nextInt((int)count);
        return Hero.findAll().page(idx,1).firstResult();
    }

    @Override
    public String toString() {
        return "Hero{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", otherName='" + otherName + '\'' +
            ", level=" + level +
            ", picture='" + picture + '\'' +
            ", powers='" + powers + '\'' +
            '}';
    }
}