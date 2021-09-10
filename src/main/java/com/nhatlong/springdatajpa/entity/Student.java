package com.nhatlong.springdatajpa.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName, lastName, email;
}
