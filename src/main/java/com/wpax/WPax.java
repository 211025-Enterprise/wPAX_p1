package com.wpax;

import com.wpax.model.*;
import com.wpax.model.query.*;
import com.wpax.service.*;
import com.wpax.service.query.*;

public class WPax {

    public static QueryCreateService create(Object o) {
        return new QueryCreateService(new QueryCreate(o));
    };

    public static QueryReadService get(Class<?> clazz, String... columns) {
        return new QueryReadService(new QueryRead(clazz, columns));
    };

    public static QueryReadService getAll(Class<?> clazz) {
        return new QueryReadService(new QueryRead(clazz, new String[0]));
    };

    public static QueryUpdateService update(Class<?> clazz, String... columns) {
        return new QueryUpdateService(new QueryUpdate(clazz, columns));
    };

    public static QueryDeleteService delete(Class<?> clazz) {
        return new QueryDeleteService(new QueryDelete(clazz));
    };

    public static TableCreateService createTable(Class<?> clazz) {
        return new TableCreateService(new Table(clazz));
    }

    public static Filter where(String comparison) {
        FilterService filterService = new FilterService();
        return filterService.where(comparison);
    }

    public static Filter where(Filter filter) {
        FilterService filterService = new FilterService();
        return filterService.where(filter);
    }

    public static Filter isIn(String field, String... values) {
        FilterService filterService = new FilterService();
        return filterService.isIn(field, values);
    }

    public static Filter isNotIn(String field, String... values) {
        FilterService filterService = new FilterService();
        return filterService.isNotIn(field, values);
    }

    public static Filter between(String field, String value1, String value2) {
        FilterService filterService = new FilterService();
        return filterService.between(field, value1, value2);
    }

    public static Filter startsWith(String field, String value) {
        FilterService filterService = new FilterService();
        return filterService.startsWith(field, value);
    };

    public static Filter endsWith(String field, String value) {
        FilterService filterService = new FilterService();
        return filterService.endsWith(field, value);
    };

    public static Filter contains(String field, String value) {
        FilterService filterService = new FilterService();
        return filterService.contains(field, value);
    };

    // ** STRETCH GOALS **

    // -Additional Functions-

    // First() / Last() / printAll() / executeAll() / deleteMany() / createMany() /
    // updateMany() / AlterTable()

    // -Bonus Features-

    // Transaction Managment: Being/Savepoint/Commit/Rollback
    // Session Caching

    // -Concepts-

    // Relationships, Unions, Stored Procedures
    // Multithreading
    // Error Handeling & Logging
    // Better Code
}
