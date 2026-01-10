package org.example.people;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PersonStatisticsTest {

    private PersonStatistics personStatistics;

    @Test
    public void test_getAverageAge_withSameAges() {
        var person1 = Person.builder().id(1).name("Anna").age(20).isStudent(true).score(85).build();
        var person2 = Person.builder().id(2).name("Béla").age(20).isStudent(true).score(90).build();
        var person3 = Person.builder().id(3).name("Csaba").age(20).isStudent(false).score(75).build();
        var persons = Arrays.asList(person1, person2, person3);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getAverageAge();

        assertEquals(20.0, result, 0.01);
    }

    @Test
    public void test_getAverageAge_withDifferentAges() {
        var person1 = Person.builder().id(1).name("Anna").age(18).isStudent(true).score(85).build();
        var person2 = Person.builder().id(2).name("Béla").age(22).isStudent(true).score(90).build();
        var person3 = Person.builder().id(3).name("Csaba").age(25).isStudent(false).score(75).build();
        var persons = Arrays.asList(person1, person2, person3);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getAverageAge();

        assertEquals(21.67, result, 0.01);
    }

    @Test
    public void test_getNumberOfStudents_allStudents() {
        var person1 = Person.builder().id(1).name("Anna").age(20).isStudent(true).score(85).build();
        var person2 = Person.builder().id(2).name("Béla").age(22).isStudent(true).score(90).build();
        var person3 = Person.builder().id(3).name("Csaba").age(19).isStudent(true).score(75).build();
        var persons = Arrays.asList(person1, person2, person3);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getNumberOfStudents();

        assertEquals(3, result);
    }

    @Test
    public void test_getNumberOfStudents_mixedStudents() {
        var person1 = Person.builder().id(1).name("Anna").age(20).isStudent(true).score(85).build();
        var person2 = Person.builder().id(2).name("Béla").age(22).isStudent(false).score(90).build();
        var person3 = Person.builder().id(3).name("Csaba").age(19).isStudent(true).score(75).build();
        var person4 = Person.builder().id(4).name("Dóra").age(25).isStudent(false).score(88).build();
        var persons = Arrays.asList(person1, person2, person3, person4);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getNumberOfStudents();

        assertEquals(2, result);
    }

    @Test
    public void test_getPersonWithHighestScore_clearWinner() {
        var person1 = Person.builder().id(1).name("Anna").age(20).isStudent(true).score(41).build();
        var person2 = Person.builder().id(2).name("Béla").age(22).isStudent(true).score(50).build();
        var person3 = Person.builder().id(3).name("Csaba").age(19).isStudent(true).score(100).build();
        var persons = Arrays.asList(person1, person2, person3);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getPersonWithHighestScore();

        assertEquals(person3, result);
    }

    @Test
    public void test_getPersonWithHighestScore_differentWinner() {
        var person1 = Person.builder().id(1).name("Anna").age(20).isStudent(true).score(95).build();
        var person2 = Person.builder().id(2).name("Béla").age(22).isStudent(false).score(70).build();
        var person3 = Person.builder().id(3).name("Csaba").age(19).isStudent(true).score(85).build();
        var persons = Arrays.asList(person1, person2, person3);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getPersonWithHighestScore();

        assertEquals(person1, result);
    }

    @Test
    public void test_getAverageScoreOfStudents_allSameScore() {
        var person1 = Person.builder().id(1).name("Anna").age(20).isStudent(true).score(90).build();
        var person2 = Person.builder().id(2).name("Béla").age(22).isStudent(true).score(90).build();
        var person3 = Person.builder().id(3).name("Csaba").age(19).isStudent(true).score(90).build();
        var person4 = Person.builder().id(4).name("Dóra").age(25).isStudent(false).score(100).build();
        var persons = Arrays.asList(person1, person2, person3, person4);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getAverageScoreOfStudents();

        assertEquals(90.0, result, 0.01);
    }

    @Test
    public void test_getAverageScoreOfStudents_differentScores() {
        var person1 = Person.builder().id(1).name("Anna").age(20).isStudent(true).score(75).build();
        var person2 = Person.builder().id(2).name("Béla").age(22).isStudent(true).score(85).build();
        var person3 = Person.builder().id(3).name("Csaba").age(19).isStudent(true).score(80).build();
        var person4 = Person.builder().id(4).name("Dóra").age(25).isStudent(false).score(100).build();
        var persons = Arrays.asList(person1, person2, person3, person4);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getAverageScoreOfStudents();

        assertEquals(80.0, result, 0.01);
    }

    @Test
    public void test_getOldestStudent_withNonStudents() {
        var person1 = Person.builder().id(1).name("Anna").age(18).isStudent(true).score(85).build();
        var person2 = Person.builder().id(2).name("Béla").age(83).isStudent(false).score(90).build();
        var person3 = Person.builder().id(3).name("Csaba").age(22).isStudent(true).score(75).build();
        var persons = Arrays.asList(person1, person2, person3);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getOldestStudent();

        assertEquals(person3, result);
    }

    @Test
    public void test_getOldestStudent_differentOldest() {
        var person1 = Person.builder().id(1).name("Anna").age(25).isStudent(true).score(85).build();
        var person2 = Person.builder().id(2).name("Béla").age(30).isStudent(false).score(90).build();
        var person3 = Person.builder().id(3).name("Csaba").age(19).isStudent(true).score(75).build();
        var person4 = Person.builder().id(4).name("Dóra").age(21).isStudent(true).score(88).build();
        var persons = Arrays.asList(person1, person2, person3, person4);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.getOldestStudent();

        assertEquals(person1, result);
    }

    @Test
    public void test_isAnyoneFailing_hasFailingStudent() {
        var person1 = Person.builder().id(1).name("Anna").age(20).isStudent(true).score(35).build();
        var person2 = Person.builder().id(2).name("Béla").age(22).isStudent(true).score(80).build();
        var person3 = Person.builder().id(3).name("Csaba").age(19).isStudent(true).score(100).build();
        var person4 = Person.builder().id(4).name("Dóra").age(25).isStudent(false).score(25).build();
        var persons = Arrays.asList(person1, person2, person3, person4);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.isAnyoneFailing();

        assertTrue(result);
    }

    @Test
    public void test_isAnyoneFailing_noFailingStudents() {
        var person1 = Person.builder().id(1).name("Anna").age(20).isStudent(true).score(90).build();
        var person2 = Person.builder().id(2).name("Béla").age(22).isStudent(true).score(80).build();
        var person3 = Person.builder().id(3).name("Csaba").age(19).isStudent(true).score(100).build();
        var person4 = Person.builder().id(4).name("Dóra").age(25).isStudent(false).score(10).build();
        var persons = Arrays.asList(person1, person2, person3, person4);

        personStatistics = new PersonStatistics(persons);
        var result = personStatistics.isAnyoneFailing();

        assertFalse(result);
    }
}