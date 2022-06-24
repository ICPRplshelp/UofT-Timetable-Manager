package org.example;

import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.internal.CourseListObtainer;
import org.example.coursegetter.usecases.internal.CourseSearcher;

public class Main {
    public static void main(String[] args) {
        CourseSearcherGetter csgTemp = new CourseSearcherGetter();
        CourseSearcher courseSearcher = csgTemp.getCourseSearcher();
        // debug here
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
