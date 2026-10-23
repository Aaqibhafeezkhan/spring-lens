package com.sdlcpro.springlens.model.transaction;

/**
 * Represents Spring's transaction propagation behavior using its standard integer codes.
 *
 * <p>The numeric values correspond to the propagation constants defined by
 * {@code org.springframework.transaction.TransactionDefinition}. Unknown or unsupported
 * values are represented by {@link #UNKNOWN}.</p>
 */
public enum PropagationBehavior {

    /** Fallback for an unrecognized propagation behavior. */
    UNKNOWN(-1),

    /** Support the current transaction, creating one if none exists. */
    REQUIRED(0),

    /** Execute non-transactionally if no transaction exists. */
    SUPPORTS(1),

    /** Require an existing transaction. */
    MANDATORY(2),

    /** Create a new transaction, suspending the current transaction if one exists. */
    REQUIRES_NEW(3),

    /** Execute non-transactionally, suspending the current transaction if one exists. */
    NOT_SUPPORTED(4),

    /** Require that no transaction exists. */
    NEVER(5),

    /** Use a nested transaction when a transaction exists. */
    NESTED(6);

    private static final PropagationBehavior[] VALUES;

    static {
        VALUES = values();
    }

    private final int value;

    /**
     * Creates a propagation behavior with its Spring transaction propagation code.
     *
     * @param value the integer code associated with this behavior
     */
    PropagationBehavior(int value) {
        this.value = value;
    }

    /**
     * Returns the Spring transaction propagation integer code.
     *
     * @return the integer code for this behavior
     */
    public int value() {
        return value;
    }

    /**
     * Resolves a Spring transaction propagation integer code to its enum value.
     *
     * @param value the propagation code to resolve
     * @return the matching behavior, or {@link #UNKNOWN} when the code is outside
     *         the supported range or cannot be mapped
     */
    public static PropagationBehavior from(int value) {
        if (value < 0 || value > 6) {
            return UNKNOWN;
        }

        for (PropagationBehavior behavior : VALUES) {
            if (behavior.value == value) {
                return behavior;
            }
        }

        return UNKNOWN;
    }
}
