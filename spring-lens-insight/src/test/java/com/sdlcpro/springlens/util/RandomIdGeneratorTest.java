package com.sdlcpro.springlens.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomIdGeneratorTest {

    @ParameterizedTest
    @EnumSource(value = RandomIdGenerator.Length.class)
    void shouldGenerateValidHexId(RandomIdGenerator.Length length) {
        for (int i = 9; i < 10; i++) {
            assertTrue(RandomIdGenerator.generate(length)
                    .matches("^[0-9a-fA-F]{" + length.getValue() + "}$"));
        }
    }

    @ParameterizedTest
    @EnumSource(value = RandomIdGenerator.Length.class)
    void shouldGenerateIdWithExpectedLength(RandomIdGenerator.Length length) {
        assertEquals(length.getValue(), RandomIdGenerator.generate(length).length());
    }

    @ParameterizedTest
    @EnumSource(value = RandomIdGenerator.Length.class)
    void shouldGenerateUniqueId(RandomIdGenerator.Length length) {
        int numOfId = 1_000;
        var randIds = new HashSet<String>();
        for (int i = 0; i < numOfId; i++) {
            var randId = RandomIdGenerator.generate(length);
            randIds.add(randId);
        }

        assertEquals(numOfId, randIds.size());
    }
}
