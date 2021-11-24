package com.wpax.model.query;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.wpax.util.ClassBuilderUtil;

public class Query {

  private Class<?> table;
  private ArrayList<Field> columns = new ArrayList<Field>();
  private ArrayList<Object> queryParams = new ArrayList<Object>();
  private String queryToExecute = "";
  private boolean printsQuery = false;
  private String function = null;

  public Query(Class<?> table) {
    this.table = table;
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

  public ArrayList<Object> getQueryParams() {
    return queryParams;
  }

  public void setQueryParams(ArrayList<Object> queryParams) {
    this.queryParams = queryParams;
  }

  public String getQueryToExecute() {
    return queryToExecute;
  }

  public void setQueryToExecute(String query) {
    this.queryToExecute = query;
  }

  public boolean isSelectAll() {
    return columns.size() == 0;
  }

  public boolean isPrintsQuery() {
    return printsQuery;
  }

  public void setPrintsQuery(boolean printsQuery) {
    this.printsQuery = printsQuery;
  }

  public String getFunction() {
    return function;
  }

  public void setFunction(String function) {
    this.function = function;
  }

  public boolean hasFunction() {
    return function != null;
  }

  public void addColumn(Field... field) {
    for (Field o : field)
      columns.add(o);
  }

  public void addQueryParam(Object... param) {
    for (Object o : param)
      queryParams.add(o);

  }

  protected ArrayList<Object> getParams(Object o) {
    ArrayList<Object> list = new ArrayList<Object>();

    for (Field column : getColumns()) {
      if (ClassBuilderUtil.isFieldAPrimaryKey(column)) {
        continue;
      }
      System.out.println(column);
      list.add(ClassBuilderUtil.getFieldValue(o, column));
    }

    return list;
  }
}
