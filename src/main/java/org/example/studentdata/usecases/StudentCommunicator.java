package org.example.studentdata.usecases;

import org.example.coursegetter.entities.Course;
import org.example.studentdata.entities.CourseChoice;
import org.example.studentdata.entities.Student;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class StudentCommunicator {
    private final Student student;

    public StudentCommunicator(Student student) {
        this.student = student;
    }

    public Collection<String> getPastCourses(){
        Collection<Course> temp = student.getAllPreviousCourses();
        return temp.stream().map(Course::getOfferingCode).collect(Collectors.toCollection(TreeSet::new));
    }

    public Collection<String> getPlannedFCourses(){
        Collection<CourseChoice> temp =  student.getPlannedFCourses();
        return temp.stream().map(cc -> cc.getCourse().getOfferingCode()).collect(Collectors.toCollection(TreeSet::new));
    }

    public Collection<String> getPlannedSCourses(){
        Collection<CourseChoice> temp =  student.getPlannedSCourses();
        return temp.stream().map(cc -> cc.getCourse().getOfferingCode()).collect(Collectors.toCollection(TreeSet::new));
    }

    public Collection<String> getPlannedYCourses(){
        Collection<CourseChoice> temp =  student.getPlannedYCourses();
        return temp.stream().map(cc -> cc.getCourse().getOfferingCode()).collect(Collectors.toCollection(TreeSet::new));
    }
}
