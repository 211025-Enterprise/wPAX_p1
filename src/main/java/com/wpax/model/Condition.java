package com.wpax.model;

public class Condition {
    private String field;
    private String comparisonOperator;
    private String[] values;
    private String logicalOperator;
    private boolean isUniqueCondition;

    // regualar condition
    public Condition(String field, String comparisonOperator, String values) {
        this.field = field;
        this.values = new String[] { values };
        this.comparisonOperator = comparisonOperator;
        logicalOperator = "";
        isUniqueCondition = false;
    }

    // unique condition
    public Condition(String field, String comparisonOperator, String[] values) {
        this.field = field;
        this.values = values;
        this.comparisonOperator = comparisonOperator;
        logicalOperator = "";
        isUniqueCondition = true;
    }

    public Condition() {

    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(String comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String getLogicalOperator() {
        return logicalOperator;
    }

    public void setLogicalOperator(String logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public boolean isUniqueCondition() {
        return isUniqueCondition;
    }

    public void setUniqueCondition(boolean isUniqueCondition) {
        this.isUniqueCondition = isUniqueCondition;
    }

    @Override
    public String toString() {
        String str = "Condition [\"" + field + comparisonOperator;
        for (String value : values) {
            str += value + " ";
        }
        return str + "\"]";
    }

    public boolean hasLogicalOperator() {
        return logicalOperator != "";
    }
}
