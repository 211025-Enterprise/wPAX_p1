package com.wpax.model;

import java.util.ArrayList;

import com.wpax.service.FilterService;

public class Filter {

    private ArrayList<Object> listOfConditions;
    private String logicalOperator;
    private boolean isExclude = false;

    public Filter(Condition condition) {
        listOfConditions = new ArrayList<Object>();
        listOfConditions.add(condition);
    }

    public Filter(Filter filter) {
        listOfConditions = new ArrayList<Object>();
        listOfConditions.add(filter);
    }

    public Filter() {
        listOfConditions = new ArrayList<Object>();

    }

    public void setExclude(boolean isExclude) {
        this.isExclude = isExclude;
    }

    public boolean isExclude() {
        return isExclude;
    }

    public FilterService and() {
        setLogicalOperators("AND");
        return new FilterService(this);
    }

    public FilterService andNot() {
        setLogicalOperators("AND NOT");
        return new FilterService(this);

    }

    public FilterService or() {
        setLogicalOperators("OR");
        return new FilterService(this);

    }

    public FilterService orNot() {
        setLogicalOperators("OR NOT");
        return new FilterService(this);
    }

    public ArrayList<Object> getListOfConditions() {
        return listOfConditions;
    }

    public void setListOfConditions(ArrayList<Object> listOfConditions) {
        this.listOfConditions = listOfConditions;
    }

    public String getLogicalOperator() {
        return logicalOperator;
    }

    public void setLogicalOperator(String logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public void addToListOfConditions(Object... condition) {
        for (Object obj : condition)
            listOfConditions.add(obj);
    }

    private void setLogicalOperators(String operator) {
        Object firstObject = listOfConditions.get(listOfConditions.size() - 1);
        if (firstObject.getClass() == this.getClass()) {
            ((Filter) firstObject).setLogicalOperator(operator);
        } else {
            ((Condition) firstObject).setLogicalOperator(operator);
        }
    }
}
