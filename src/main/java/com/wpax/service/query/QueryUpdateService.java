package com.wpax.service.query;

import com.wpax.builder.QueryUpdateBuilder;
import com.wpax.dao.Dao;
import com.wpax.model.Filter;
import com.wpax.model.query.QueryUpdate;
import com.wpax.util.ConvertObjectUtil;

public class QueryUpdateService {

    private QueryUpdate query;

    public QueryUpdateService(QueryUpdate query) {

        this.query = query;
    }

    public QueryUpdateService filter(Filter filter) {
        query.setFilter(filter);
        return this;
    }

    public QueryUpdateService exclude(Filter filter) {

        filter.setExclude(true);
        query.setFilter(filter);
        return this;
    }

    public void printQuery() {
        query.setPrintsQuery(true);
        System.out.println((String) execute());
    }

    public <T> T execute() {

        query = new QueryUpdateBuilder(query).buildQueryUpdate();
        return ConvertObjectUtil.castObj(Dao.executeQuery(query), query.getTable());
    }

}
