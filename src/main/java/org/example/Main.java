package org.example;

import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.internal.CourseListObtainer;

public class Main {
    public static void main(String[] args) {
        var cgs = new CourseSearcherGetter();
        var cg = cgs.csr;
        System.out.println("DONE");
//        FileOpener fo = new FileOpener();
//        CourseReader cr = new CourseReader();
//        String crsJsonAsStr = fo.readFile("src\\coursesMASTER.json");
//        Map<String, Course> crses = cr.getCourses(crsJsonAsStr);
//        CourseStorage cs = new CourseStorage(crses, "20229");
//        System.out.println("DONE");
        // System.out.println("FINISHED!!");
    }
}
