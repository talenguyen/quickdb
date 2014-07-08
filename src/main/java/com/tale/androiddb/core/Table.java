package com.tale.androiddb.core;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by TALE on 7/7/2014.
 */
public class Table {

    private static final Joiner joiner = Joiner.on(",").skipNulls();

    private final String name;
    private List<Column> columns;

    private Table(String name) {
        this.name = name;
    }

    public static Table with(String name) {
        return new Table(name);
    }

    public Table column(Column column) {
        if (column == null) {
            throw new NullPointerException("column must not be null");
        }
        if (columns == null) {
            columns = new ArrayList<Column>();
        }

        columns.add(column);
        return this;
    }

    public Table columns(Column... columns) {
        if (columns == null || columns.length == 0) {
            throw new NullPointerException("columns must not be null");
        }

        if (this.columns == null) {
            this.columns = new ArrayList<Column>();
        }

        this.columns.addAll(Arrays.asList(columns));
        return this;
    }

    public String buildCreateStatement() {
        if (columns == null || columns.size() == 0) {
            throw new NullPointerException("columns is empty");
        }
        final List<String> columnStatements = new ArrayList<String>();
        for (Column column : columns) {
            columnStatements.add(column.build());
        }
        final String columnDeclareStatement = joiner.join(columnStatements);
        return "create table " + name + " (" + columnDeclareStatement + ");";
    }

    public String buildDeleteStatement() {
        return "drop table if exist " + name;
    }
}
