package com.wpax.builder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import com.wpax.model.Table;

public class TableCreateBuilder {

    private Table table;

    public TableCreateBuilder(Table table) {
        this.table = table;
    }

    public Table buildTableCreate() {

        String queryToExecute = "";

        queryToExecute += "CREATE TABLE \"" + table.getTable().getSimpleName() + "\"";
        queryToExecute += " (" + getColumns() + "); ";

        table.setQueryToExecute(queryToExecute);

        return table;
    }

    private String getColumns() {

        String queryToExecute = "";

        System.out.println(table.getColumns().size());
        for (int i = 0; i < table.getColumns().size(); i++) {
            if (i != 0)
                queryToExecute += ", ";

            queryToExecute += "\"" + table.getFieldNames().get(i) + "\"";
            queryToExecute += " " + toSQLDatatype(table.getFieldTypes().get(i)) + " ";

            Annotation[] ann = table.getColumns().get(i).getAnnotations();

            for (Annotation a : ann) {
                switch (a.annotationType().getSimpleName()) {
                case "P_Key":
                    queryToExecute += " PRIMARY KEY ";
                case "Not_Null":
                    queryToExecute += " NOT NULL ";
                }
            }
        }

        return queryToExecute;
    };

    private String toSQLDatatype(Type type) {
        String typeName = type.getTypeName();

        if (Boolean.TYPE.getName() == typeName)
            return "bool";

        if (Byte.TYPE.getName() == typeName)
            return "bytea";

        if (Short.TYPE.getName() == typeName)
            return "int2";

        if (Integer.TYPE.getName() == typeName)
            return "int4";

        if (Long.TYPE.getName() == typeName)
            return "int8";

        if (Float.TYPE.getName() == typeName)
            return "float4";

        if (Double.TYPE.getName() == typeName)
            return "float8";

        if (String.class.getName() == typeName)
            return "text";

        return null;
    }
}
