package com.wpax.service.query;

import com.wpax.builder.QueryDeleteBuilder;
import com.wpax.dao.Dao;
import com.wpax.model.Filter;
import com.wpax.model.query.QueryDelete;
import com.wpax.util.ConvertObjectUtil;

public class QueryDeleteService {

    private QueryDelete query;

    public QueryDeleteService(QueryDelete query) {
        this.query = query;
    }

    public QueryDeleteService filter(Filter filter) {
        query.setFilter(filter);
        return this;
    }

    public QueryDeleteService exclude(Filter filter) {

        filter.setExclude(true);
        query.setFilter(filter);
        return this;
    }

    public void printQuery() {
        query.setPrintsQuery(true);
        System.out.println((String) execute());
    }

    public <T> T execute() {
        query = new QueryDeleteBuilder(query).buildQueryDelete();
        return ConvertObjectUtil.castObj(Dao.executeQuery(query), query.getTable());
    }

}
