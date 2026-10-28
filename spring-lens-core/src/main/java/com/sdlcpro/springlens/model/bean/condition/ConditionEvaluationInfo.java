package com.sdlcpro.springlens.model.bean.condition;

import com.sdlcpro.springlens.annotation.SpringLensWritable;
import com.sdlcpro.springlens.util.Preconditions;
import java.util.List;

import static com.sdlcpro.springlens.util.DefensiveCopies.immutableListOrEmpty;

/**
 * Complete snapshot of a single condition evaluation, including every individual match that contributed to the final outcome.
 *
 * <p>The {@code matches} list is defensively copied on construction, so instances
 * are deeply immutable and safe to share across contexts and threads.
 *
 * <p>This type is a response model marked with {@link SpringLensWritable}, so it is
 * serialized as the body of Spring-Lens REST responses using the
 * {@code application/vnd.sdlc-pro.spring-lens.v2+json} media type.
 *
 * @param contextId the identifier of the Spring application context
 * @param source    the source class or configuration that was evaluated;
 *                  must not be null
 * @param outcome   the aggregated result of the evaluation; must not be null
 * @param matches   the individual condition checks; a null value is normalized
 *                  to an empty list, and the supplied list is copied
 * @since 1.0
 */
@SpringLensWritable
public record ConditionEvaluationInfo(
        String contextId,
        String source,
        ConditionOutcome outcome,
        List<ConditionMatch> matches
) {

    /**
     * Validates required components and defensively copies {@code matches}.
     *
     * @throws IllegalArgumentException if {@code source} or {@code outcome} is null
     * @throws NullPointerException     if {@code matches} contains a null element
     */

    public ConditionEvaluationInfo {
        Preconditions.notNull(contextId, "The value of contextId must not be bull");
        Preconditions.notNull(source, "The value of source must not be null");
        Preconditions.notNull(outcome, "The value of outcome must not be null");
        matches = immutableListOrEmpty(matches);
    }

}
