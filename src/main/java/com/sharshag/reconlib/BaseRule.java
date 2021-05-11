package com.sharshag.reconlib;

public abstract class BaseRule {

    private RunContext runContext;
    
    public abstract String ruleName();

    public abstract void applyMatching();

    public void setRunContext(RunContext runContext) {
        this.runContext = runContext;
    }

    public RunContext getRunContext() {
        return this.runContext;
    }
}
