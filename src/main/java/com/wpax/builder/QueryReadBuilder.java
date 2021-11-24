package com.wpax.builder;

import java.util.ArrayList;

import com.wpax.model.query.QueryRead;

public class QueryReadBuilder extends QueryBuilder {

    private QueryRead query;

    public QueryReadBuilder(QueryRead query) {
        super(query);
        this.query = query;
    }

    public QueryRead buildQueryRead() {

        query.setQueryParams(new ArrayList<Object>());

        String queryToExecute;

        queryToExecute = "SELECT " + getFunctions(query.isSelectAll() ? "*" : getColumns());
        queryToExecute += " FROM \"" + query.getTable().getSimpleName() + "\" ";
        queryToExecute += query.hasFilter() ? "WHERE(" + getFilter(query.getFilter()) + ")" : "";
        queryToExecute += query.isOrdered() ? "" : getOrder();

        query.setQueryToExecute(queryToExecute);
        return query;
    }

    private String getFunctions(String selectColumns) {

        String temp = query.isDisctinct() ? " (DISTINCT " + selectColumns + " ) " : selectColumns;
        return query.getFunction() != null ? " " + query.getFunction() + "( " + temp + " ) " : temp;

    }

    private String getOrder() {

        String queryToExecute = "";

        queryToExecute += "ORDER BY ";

        for (int i = 0; i < query.getOrderColumns().size(); i++) {

            if (i != 0) {
                queryToExecute += ", ";
            }
            queryToExecute += "\"" + query.getOrderColumns().get(i).getName() + "\" ";
        }

        queryToExecute += (query.isOrderedByAsc() ? " ASC " : " DESC ");
        return queryToExecute;
    }
}
