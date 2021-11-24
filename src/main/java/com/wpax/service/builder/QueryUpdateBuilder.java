package com.wpax.service.builder;

import com.wpax.model.query.QueryUpdate;

public class QueryUpdateBuilder extends QueryBuilder {

    private QueryUpdate query;

    public QueryUpdateBuilder(QueryUpdate query) {
        super(query);
        this.query = query;
    }

    public QueryUpdate buildQueryUpdate() {

        String queryToExecute = "";

        queryToExecute += "UPDATE \"" + query.getTable().getSimpleName() + "\" ";
        queryToExecute += "SET" + getSetParams();
        queryToExecute += query.hasFilter() ? " WHERE(" + getFilter(query.getFilter()) + ") " : " ";
        queryToExecute += "RETURNING *";

        query.setQueryToExecute(queryToExecute);
        return query;
    }

    private String getSetParams() {
        String queryToExecute = "";

        for (int i = 0; i < query.getColumns().size(); i++) {
            if (i != 0) {
                queryToExecute += ", ";
            }

            queryToExecute += "\"" + query.getColumns().get(i).getName() + "\" = ?";
        }

        return queryToExecute;
    }

}
