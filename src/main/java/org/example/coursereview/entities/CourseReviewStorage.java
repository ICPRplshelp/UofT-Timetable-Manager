package org.example.coursereview.entities;

import java.util.HashMap;
import java.util.Map;

public class CourseReviewStorage {
    private final Map<String, CourseReview> courseReviews = new HashMap<>();

    public CourseReviewStorage(){

    }

    public void addCourseReview(CourseReview courseReview){
        courseReviews.put(courseReview.getCode(), courseReview);
    }

    public CourseReview getCourseReview(String code){
        return courseReviews.get(code);
    }

}
