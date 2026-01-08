package org.example.people;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PersonStatisticsTest {

    private PersonStatistics personStatistics;

    // Helper method to create a person with all fields
    private Person createPerson(int id, String name, int age, boolean isStudent, int score) {
        var person = new Person();
        person.setId(id);
        person.setName(name);
        person.setAge(age);
        person.setStudent(isStudent);
        person.setScore(score);
        return person;
    }

    @Test
    public void test_getAverageAge_withSameAges() {
        var person1 = createPerson(1, "Anna", 20, true, 85);
        var person2 = createPerson(2, "Béla", 20, true, 90);
        var person3 = createPerson(3, "Csaba", 20, false, 75);
        var persons = Arrays.asList(person1, person2, person3);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getAverageAge();

        assertEquals(20.0, result, 0.01);
    }

    @Test
    public void test_getAverageAge_withDifferentAges() {
        var person1 = createPerson(1, "Anna", 18, true, 85);
        var person2 = createPerson(2, "Béla", 22, true, 90);
        var person3 = createPerson(3, "Csaba", 25, false, 75);
        var persons = Arrays.asList(person1, person2, person3);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getAverageAge();

        assertEquals(21.67, result, 0.01);
    }

    @Test
    public void test_getNumberOfStudents_allStudents() {
        var person1 = createPerson(1, "Anna", 20, true, 85);
        var person2 = createPerson(2, "Béla", 22, true, 90);
        var person3 = createPerson(3, "Csaba", 19, true, 75);
        var persons = Arrays.asList(person1, person2, person3);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getNumberOfStudents();

        assertEquals(3, result);
    }

    @Test
    public void test_getNumberOfStudents_mixedStudents() {
        var person1 = createPerson(1, "Anna", 20, true, 85);
        var person2 = createPerson(2, "Béla", 22, false, 90);
        var person3 = createPerson(3, "Csaba", 19, true, 75);
        var person4 = createPerson(4, "Dóra", 25, false, 88);
        var persons = Arrays.asList(person1, person2, person3, person4);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getNumberOfStudents();

        assertEquals(2, result);
    }

    @Test
    public void test_getPersonWithHighestScore_clearWinner() {
        var person1 = createPerson(1, "Anna", 20, true, 41);
        var person2 = createPerson(2, "Béla", 22, true, 50);
        var person3 = createPerson(3, "Csaba", 19, true, 100);
        var persons = Arrays.asList(person1, person2, person3);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getPersonWithHighestScore();

        assertEquals(person3, result);
    }

    @Test
    public void test_getPersonWithHighestScore_differentWinner() {
        var person1 = createPerson(1, "Anna", 20, true, 95);
        var person2 = createPerson(2, "Béla", 22, false, 70);
        var person3 = createPerson(3, "Csaba", 19, true, 85);
        var persons = Arrays.asList(person1, person2, person3);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getPersonWithHighestScore();

        assertEquals(person1, result);
    }

    @Test
    public void test_getAverageScoreOfStudents_allSameScore() {
        var person1 = createPerson(1, "Anna", 20, true, 90);
        var person2 = createPerson(2, "Béla", 22, true, 90);
        var person3 = createPerson(3, "Csaba", 19, true, 90);
        var person4 = createPerson(4, "Dóra", 25, false, 100);
        var persons = Arrays.asList(person1, person2, person3, person4);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getAverageScoreOfStudents();

        assertEquals(90.0, result, 0.01);
    }

    @Test
    public void test_getAverageScoreOfStudents_differentScores() {
        var person1 = createPerson(1, "Anna", 20, true, 75);
        var person2 = createPerson(2, "Béla", 22, true, 85);
        var person3 = createPerson(3, "Csaba", 19, true, 80);
        var person4 = createPerson(4, "Dóra", 25, false, 100);
        var persons = Arrays.asList(person1, person2, person3, person4);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getAverageScoreOfStudents();

        assertEquals(80.0, result, 0.01);
    }

    @Test
    public void test_getOldestStudent_withNonStudents() {
        var person1 = createPerson(1, "Anna", 18, true, 85);
        var person2 = createPerson(2, "Béla", 83, false, 90);
        var person3 = createPerson(3, "Csaba", 22, true, 75);
        var persons = Arrays.asList(person1, person2, person3);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getOldestStudent();

        assertEquals(person3, result);
    }

    @Test
    public void test_getOldestStudent_differentOldest() {
        var person1 = createPerson(1, "Anna", 25, true, 85);
        var person2 = createPerson(2, "Béla", 30, false, 90);
        var person3 = createPerson(3, "Csaba", 19, true, 75);
        var person4 = createPerson(4, "Dóra", 21, true, 88);
        var persons = Arrays.asList(person1, person2, person3, person4);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getOldestStudent();

        assertEquals(person1, result);
    }

    @Test
    public void test_isAnyoneFailing_hasFailingStudent() {
        var person1 = createPerson(1, "Anna", 20, true, 35);
        var person2 = createPerson(2, "Béla", 22, true, 80);
        var person3 = createPerson(3, "Csaba", 19, true, 100);
        var person4 = createPerson(4, "Dóra", 25, false, 25);
        var persons = Arrays.asList(person1, person2, person3, person4);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.isAnyoneFailing();

        assertTrue(result);
    }

    @Test
    public void test_isAnyoneFailing_noFailingStudents() {
        var person1 = createPerson(1, "Anna", 20, true, 90);
        var person2 = createPerson(2, "Béla", 22, true, 80);
        var person3 = createPerson(3, "Csaba", 19, true, 100);
        var person4 = createPerson(4, "Dóra", 25, false, 10);
        var persons = Arrays.asList(person1, person2, person3, person4);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.isAnyoneFailing();

        assertFalse(result);
    }
}