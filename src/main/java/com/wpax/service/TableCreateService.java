package com.wpax.service;

import com.wpax.builder.TableCreateBuilder;
import com.wpax.dao.Dao;
import com.wpax.model.Table;
import com.wpax.util.ConvertObjectUtil;

public class TableCreateService {

    private Table table;

    public TableCreateService(Table table) {
        this.table = table;
    }

    public void printQuery() {
        table.setPrintsQuery(true);
        System.out.println((String) execute());
    }

    public <T> T execute() {

        table = new TableCreateBuilder(table).buildTableCreate();
        System.out.println(table.getQueryToExecute());

        return ConvertObjectUtil.castObj(Dao.executeTableQuery(table), table.getTable());
    }
}
