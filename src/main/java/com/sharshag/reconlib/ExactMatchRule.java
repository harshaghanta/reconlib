package com.sharshag.reconlib;

import java.lang.reflect.Field;
import java.util.List;

public class ExactMatchRule extends BaseRule {

    private String lhsFieldName;
    private String rhsFieldName;

    public ExactMatchRule(String lhsFieldName, String rhsFieldName) {
        this.lhsFieldName = lhsFieldName;
        this.rhsFieldName = rhsFieldName;
    }

    @Override
    public String ruleName() {

        return ExactMatchRule.class.getSimpleName();
    }

    @Override
    public void applyMatching() {
        
        RunContext runContext = this.getRunContext();
        List<BaseTransaction> lhsItems = runContext.getLHS();
        List<BaseTransaction> rhsItems = runContext.getRHS();

        if(lhsItems.size() == 0 || rhsItems.size() == 0)
            return;

        for (BaseTransaction lhsItem : lhsItems) {

            if( runContext.getMatchedLHSItems().contains(lhsItem.hashCode()))
                continue;

            for (BaseTransaction rhsItem : rhsItems) {

                if( runContext.getMatchedRHSItems().contains(rhsItem.hashCode()))
                    continue;

                try {                    
                    if(lhsItem.getFieldValue(lhsFieldName).equals(rhsItem.getFieldValue(rhsFieldName))) {

                        //Create new matching pair
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

}
