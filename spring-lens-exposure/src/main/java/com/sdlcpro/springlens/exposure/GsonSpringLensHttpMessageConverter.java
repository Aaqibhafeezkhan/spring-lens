package com.sdlcpro.springlens.exposure;

import com.google.gson.Gson;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class GsonSpringLensHttpMessageConverter extends AbstractSpringLensHttpMessageConverter {
    private final Gson gsonMapper;

    public GsonSpringLensHttpMessageConverter(Gson gsonMapper) {
        this.gsonMapper = gsonMapper;
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
}
