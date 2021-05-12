package com.sharshag.reconlib;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RuleCondition {
    
    private String srcSysFieldName;
    private String subSysFieldName;
    private MatchType matchType;

}
