package com.wpax.model.query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class QueryCreate extends Query {

    public QueryCreate(Object table) {
        super(table.getClass());

        setColumns(new ArrayList<Field>(Arrays.asList(getTable().getDeclaredFields())));
        setQueryParams(getParams(table));
    }
}
