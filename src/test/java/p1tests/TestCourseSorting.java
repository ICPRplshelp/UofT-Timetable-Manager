package p1tests;

import org.example.coursegetter.entities.baseclasses.Course;
import org.example.coursegetter.usecases.CourseBuilder;
import org.example.coursegetter.usecases.CourseBuilderDirector;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static java.util.Map.entry;

public class TestCourseSorting {
    private Course returnCourseFromName(String courseName){
        Map<String, Object> courseInfo = Map.ofEntries(
                entry("orgName", "orgName"),
                entry("code", courseName),
                entry("webTimetableInstructions", "webTimetableInstructions"),
                entry("org", "org"),
                entry("session", "session"),
                entry("prerequisite", "prerequisite"),
                entry("exclusion", "exclusion"),
                entry("section", "section"),
                entry("courseDescription", "courseDescription"),
                entry("breadthCategories", "breadthCategories"),
                entry("deliveryInstructions", "deliveryInstructions"),
                entry("courseTitle", "courseTitle"),
                entry("corequisite", "corequisite"),
                entry("meetings", new HashMap<String, Object>())
        );
        CourseBuilderDirector cbd = new CourseBuilderDirector(new CourseBuilder());

        return cbd.construct(courseInfo);
    }

    @Test(timeout = 50)
    public void testCompareCourseLevel() {
        String[] courseList = {"CSC210Y1", "CSC110Y1", "CSC310Y1", "CSC410Y1"};
        String[] expectedCourseList = {"CSC110Y1", "CSC210Y1", "CSC310Y1", "CSC410Y1"};

        ArrayList<Course> courseArrayList = new ArrayList<>();
        for (String courseName: courseList) {
            courseArrayList.add(returnCourseFromName(courseName));
        }

        courseArrayList.sort(Comparator.naturalOrder());
        String[] actualCourseList = new String[courseArrayList.size()];

        for (int i = 0; i < courseArrayList.size(); i++) {
            actualCourseList[i] = courseArrayList.get(i).getCode();
        }

        Assert.assertArrayEquals(expectedCourseList, actualCourseList);
    }

    @Test(timeout = 50)
    public void testCompareCourseDigits() {
        String[] courseList = {"CSC150Y1", "CSC130Y1", "CSC120Y1", "CSC110Y1", "CSC140Y1"};
        String[] expectedCourseList = {"CSC110Y1", "CSC120Y1", "CSC130Y1", "CSC140Y1", "CSC150Y1"};

        ArrayList<Course> courseArrayList = new ArrayList<>();
        for (String courseName: courseList) {
            courseArrayList.add(returnCourseFromName(courseName));
        }

        courseArrayList.sort(Comparator.naturalOrder());
        String[] actualCourseList = new String[courseArrayList.size()];

        for (int i = 0; i < courseArrayList.size(); i++) {
            actualCourseList[i] = courseArrayList.get(i).getCode();
        }

        Assert.assertArrayEquals(expectedCourseList, actualCourseList);
    }

    @Test(timeout = 50)
    public void testCompareCampus() {
        String[] courseList = {"CSC100Y8", "CSC100Y3", "CSC100Y5", "CSC100Y4", "CSC100Y1", "CSC100Y7", "CSC100Y0", "CSC100Y2", "CSC100Y6", "CSC100Y9"};
        String[] expectedCourseList = {"CSC100Y1", "CSC100Y0", "CSC100Y5", "CSC100Y3", "CSC100Y9", "CSC100Y2", "CSC100Y4", "CSC100Y6", "CSC100Y7", "CSC100Y8"};

        ArrayList<Course> courseArrayList = new ArrayList<>();
        for (String courseName: courseList) {
            courseArrayList.add(returnCourseFromName(courseName));
        }

        courseArrayList.sort(Comparator.naturalOrder());
        String[] actualCourseList = new String[courseArrayList.size()];

        for (int i = 0; i < courseArrayList.size(); i++) {
            actualCourseList[i] = courseArrayList.get(i).getCode();
        }

        Assert.assertArrayEquals(expectedCourseList, actualCourseList);
    }

    @Test(timeout = 50)
    public void testCompareAlphabetical() {
        String[] courseList = {"AAA100Y1", "CSC100Y1", "BCB100Y1", "LOL100Y1", "HUH100Y1", "DOG100Y1", "CAT100Y1", "JOB100Y1", "CAD100Y1"};
        String[] expectedCourseList = {"AAA100Y1", "BCB100Y1", "CAD100Y1", "CAT100Y1", "CSC100Y1", "DOG100Y1", "HUH100Y1", "JOB100Y1", "LOL100Y1"};

        ArrayList<Course> courseArrayList = new ArrayList<>();
        for (String courseName: courseList) {
            courseArrayList.add(returnCourseFromName(courseName));
        }

        courseArrayList.sort(Comparator.naturalOrder());
        String[] actualCourseList = new String[courseArrayList.size()];

        for (int i = 0; i < courseArrayList.size(); i++) {
            actualCourseList[i] = courseArrayList.get(i).getCode();
        }

        Assert.assertArrayEquals(expectedCourseList, actualCourseList);
    }
}
