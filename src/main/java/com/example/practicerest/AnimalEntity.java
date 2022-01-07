package com.example.practicerest;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="animals")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnimalEntity implements Serializable {

    @Id
    @Column(name="id", nullable = false, updatable = false)
    private String id = UUID.randomUUID().toString();

    @Column(name="name")
    private String name;

    @Column(name="binomial_name")
    private String binomialName;

    @Column(name="description")
    private String description;

    @Column(name="conservations_status")
    private String conservationStatus;


    public AnimalEntity(String name, String binomialName, String s, String s1) {
    }


}
/*
@GeneratedValue(generator = "UUID")
@GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
@Type(type="uuid-char")*/
