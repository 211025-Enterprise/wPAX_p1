package com.wpax.model.query;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.wpax.model.Filter;
import com.wpax.util.ClassBuilderUtil;

public class QueryRead extends Query {

    private ArrayList<Field> orderColumns = new ArrayList<Field>();
    private boolean isOrderedByAsc = false;
    private boolean isDisctinct = false;
    private Filter filter = null;

    public QueryRead(Class<?> table, String... columns) {
        super(table);
        setColumns(ClassBuilderUtil.convertStringArrayToFieldArray(this.getTable(), columns));
        setQueryParams(new ArrayList<Object>());
    }

    public ArrayList<Field> getOrderColumns() {
        return orderColumns;
    }

    public void setOrderColumns(ArrayList<Field> arrayList) {
        this.orderColumns = arrayList;
    }

    public boolean isOrderedByAsc() {
        return isOrderedByAsc;
    }

    public void setOrderedByAsc(boolean isOrderedByAsc) {
        this.isOrderedByAsc = isOrderedByAsc;
    }

    public boolean isDisctinct() {
        return isDisctinct;
    }

    public void setDisctinct(boolean isDisctinct) {
        this.isDisctinct = isDisctinct;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public boolean hasFilter() {

        return filter != null;
    }

    public boolean isOrdered() {

        return orderColumns.isEmpty();
    }

    public QueryRead orderByAsc() {
        setOrderedByAsc(true);
        return this;
    };

    public QueryRead orderByDsc() {
        setOrderedByAsc(false);
        return this;
    };

    public QueryRead distinct() {
        setDisctinct(true);
        return this;
    };

    public QueryRead count() {
        setFunction("COUNT");
        return this;
    };

    public QueryRead avg() {
        setFunction("AVG");
        return this;
    };

    public QueryRead max() {
        setFunction("MAX");
        return this;
    };

    public QueryRead min() {
        setFunction("MIN");
        return this;
    };

    public QueryRead sum() {
        setFunction("SUM");
        return this;
    };

    public QueryRead reverse() {
        setFunction("REVERSE");
        return this;
    };

}
