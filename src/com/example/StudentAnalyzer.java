package com.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StudentAnalyzer {
    public static void main(String[] args) {
        try {
            // Загрузка данных из CSV
            List<Student> students = Files.lines(Paths.get("students.csv"))
                    .map(line -> line.split(","))
                    .map(arr -> new Student(arr[0], Integer.parseInt(arr[1]), Double.parseDouble(arr[2]), arr[3]))
                    .collect(Collectors.toList());
            System.out.println("Загружено студентов: " + students.size());

            // 1. Фильтрация студентов с баллом выше 4.0
            List<Student> highAchievers = students.stream()
                    .filter(s -> s.getGrade() > 4.0)
                    .collect(Collectors.toList());
            System.out.println("\nСтуденты с баллом выше 4.0:");
            highAchievers.forEach(System.out::println);

            // 2. Сортировка по убыванию балла
            List<Student> sortedStudents = students.stream()
                    .sorted(Comparator.comparing(Student::getGrade).reversed())
                    .collect(Collectors.toList());
            System.out.println("\nОтсортированные студенты:");
            sortedStudents.forEach(System.out::println);

            // 3. Средний балл по факультетам
            Map<String, Double> facultyGrades = students.stream()
                    .collect(Collectors.groupingBy(
                            Student::getFaculty,
                            Collectors.averagingDouble(Student::getGrade)));
            System.out.println("\nСредний балл по факультетам:");
            facultyGrades.forEach((faculty, avg) -> System.out.println(faculty + ": " + avg));

            // 4. Топ-5 студентов
            List<Student> topStudents = students.stream()
                    .sorted(Comparator.comparing(Student::getGrade).reversed())
                    .limit(5)
                    .collect(Collectors.toList());
            System.out.println("\nТоп-5 студентов:");
            topStudents.forEach(System.out::println);

            // Тестирование производительности (на 10 000 студентов)
            List<Student> testStudents = generateStudents(10000);
            long start = System.nanoTime();
            List<Student> filteredTest = testStudents.stream()
                    .filter(s -> s.getGrade() > 4.0)
                    .collect(Collectors.toList());
            long end = System.nanoTime();
            System.out.println("\nВремя фильтрации 10 000 студентов: " + (end - start) / 1_000_000 + " мс");

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    // Метод для генерации тестовых данных
    private static List<Student> generateStudents(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new Student(
                        "Student" + i,
                        18 + (i % 5),
                        3.0 + (Math.random() * 2.0),
                        "Faculty" + (i % 3)))
                .collect(Collectors.toList());
    }
}