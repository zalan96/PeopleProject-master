package org.example.people;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class PersonStatistics {
    private List<Person> person;

    public PersonStatistics(List<Person> person) {
        Objects.requireNonNull(person);
        this.person = person;
    }

    public void setPerson(List<Person> person) {
        Objects.requireNonNull(person);
        this.person = person;
    }

    public double getAverageAge() {
        return person.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
    }

    public int getNumberOfStudents() {
        return (int) person.stream()
                .filter(Person::isStudent)
                .count();
    }

    public Person getPersonWithHighestScore() {
        return person.stream()
                .max(Comparator.comparingInt(Person::getScore))
                .orElse(null);
    }

    public double getAverageScoreOfStudents() {
        return person.stream()
                .filter(Person::isStudent)
                .mapToInt(Person::getScore)
                .average()
                .orElse(0.0);
    }

    public Person getOldestStudent() {
        return person.stream()
                .filter(Person::isStudent)
                .max(Comparator.comparingInt(Person::getAge))
                .orElse(null);
    }

    public boolean isAnyoneFailing() {
        return person.stream()
                .anyMatch(p -> p.getScore() < 40);

    }
}
