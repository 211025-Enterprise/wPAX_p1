package com.wpax.builder;

import com.wpax.util.ClassBuilderUtil;
import com.wpax.util.ConvertObjectUtil;
import com.wpax.model.Filter;
import com.wpax.model.query.Query;
import com.wpax.model.Condition;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class QueryBuilder {

    public Query query;

    public QueryBuilder(Query query) {
        this.query = query;
    }

    protected String getColumns() {

        String queryToExecute = "";

        for (int i = 0; i < query.getColumns().size(); i++) {
            if (i != 0) {
                queryToExecute += ", ";
            }

            queryToExecute += "\"" + query.getColumns().get(i).getName() + "\"";
        }
        return queryToExecute;

    }

    protected String getFilter(Filter filter) {

        ArrayList<Object> listOfConditions = filter.getListOfConditions();
        String str = "";

        // for each condition
        for (int i = 0; i < listOfConditions.size(); i++) {

            Class<?> clazz = listOfConditions.get(i).getClass();

            // if condition is a filter, recursively call function
            if (clazz == Filter.class) {
                Filter subFilter = (Filter) listOfConditions.get(i);
                str += "(" + getFilter(subFilter) + ") " + subFilter.getLogicalOperator();

            } else {

                Condition condition = (Condition) listOfConditions.get(i);

                Field field = ClassBuilderUtil.stringToField(query.getTable(), condition.getField());
                String comparisonOperator = condition.getComparisonOperator();
                String logicalOperator = condition.getLogicalOperator();

                if (condition.isUniqueCondition()) {
                    str += " \"" + field.getName() + "\" " + comparisonOperator;

                } else {
                    str += " \"" + field.getName() + "\" " + comparisonOperator + " ? ";
                }

                if (condition.hasLogicalOperator()) {
                    str += " " + logicalOperator + " ";
                }

                try {
                    String typeName = field.getType().getTypeName();
                    for (String value : condition.getValues()) {
                        query.addQueryParam(ConvertObjectUtil.toObject(typeName, value));
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        }

        return str;
    }
}
