package com.wpax.dao;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

import com.wpax.model.*;
import com.wpax.model.query.Query;
import com.wpax.util.ClassBuilderUtil;
import com.wpax.util.ConnectionUtil;

/**
 * GenericDao used for CRUD-ing objects. Gonna need to add another kind of dao
 * file for creating tables mehhhhhhhhhh how?
 */
public class Dao {

    static final int INITIAL_CAPACITY = 50;
    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    public Dao() {
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            pool.add(ConnectionUtil.getConnection());
        }
    }

    public synchronized static Connection getConnection() throws SQLException {
        if (pool.isEmpty()) {
            pool.add(ConnectionUtil.getConnection());
        }
        return pool.pop();
    }

    public synchronized static void returnConnection(Connection connection) {
        pool.push(connection);
    }

    public static Object executeTableQuery(Table query) {

        try {

            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query.getQueryToExecute());

            if (query.isPrintsQuery()) {
                return query.getQueryToExecute();
            }

            stmt.execute(query.getQueryToExecute());

            returnConnection(conn);
            stmt.close();

            return "Success!";

        } catch (SQLException | IllegalArgumentException | SecurityException e) {

            e.printStackTrace();
            return "Failed!";

        }

    }

    public static Object executeQuery(Query query) {

        ArrayList<Object> list = null;

        try {

            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query.getQueryToExecute());

            for (int i = 1; i < query.getQueryParams().size() + 1; i++) {
                stmt.setObject(i, query.getQueryParams().get(i - 1));
            }

            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();

            int rowcount = 0;

            if (query.hasFunction()) {
                rs.next();
                return rs.getObject(1);
            }

            Object obj = ClassBuilderUtil.createClassInstance(query.getTable());
            while (rs.next()) {
                rowcount++;
                list = (list == null) ? list = new ArrayList<Object>() : list;

                obj = ClassBuilderUtil.createClassInstance(query.getTable());

                for (int col = 1; col <= meta.getColumnCount(); col++) {

                    Field field = obj.getClass().getDeclaredField(meta.getColumnName(col));
                    field.setAccessible(true);
                    field.set(obj, rs.getObject(col));
                }

                list.add(obj);
            }

            if (rowcount == 1) {
                return obj;
            }

            returnConnection(conn);
            rs.close();
            stmt.close();

            return list;

        } catch (SQLException | IllegalArgumentException | SecurityException | NoSuchFieldException
                | IllegalAccessException e) {

            e.printStackTrace();
        }

        return null;
    }
}
