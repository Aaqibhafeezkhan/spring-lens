package com.sdlcpro.springlens.model.jdbc;

/**
 * Represents the result of a SQL query execution tracked by Spring Lens.
 */
public enum SqlExecutionStatus {

    /**
     * The SQL query was executed successfully.
     */
    SUCCESS,

    /**
     * The SQL query failed during execution.
     */
    FAILED
}