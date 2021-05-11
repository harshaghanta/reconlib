package com.sharshag.reconlib;

import java.util.HashMap;
import java.util.Map;

public class BaseTransaction {
    

    private Map<String, Object> fieldData = new HashMap<>() ;

    public void setFieldValue(String fieldName, Object value) {
        fieldData.put(fieldName, value);
    }

    public Object getFieldValue(String fieldName) {
        return fieldData.get(fieldName);
    }
    
}
