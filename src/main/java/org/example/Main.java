package org.example;

import org.example.entities.courserelated.Course;
import org.example.usecases.CourseReader;
import org.example.usecases.FileOpener;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        FileOpener fo = new FileOpener();
        CourseReader cr = new CourseReader();
        String crsJsonAsStr = fo.readFile("src\\coursesCSC.json");
        Map<String, Course> crses = cr.getCourses(crsJsonAsStr);
        CourseStorage cs = new CourseStorage(crses, "20229");
        System.out.println("DONE");
        // System.out.println("FINISHED!!");
    }
}
