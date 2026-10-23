package com.sdlcpro.springlens.model.transaction;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PropagationBehaviorTest {

    @Test
    void mapsEverySupportedPropagationCode() {
        assertThat(PropagationBehavior.from(0)).isEqualTo(PropagationBehavior.REQUIRED);
        assertThat(PropagationBehavior.from(1)).isEqualTo(PropagationBehavior.SUPPORTS);
        assertThat(PropagationBehavior.from(2)).isEqualTo(PropagationBehavior.MANDATORY);
        assertThat(PropagationBehavior.from(3)).isEqualTo(PropagationBehavior.REQUIRES_NEW);
        assertThat(PropagationBehavior.from(4)).isEqualTo(PropagationBehavior.NOT_SUPPORTED);
        assertThat(PropagationBehavior.from(5)).isEqualTo(PropagationBehavior.NEVER);
        assertThat(PropagationBehavior.from(6)).isEqualTo(PropagationBehavior.NESTED);
    }

    @Test
    void exposesUnderlyingIntegerCodes() {
        assertThat(PropagationBehavior.UNKNOWN.value()).isEqualTo(-1);
        assertThat(PropagationBehavior.REQUIRED.value()).isEqualTo(0);
        assertThat(PropagationBehavior.SUPPORTS.value()).isEqualTo(1);
        assertThat(PropagationBehavior.MANDATORY.value()).isEqualTo(2);
        assertThat(PropagationBehavior.REQUIRES_NEW.value()).isEqualTo(3);
        assertThat(PropagationBehavior.NOT_SUPPORTED.value()).isEqualTo(4);
        assertThat(PropagationBehavior.NEVER.value()).isEqualTo(5);
        assertThat(PropagationBehavior.NESTED.value()).isEqualTo(6);
    }

    @Test
    void returnsUnknownForNegativeValues() {
        assertThat(PropagationBehavior.from(-1)).isEqualTo(PropagationBehavior.UNKNOWN);
        assertThat(PropagationBehavior.from(Integer.MIN_VALUE)).isEqualTo(PropagationBehavior.UNKNOWN);
    }

    @Test
    void returnsUnknownForValuesAboveSupportedRange() {
        assertThat(PropagationBehavior.from(7)).isEqualTo(PropagationBehavior.UNKNOWN);
        assertThat(PropagationBehavior.from(Integer.MAX_VALUE)).isEqualTo(PropagationBehavior.UNKNOWN);
    }
}
