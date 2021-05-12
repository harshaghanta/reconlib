package com.sharshag.reconlib;

import java.util.List;



public class One2One extends BaseRule {


    public One2One() {

    }

    @Override
    public String ruleName() {

        return One2One.class.getSimpleName();
    }

    @Override
    public void applyMatching() {

        RunContext runContext = this.getRunContext();
        List<BaseTransaction> lhsItems = runContext.getLHS();
        List<BaseTransaction> rhsItems = runContext.getRHS();

        if (lhsItems.size() == 0 || rhsItems.size() == 0)
            return;

        for (BaseTransaction lhsItem : lhsItems) {

            if (runContext.getMatchedLHSItems().contains(lhsItem.hashCode()))
                continue;

            for (BaseTransaction rhsItem : rhsItems) {

                if (runContext.getMatchedRHSItems().contains(rhsItem.hashCode()))
                    continue;

                try {

                    boolean conditionsMatched = true;
                    conditionsMatched = evaluateRuleConditions(this.getRuleConditions(), lhsItem, rhsItem);
                    if (conditionsMatched) {

                        // Create new matching pair
                        MatchingPair matchingPair = new MatchingPair(lhsItem, rhsItem);
                        runContext.getMatchedLHSItems().add(lhsItem.hashCode());
                        runContext.getMatchedRHSItems().add(rhsItem.hashCode());
                        runContext.getMatchingPairs().add(matchingPair);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private boolean evaluateRuleConditions(List<RuleCondition> ruleConditions, BaseTransaction srcSysItem, BaseTransaction subSysItem) {
        
        for (RuleCondition ruleCondition : ruleConditions) {
            
            String srcSysFieldName = ruleCondition.getSrcSysFieldName();
            String subSysFieldName = ruleCondition.getSubSysFieldName();
            MatchType matchType = ruleCondition.getMatchType();

            
            if(matchType == MatchType.MATCHES_EXACTLY) {

                boolean matched = evaluateRuleCondition(srcSysItem, subSysItem, srcSysFieldName, subSysFieldName);
                if(!matched)
                    return false;
            }
        }
        return true;
        
    }

    private boolean evaluateRuleCondition(BaseTransaction srcSysItem, BaseTransaction subSysItem, String srcSysFieldName,
            String subSysFieldName) {
        return srcSysItem.getFieldValue(srcSysFieldName).equals(subSysItem.getFieldValue(subSysFieldName));
    }

}
