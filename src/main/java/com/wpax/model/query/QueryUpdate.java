package com.wpax.model.query;

import com.wpax.model.Filter;
import com.wpax.util.ClassBuilderUtil;

public class QueryUpdate extends Query {

    private Filter filter = null;

    public QueryUpdate(Class<?> table, String... columns) {
        super(table);
        setColumnsAndParams(columns);

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

    private void setColumnsAndParams(String[] columns) {
        for (String column : columns) {

            String[] split = column.split("=");
            addColumn(ClassBuilderUtil.stringToField(getTable(), split[0].trim()));
            addQueryParam(split[1].trim());

        }

    }

}
