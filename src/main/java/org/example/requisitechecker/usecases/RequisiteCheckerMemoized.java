package org.example.requisitechecker.usecases;

import org.example.requisitechecker.entities.RequisiteList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RequisiteCheckerMemoized extends RequisiteChecker {

    private final Map<String, RequisiteList> memoizedPrqs = new HashMap<>();
    private final Map<String, RequisiteList> memoizedExcl = new HashMap<>();

    public boolean check(Collection<String> courseAsString, String requisiteAsString) {
        if (memoizedPrqs.containsKey(requisiteAsString)) {
            return memoizedPrqs.get(requisiteAsString).check(courseAsString);
        }
        RequisiteList temp = getPlb().buildRequisiteList(requisiteAsString);
        boolean state = temp.check(courseAsString);
        memoizedPrqs.put(requisiteAsString, temp);
        return state;
    }

    public boolean checkExclusions(Collection<String> courseAsString,
                                   String exclusionsAsString) {
        if (memoizedExcl.containsKey(exclusionsAsString)) {
            return memoizedExcl.get(exclusionsAsString).check(courseAsString);
        }
        RequisiteList temp = getElb().buildRequisiteList(exclusionsAsString);
        boolean state = temp.check(courseAsString);
        memoizedExcl.put(exclusionsAsString, temp);
        return state;
    }
}
