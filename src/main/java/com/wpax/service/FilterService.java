package com.wpax.service;

import java.util.Collections;

import com.wpax.model.Condition;
import com.wpax.model.Filter;

public class FilterService {

    private Filter filter;

    public FilterService(Filter filter) {
        this.filter = filter;
    }

    public FilterService() {
        this.filter = new Filter();
    }

    public Filter where(String comparison) {
        filter.addToListOfConditions(createCondition(comparison));
        return filter;
    }

    public Filter where(Filter filter) {
        this.filter.addToListOfConditions(filter);
        return this.filter;

    }

    public Filter isIn(String field, String... values) {

        filter.addToListOfConditions(new Condition(field, " IN ", values));
        return filter;
    }

    public Filter isNotIn(String field, String... values) {

        String comparisonOperator = " NOT IN (" + String.join(", ", Collections.nCopies(values.length, "?")) + ")";
        filter.addToListOfConditions(new Condition(field, comparisonOperator, values));
        return filter;
    }

    public Filter between(String field, String value1, String value2) {
        filter.addToListOfConditions(new Condition(field, " BETWEEN ? AND ? ", new String[] { value1, value2 }));
        return filter;
    }

    public Filter startsWith(String field, String value) {
        filter.addToListOfConditions(new Condition(field, " LIKE ?", new String[] { value + "%" }));
        return filter;
    };

    public Filter endsWith(String field, String value) {
        filter.addToListOfConditions(new Condition(field, " LIKE ?", new String[] { "%" + value }));
        return filter;

    };

    public Filter contains(String field, String value) {
        filter.addToListOfConditions(new Condition(field, " LIKE ?", new String[] { "%" + value + "%" }));
        return filter;
    };

    public void setExclude() {
        filter.setExclude(true);
    }

    public Filter getFilter() {
        return filter;
    }

    private static Condition createCondition(String comparison) {

        String[] comparisonOperators = { "=", ">", "<", ">=", "<=", "<>" };

        for (String comparisonOperator : comparisonOperators) {
            if (comparison.contains(comparisonOperator)) {

                String[] params = comparison.split(comparisonOperator);
                return new Condition(params[0].trim(), comparisonOperator, params[1].trim());
            }
        }
        return null;
    }
}
