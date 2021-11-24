package com.wpax.builder;

import com.wpax.model.query.QueryCreate;
import com.wpax.util.ClassBuilderUtil;

public class QueryCreateBuilder extends QueryBuilder {

    private QueryCreate query;

    public QueryCreateBuilder(QueryCreate query) {
        super(query);
        this.query = query;
    }

    public QueryCreate buildQueryCreate() {

        String queryToExecute = "";
        queryToExecute += "INSERT INTO \"" + query.getTable().getSimpleName() + "\" ";

        queryToExecute += "( " + getColumns() + " ) ";

        queryToExecute += " VALUES ( " + getValues() + ") RETURNING *";

        query.setQueryToExecute(queryToExecute);
        return query;
    }

    public String getValues() {
        String queryToExecute = "";
        System.out.println(query.getQueryParams().size());
        for (int i = 0; i < query.getColumns().size(); i++) {

            if (i != 0) {
                queryToExecute += ", ";
            }
            if (ClassBuilderUtil.isFieldAPrimaryKey(query.getColumns().get(i))) {
                queryToExecute += "DEFAULT";
            } else {
                queryToExecute += "?";
            }
        }

        return queryToExecute;
    }

}
