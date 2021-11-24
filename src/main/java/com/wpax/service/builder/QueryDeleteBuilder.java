package com.wpax.service.builder;

import com.wpax.model.query.QueryDelete;

public class QueryDeleteBuilder extends QueryBuilder {

    private QueryDelete query;

    public QueryDeleteBuilder(QueryDelete query) {
        super(query);
        this.query = query;
    }

    public QueryDelete buildQueryDelete() {

        String queryToExecute = "";
        queryToExecute += "DELETE FROM \"" + query.getTable().getSimpleName() + "\"";

        queryToExecute += query.hasFilter() ? "WHERE(" + getFilter(query.getFilter()) + ") " : " ";

        queryToExecute += " RETURNING *";
        query.setQueryToExecute(queryToExecute);
        return query;
    }
}
