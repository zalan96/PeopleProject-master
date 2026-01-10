package org.example.people;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Person
{
    private int id;
    private String name;
    private int age;
    private boolean isStudent;
    private int score;
}