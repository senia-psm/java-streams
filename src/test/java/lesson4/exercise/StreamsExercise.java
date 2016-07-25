package lesson4.exercise;

import data.Employee;
import data.JobHistoryEntry;
import data.Person;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class StreamsExercise {
    // https://youtu.be/kxgo7Y4cdA8 Сергей Куксенко и Алексей Шипилёв — Через тернии к лямбдам, часть 1
    // https://youtu.be/JRBWBJ6S4aU Сергей Куксенко и Алексей Шипилёв — Через тернии к лямбдам, часть 2

    // https://youtu.be/O8oN4KSZEXE Сергей Куксенко — Stream API, часть 1
    // https://youtu.be/i0Jr2l3jrDA Сергей Куксенко — Stream API, часть 2


    private static String generateString() {
        final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int maxLength = 10;
        final int length = ThreadLocalRandom.current().nextInt(maxLength) + 1;

        return IntStream.range(0, length)
                .mapToObj(letters::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private static Person generatePerson() {
        return new Person(generateString(), generateString(), 18 + ThreadLocalRandom.current().nextInt(50));
    }

    private static JobHistoryEntry generateJobHistoryEntry() {
        final int maxDuration = 10;
        final int duration = ThreadLocalRandom.current().nextInt(maxDuration) + 1;
        return new JobHistoryEntry(duration, generatePosition(), generateEmployer());
    }

    private static String generateEmployer() {
        final String[] employers = {"epam", "google", "yandex", "abc"};

        return employers[ThreadLocalRandom.current().nextInt(employers.length)];
    }

    private static String generatePosition() {
        final String[] positions = {"dev", "QA", "BA"};

        return positions[ThreadLocalRandom.current().nextInt(positions.length)];
    }

    private static List<JobHistoryEntry> generateJobHistory() {
        int maxLength = 10;
        final int length = ThreadLocalRandom.current().nextInt(maxLength) + 1;

        return Stream.generate(StreamsExercise::generateJobHistoryEntry)
                .limit(length)
                .collect(toList());
    }

    private static Employee generateEmployee() {
        // TODO
        throw new UnsupportedOperationException();
    }

    private static List<Employee> generateEmployeeList() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Test
    public void sumEpamDurations() {
        final List<Employee> employees = generateEmployeeList();

        int expected = 0;

        for (Employee e : employees) {
            for (JobHistoryEntry j : e.getJobHistory()) {
                if (j.getEmployer().equals("epam")) {
                    expected += j.getDuration();
                }
            }
        }

        // TODO
        throw new UnsupportedOperationException();

        // int result = ???
        // assertEquals(expected, result);
    }

    @Test
    public void getAllEpamEmployees() {
        List<Person> epamEmployees = null;// TODO all persons with experience in epam
        throw new UnsupportedOperationException();
    }

    @Test
    public void getEmployeesStartedFromEpam() {
        List<Person> epamEmployees = null;// TODO all persons with first experience in epam
        throw new UnsupportedOperationException();
    }

    // https://github.com/senia-psm/java-streams

    private static class PersonEmployerPair {
        private final Person person;
        private final String employer;

        public PersonEmployerPair(Person person, String employer) {
            this.person = person;
            this.employer = employer;
        }

        public Person getPerson() {
            return person;
        }

        public String getEmployer() {
            return employer;
        }
    }

    @Test
    public void indexByFirstEmployer() {
        Map<String, List<Person>> employeesIndex = getEmployees().stream()
                .flatMap(
                        e -> e.getJobHistory().stream()
                                 .findFirst()
                                 .map(JobHistoryEntry::getEmployer)
                                 .map(empl -> new PersonEmployerPair(e.getPerson(), empl))
                                 .map(Stream::of)
                                 .orElseGet(Stream::empty)
                )
                .collect(groupingBy(PersonEmployerPair::getEmployer,
                        mapping(PersonEmployerPair::getPerson, toList())));// TODO
        throw new UnsupportedOperationException();
    }


    // TODO class PersonEmployerPair

    @Test
    public void employersStuffLists() {
        Map<String, List<Person>> employersStuffLists = null;// TODO
        throw new UnsupportedOperationException();
    }

    private List<Employee> getEmployees() {
        return Arrays.asList(
                new Employee(
                        new Person("John", "Galt", 20),
                        Arrays.asList(
                                new JobHistoryEntry(3, "dev", "epam"),
                                new JobHistoryEntry(2, "dev", "google")
                        )),
                new Employee(
                        new Person("John", "Doe", 21),
                        Arrays.asList(
                                new JobHistoryEntry(4, "BA", "yandex"),
                                new JobHistoryEntry(2, "QA", "epam"),
                                new JobHistoryEntry(2, "dev", "abc")
                        )),
                new Employee(
                        new Person("John", "White", 22),
                        Collections.singletonList(
                                new JobHistoryEntry(6, "QA", "epam")
                        )),
                new Employee(
                        new Person("John", "Galt", 23),
                        Arrays.asList(
                                new JobHistoryEntry(3, "dev", "epam"),
                                new JobHistoryEntry(2, "dev", "google")
                        )),
                new Employee(
                        new Person("John", "Doe", 24),
                        Arrays.asList(
                                new JobHistoryEntry(4, "QA", "yandex"),
                                new JobHistoryEntry(2, "BA", "epam"),
                                new JobHistoryEntry(2, "dev", "abc")
                        )),
                new Employee(
                        new Person("John", "White", 25),
                        Collections.singletonList(
                                new JobHistoryEntry(6, "QA", "epam")
                        )),
                new Employee(
                        new Person("John", "Galt", 26),
                        Arrays.asList(
                                new JobHistoryEntry(3, "dev", "epam"),
                                new JobHistoryEntry(1, "dev", "google")
                        )),
                new Employee(
                        new Person("Bob", "Doe", 27),
                        Arrays.asList(
                                new JobHistoryEntry(4, "QA", "yandex"),
                                new JobHistoryEntry(2, "QA", "epam"),
                                new JobHistoryEntry(2, "dev", "abc")
                        )),
                new Employee(
                        new Person("John", "White", 28),
                        Collections.singletonList(
                                new JobHistoryEntry(6, "BA", "epam")
                        )),
                new Employee(
                        new Person("John", "Galt", 29),
                        Arrays.asList(
                                new JobHistoryEntry(3, "dev", "epam"),
                                new JobHistoryEntry(1, "dev", "google")
                        )),
                new Employee(
                        new Person("John", "Doe", 30),
                        Arrays.asList(
                                new JobHistoryEntry(4, "QA", "yandex"),
                                new JobHistoryEntry(2, "QA", "epam"),
                                new JobHistoryEntry(5, "dev", "abc")
                        )),
                new Employee(
                        new Person("Bob", "White", 31),
                        Collections.singletonList(
                                new JobHistoryEntry(6, "QA", "epam")
                        ))
        );
    }

}
