package org.example.coursereview.entities;

import java.util.ArrayList;
import java.util.List;

public class CourseReview {
    private final String code;

    // this currently stores a list of Objects because what we are going to store is undecided at the moment.
    private final List<Object> reviews = new ArrayList<>();

    public CourseReview(String code) {
        this.code = code;
    }

    public void addReview(Object review){
        reviews.add(review);
    }

    public List<Object> getReviews() {
        return reviews;
    }

    public String getCode() {
        return code;
    }
}
