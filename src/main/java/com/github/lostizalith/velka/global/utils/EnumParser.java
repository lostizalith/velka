package com.github.lostizalith.velka.global.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public final class EnumParser {

    private EnumParser() {
        // Utility classes should not have a public or default constructor.
    }

    public static <T extends Enum> T findEnumValue(final T[] enumValues, final String value) {
        return findEnumValue(enumValues, Enum::name, value);
    }

    public static <T extends Enum> Optional<T> findOptionalEnumValue(final T[] enumValues, final String value) {
        final Function<T, String> valueExtractor = Enum::name;

        return Stream.of(enumValues)
            .filter(v -> valueExtractor.apply(v).equalsIgnoreCase(value))
            .findFirst();
    }

    public static <T extends Enum> T findEnumValue(final T[] enumValues,
                                                   final Function<T, String> valueExtractor,
                                                   final String value) {
        return Stream.of(enumValues)
            .filter(v -> valueExtractor.apply(v).equalsIgnoreCase(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(String.format("Can't build %s from value '%s'. "
                    + "Possible values are %s",
                enumValues[0].getClass().getSimpleName(), value, Arrays.toString(enumValues))));
    }

    public static <T extends Enum> T findEnumValue(final T[] enumValues, final String value,
                                                   final Function<T, Collection<String>> enumValueOptionsExtractor) {
        return findEnumOptionalValue(enumValues, value, enumValueOptionsExtractor)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Can't build %s from value '%s'. "
                    + "Possible values are %s",
                enumValues[0].getClass().getSimpleName(), value, Arrays.toString(enumValues))));
    }

    public static <T extends Enum> T findNullableEnumValue(final T[] enumValues, final String value,
                                                           final Function<T, Collection<String>> enumValueOptionsExtractor) {
        return findEnumOptionalValue(enumValues, value, enumValueOptionsExtractor)
            .orElse(null);
    }

    public static <T extends Enum> Optional<T> findEnumOptionalValue(final T[] enumValues, final String value,
                                                                     final Function<T, Collection<String>> enumValueOptionsExtractor) {
        return Stream.of(enumValues)
            .filter(v -> enumValueOptionsExtractor.apply(v)
                .stream()
                .anyMatch(option -> option.toLowerCase(Locale.getDefault())
                    .equals(value.toLowerCase(Locale.getDefault()))))
            .findFirst();
    }
}
