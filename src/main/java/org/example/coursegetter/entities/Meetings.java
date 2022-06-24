package org.example.coursegetter.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class lists the lectures, tutorials, and practicals for a single
 * course.
 * Note that MAT135H1-F and MAT135H1-S are considered
 * completely different courses for the purposes
 * of defining this class.
 */
public class Meetings {
    // lectures
    // tutorials
    // practicals
    List<Meeting> lectures = new ArrayList<>();
    List<Meeting> tutorials = new ArrayList<>();
    List<Meeting> practicals = new ArrayList<>();

    public Meetings(Map<String, Object> mInfo1){
        for(Object hmv : mInfo1.values()){
            Map<String, Object> hmv2 = (Map<String, Object>) hmv;
            Meeting tempMeeting = new Meeting(hmv2);
            switch(tempMeeting.teachingMethod){
                case "LEC": lectures.add(tempMeeting); break;
                case "TUT": tutorials.add(tempMeeting); break;
                case "PRA": practicals.add(tempMeeting); break;
            }
        }
    }
}
