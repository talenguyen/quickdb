package com.tale.androiddb.core;

import com.google.common.base.Joiner;

/**
 * Created by TALE on 7/7/2014.
 */
public class Column {

    private static final Joiner joiner = Joiner.on(" ").skipNulls();

    private final String name;
    private final Type type;
    private boolean primaryKey;
    private boolean autoincrement;
    private boolean unique;
    private boolean nullable = true;

    private Column(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public static Column with(String name, Type type) {
        if (name == null || name.trim().length() == 0) {
            throw new NullPointerException("name must not be null or empty");
        }
        if (type == null) {
            throw new NullPointerException("type must not be null");
        }
        return new Column(name, type);
    }

    public Column primaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
        return this;
    }

    public Column autoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
        return this;
    }

    public Column unique(boolean unique) {
        this.unique = unique;
        return this;
    }

    public Column nullable(boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    public String build() {
        return joiner.join(name, type, primaryKey ? "primary key" : null, autoincrement ? "autoincrement" : null, unique && !primaryKey ? "unique" : null, (nullable | primaryKey | unique) ? null : "not null");
    }
}
