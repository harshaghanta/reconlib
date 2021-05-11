package com.sharshag.reconlib;

import java.util.ArrayList;
import java.util.List;

public class ReconciliationEngine {
    
    private RunContext runContext;
    private List<BaseRule> rules;

    public ReconciliationEngine() {
        this.runContext = new RunContext();
        rules = new ArrayList<>();
    }

    public List<MatchingPair> run(List<BaseTransaction> bts, List<BaseTransaction> cts) {

        this.runContext.setLHS(bts);
        this.runContext.setRHS(cts);

        for (BaseRule rule : rules) {
            rule.applyMatching();
        }

        return this.runContext.getMatchingPairs();
    }

    public void addRule(BaseRule rule) {

        rule.setRunContext(this.runContext);
        this.rules.add(rule);
    }
}
