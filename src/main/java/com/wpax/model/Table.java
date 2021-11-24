package com.wpax.model;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Table {
    private Class<?> table;
    private ArrayList<Field> columns = new ArrayList<Field>();
    private ArrayList<String> fieldNames;
    private ArrayList<Type> fieldTypes;
    private boolean printsQuery;
    private String queryToExecute;

    public Table(Class<?> table) {
        this.table = table;
        columns = new ArrayList<Field>(Arrays.asList(table.getDeclaredFields()));
        fieldNames = getNamesOfFields();
        fieldTypes = getTypesOfFields();

    }

    public Class<?> getTable() {
        return table;
    }

    public void setTable(Class<?> table) {
        this.table = table;
    }

    public ArrayList<Field> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<Field> columns) {
        this.columns = columns;
    }

    public ArrayList<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(ArrayList<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public ArrayList<Type> getFieldTypes() {
        return fieldTypes;
    }

    public void setFieldTypes(ArrayList<Type> fieldTypes) {
        this.fieldTypes = fieldTypes;
    }

    public boolean isPrintsQuery() {
        return printsQuery;
    }

    public void setPrintsQuery(boolean printsQuery) {
        this.printsQuery = printsQuery;
    }

    public String getQueryToExecute() {
        return queryToExecute;
    }

    public void setQueryToExecute(String queryToExecute) {
        this.queryToExecute = queryToExecute;
    }

    public ArrayList<String> getNamesOfFields() {
        ArrayList<String> list = new ArrayList<String>();
        System.out.println(columns.size());
        for (Field column : columns) {
            column.setAccessible(true);
            try {
                System.out.println(column.getName());
                list.add(column.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public ArrayList<Type> getTypesOfFields() {
        ArrayList<Type> list = new ArrayList<Type>();
        for (Field column : columns) {
            column.setAccessible(true);
            list.add(column.getType());
        }
        return list;
    }

}
