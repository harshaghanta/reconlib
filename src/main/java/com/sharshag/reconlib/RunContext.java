package com.sharshag.reconlib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class RunContext {
    
    private List<BaseTransaction> lhs;
    private List<BaseTransaction> rhs;
    private HashSet<Integer> matchedLHSItems = new HashSet<>();
    private HashSet<Integer> matchedRHSItems = new HashSet<>();
    private List<MatchingPair> matchingPairs = new ArrayList<>();

    public void setLHS(List<BaseTransaction> lhs) {
        this.lhs = lhs;
    }

    public void setRHS(List<BaseTransaction> rhs) {
        this.rhs = rhs;   
    }

    public List<BaseTransaction> getLHS() {
        return this.lhs;
    }

    public List<BaseTransaction> getRHS() {
        return this.rhs;
    }

    public HashSet<Integer> getMatchedLHSItems() {
        return matchedLHSItems;
    }


    public HashSet<Integer> getMatchedRHSItems() {
        return matchedRHSItems;
    }

    public List<MatchingPair> getMatchingPairs() {
        return this.matchingPairs;
    }

}
