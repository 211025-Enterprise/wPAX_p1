package com.wpax.service.query;

import com.wpax.builder.QueryCreateBuilder;
import com.wpax.dao.Dao;
import com.wpax.model.query.QueryCreate;
import com.wpax.util.ConvertObjectUtil;

public class QueryCreateService {

    private QueryCreate query;

    public QueryCreateService(QueryCreate query) {
        this.query = query;
    }

    public void printQuery() {
        query.setPrintsQuery(true);
        System.out.println((String) execute());
    }

    public <T> T execute() {
        query = new QueryCreateBuilder(query).buildQueryCreate();
        return ConvertObjectUtil.castObj(Dao.executeQuery(query), query.getTable());
    }

}
