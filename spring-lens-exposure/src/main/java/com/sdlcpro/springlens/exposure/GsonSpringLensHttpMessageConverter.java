package com.sdlcpro.springlens.exposure;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.sdlcpro.springlens.annotation.SpringLensInternalComponent;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.*;
import java.util.Objects;
import java.util.function.Function;

@SpringLensInternalComponent
public final class GsonSpringLensHttpMessageConverter extends AbstractSpringLensHttpMessageConverter {
    private final Gson gsonMapper;

    public GsonSpringLensHttpMessageConverter() {
        this.gsonMapper = createGsonMapper();
    }

    @Override
    protected Object readInternal(Type resolvedType, Reader reader) throws Exception {
        return this.gsonMapper.fromJson(reader, resolvedType);
    }

    @Override
    protected void writeInternal(Object object, Type type, Writer writer) throws Exception {
        if (type instanceof ParameterizedType) {
            this.gsonMapper.toJson(object, type, writer);
        } else {
            this.gsonMapper.toJson(object, writer);
        }
    }

    private static Gson createGsonMapper() {
        return new GsonBuilder()
                .registerTypeAdapter(Instant.class,
                        new JavaTimeAdapter<>(Instant::toString, Instant::parse, "Instant"))
                .registerTypeAdapter(LocalDate.class,
                        new JavaTimeAdapter<>(LocalDate::toString, LocalDate::parse, "LocalDate"))
                .registerTypeAdapter(LocalDateTime.class,
                        new JavaTimeAdapter<>(LocalDateTime::toString, LocalDateTime::parse, "LocalDateTime"))
                .registerTypeAdapter(OffsetDateTime.class,
                        new JavaTimeAdapter<>(OffsetDateTime::toString, OffsetDateTime::parse, "OffsetDateTime"))
                .registerTypeAdapter(Duration.class,
                        new JavaTimeAdapter<>(Duration::toString, Duration::parse, "Duration"))
                .serializeNulls()
                .disableHtmlEscaping()
                .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                .create();
    }

    private static final class JavaTimeAdapter<T> extends TypeAdapter<T> {
        private final Function<T, String> formatter;
        private final Function<String, T> parser;
        private final String typeName;

        JavaTimeAdapter(Function<T, String> formatter, Function<String, T> parser, String typeName) {
            this.formatter = Objects.requireNonNull(formatter);
            this.parser = Objects.requireNonNull(parser);
            this.typeName = Objects.requireNonNull(typeName);
        }

        @Override
        public void write(JsonWriter out, T value) throws IOException {
            if (out == null) {
                return;
            }

            if (value == null) {
                out.nullValue();
                return;
            }

            out.value(formatter.apply(value));
        }

        @Override
        public T read(JsonReader in) throws IOException {
            if (in == null) {
                return null;
            }

            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }

            String text = in.nextString();
            try {
                return parser.apply(text);
            } catch (Exception e) {
                throw new JsonParseException("Invalid " + typeName + " value '" + text + "' at path " + in.getPath(), e);
            }
        }
    }
}
