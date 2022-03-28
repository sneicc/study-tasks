package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        List<Student> list = new ArrayList<>();

        Scanner scanner = new Scanner(new FileInputStream("src/students/students.txt"));
        String line = "";

        Map<Integer, String> inputStudents = new HashMap<>();
        //line = scanner.nextLine();
        int i = 0;
        while (!"END".equals(line = scanner.nextLine())) {
            inputStudents.put(i++, line);
        }
        for (int j = 0; j < inputStudents.size(); j++) {
            String[] tokens = inputStudents.get(j).split("\\s+");
            String name = tokens[0];
            int group = Integer.valueOf(tokens[1]);
            int course = Integer.valueOf(tokens[2]);
            List<Integer> grades = new ArrayList<>();
            Stream.of(tokens).skip(3).limit(5).mapToInt(Integer::parseInt).forEach(grades::add);

            Student student = new Student(name, group, course, grades);
            list.add(student);
        }
        topic();
        list.stream()
                .forEach(l -> System.out.printf("%-10s \t %d \t %d \t %15s \n", l.getName(), l.getGroup(), l.getCourse(), l.getGrades()));
        System.out.println();
        passNewCourse(list);
        System.out.println("Введите курс (от 1 до 4):");
        Scanner scn = new Scanner(System.in);
        int scn1 = scn.nextInt();
        sameCourse(list, scn1);


    }

    public static void passNewCourse(List<Student> list) {
        System.out.println("Прошли на следуюий курс:");
        topic();
        list.removeIf(l -> (l.getGrades().stream().mapToInt(g -> g).sum()) / (l.getGrades().stream().count()) < 3 || l.getCourse() == 4);
        list.stream().forEach(l -> l.setCourse(l.getCourse() + 1));
        list.stream().forEach(l -> System.out.printf("%-10s \t %d \t %d \t %15s \n", l.getName(), l.getGroup(), l.getCourse(), l.getGrades()));
        System.out.println();
    }

    public static void sameCourse(List<Student> list, int course) {
        System.out.println("Список студентов, обучающихся на " + course + " курсе:");
        topic();
        list.stream()
                .filter(l -> l.getCourse() == course)
                .forEach(l -> System.out.printf("%-10s \t %d \t %d \t %15s \n", l.getName(), l.getGroup(), l.getCourse(), l.getGrades()));
        System.out.println();

    }

    public static void topic() {
        System.out.printf("%-1s \s \s %s \s %s \t %s \n", "Имя", "Группа", "Курс", "Оценки");
    }

}
