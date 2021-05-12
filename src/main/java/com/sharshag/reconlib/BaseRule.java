package com.sharshag.reconlib;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public abstract class BaseRule {

    private RunContext runContext;

    private List<RuleCondition> ruleConditions = new ArrayList<>();
    
    public abstract String ruleName();

    public abstract void applyMatching();
    
}
