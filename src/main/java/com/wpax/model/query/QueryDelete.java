package com.wpax.model.query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import com.wpax.model.Filter;

public class QueryDelete extends Query {

    private Filter filter = null;

    public QueryDelete(Class<?> table) {
        super(table);

        setColumns(new ArrayList<Field>(Arrays.asList(getTable().getDeclaredFields())));
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
}
