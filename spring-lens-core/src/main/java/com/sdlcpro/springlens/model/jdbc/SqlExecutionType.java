package com.sdlcpro.springlens.model.jdbc;

/**
 * Represents the execution mode of a SQL query tracked by Spring Lens.
 */
public enum SqlExecutionType {

    /**
     * A single SQL statement executed through a normal execution operation.
     */
    SIMPLE,

    /**
     * SQL statements executed through a JDBC batch operation.
     */
    BATCH
}