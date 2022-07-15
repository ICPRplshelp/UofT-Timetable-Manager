package org.example.coursereview.usecases;

import org.example.coursereview.entities.CourseReview;

import java.util.List;

public class CourseReviewManager {
    private final CourseReview courseReview;

    public CourseReviewManager(CourseReview courseReview){
        this.courseReview = courseReview;
    }

    public void addReview(Object object){
        //object.dostuff;
        courseReview.addReview(object);
    }

    public List<Object> getReviews(){
        return courseReview.getReviews();
    }
}
