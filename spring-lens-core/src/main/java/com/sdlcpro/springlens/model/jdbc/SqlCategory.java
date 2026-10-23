package com.sdlcpro.springlens.model.jdbc;

/**
 * Represents the category of the SQL statement intercepted
 * <p>
 * This enumeration provides a type-safe classification of SQL statements for database and query inspection.
 */
public enum SqlCategory {
    /**
     * A query to fetch data from the database
     */
    SELECT,

    /**
     * A query to insert new data into the database
     */
    INSERT,

    /**
     * A query to modify existing data in the database
     */
    UPDATE,

    /**
     * A query to delete data from the database
     */
    DELETE,

    /**
     * A query to merge data into a target.
     */
    MERGE,

    /**
     * A data definition language query that defines or modifies database structures.
     */
    DDL,

    /**
     * A data control language operation that manages database privileges.
     */
    DCL,

    /**
     * A transaction control language operation that manages transactions.
     */
    TCL,

    /**
     * A statement that invokes a stored procedure or database routine.
     */
    CALL,

    /**
     * A SQL statement that does not match any recognized category.
     */
    OTHER
}