package com.wpax.service.query;

import com.wpax.builder.QueryReadBuilder;
import com.wpax.dao.Dao;
import com.wpax.model.Filter;
import com.wpax.model.query.QueryRead;
import com.wpax.util.ClassBuilderUtil;
import com.wpax.util.ConvertObjectUtil;

public class QueryReadService {

    private QueryRead query;

    public QueryReadService(QueryRead query) {
        this.query = query;
    }

    public QueryReadService orderByAsc(String... columns) {
        query.setOrderColumns(ClassBuilderUtil.convertStringArrayToFieldArray(query.getTable(), columns));
        query.setOrderedByAsc(true);
        return this;
    };

    public QueryReadService orderByDsc(String... columns) {
        query.setOrderColumns(ClassBuilderUtil.convertStringArrayToFieldArray(query.getTable(), columns));
        query.setOrderedByAsc(false);
        return this;
    };

    public QueryReadService distinct() {
        query.setDisctinct(true);
        return this;
    };

    public QueryReadService count() {
        query.setFunction("COUNT");
        return this;
    };

    public QueryReadService avg() {
        query.setFunction("AVG");
        return this;
    };

    public QueryReadService max() {
        query.setFunction("MAX");
        return this;
    };

    public QueryReadService min() {
        query.setFunction("MIN");
        return this;
    };

    public QueryReadService sum() {
        query.setFunction("SUM");
        return this;
    };

    public QueryReadService reverse() {
        query.setFunction("REVERSE");
        return this;
    };

    public QueryReadService filter(Filter filter) {
        query.setFilter(filter);
        return this;
    }

    public QueryReadService exclude(Filter filter) {

        filter.setExclude(true);
        query.setFilter(filter);
        return this;
    }

    public void printQuery() {
        query.setPrintsQuery(true);
        System.out.println((String) execute());
    }

    public <T> T execute() {
        query = new QueryReadBuilder(query).buildQueryRead();
        return ConvertObjectUtil.castObj(Dao.executeQuery(query), query.getTable());
    }

}
