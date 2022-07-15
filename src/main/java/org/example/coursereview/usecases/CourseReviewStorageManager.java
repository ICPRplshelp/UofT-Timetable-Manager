package org.example.coursereview.usecases;

import org.example.coursereview.entities.CourseReview;
import org.example.coursereview.entities.CourseReviewStorage;

import java.util.List;

public class CourseReviewStorageManager {
    private final CourseReviewStorage courseReviewStorage;

    public CourseReviewStorageManager(CourseReviewStorage courseReviewStorage){
        this.courseReviewStorage = courseReviewStorage;
    }

    public void createCourseReview(String code){
        courseReviewStorage.addCourseReview(new CourseReview(code));
    }

    public CourseReview getCourseReview(String code){
        return courseReviewStorage.getCourseReview(code);
    }
}
