package com.sdlcpro.springlens.util;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomIdGenerator {

    private static final char[] HEX = {
            '0', '1', '2', '3',
            '4', '5', '6', '7',
            '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f'
    };

    private RandomIdGenerator() {
        throw new UnsupportedOperationException("RandomIdGenerator is an utility class and cannot be instantiated");
    }

    public static String generate(Length length) {
        Preconditions.requireNonNull(length, "The value of length must not be null");
        return generateHex(length.getValue());
    }

    private static String generateHex(int length) {
        char[] chars = new char[length];
        var random = ThreadLocalRandom.current();

        int i = 0;
        for (int j = 0; j < length / 2; j++) {
            int b = random.nextInt(256);
            chars[i++] = HEX[(b >>> 4) & 0x0F];
            chars[i++] = HEX[b & 0x0F];
        }

        return new String(chars);
    }

    public enum Length {
        _8(8),
        _16(16),
        _32(32),
        _64(64);

        private final int value;

        Length(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}
