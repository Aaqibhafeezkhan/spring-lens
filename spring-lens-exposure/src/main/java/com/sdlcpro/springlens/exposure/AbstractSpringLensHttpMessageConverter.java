package com.sdlcpro.springlens.exposure;

import com.sdlcpro.springlens.annotation.SpringLensReadable;
import com.sdlcpro.springlens.annotation.SpringLensWritable;
import com.sdlcpro.springlens.util.ClassInspector;
import org.springframework.core.GenericTypeResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public abstract class AbstractSpringLensHttpMessageConverter extends AbstractGenericHttpMessageConverter<Object> {
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public AbstractSpringLensHttpMessageConverter() {
        super(SpringLensMediaType.APPLICATION_SPRING_LENS_JSON, SpringLensMediaType.APPLICATION_SPRING_LENS_JSON_UTF8);
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return ClassInspector.isAnnotationPresent(clazz, SpringLensReadable.class) && this.canRead(mediaType);
    }

    @Override
    protected boolean canRead(MediaType mediaType) {
        return canResolve(mediaType);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return ClassInspector.isAnnotationPresent(clazz, SpringLensWritable.class) && this.canWrite(mediaType);
    }

    @Override
    protected boolean canWrite(MediaType mediaType) {
        return canResolve(mediaType);
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return readResolved(GenericTypeResolver.resolveType(type, contextClass), inputMessage);
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return readResolved(clazz, inputMessage);
    }

    private Object readResolved(Type resolvedType, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        Reader reader = getReader(inputMessage);
        try {
            return this.readInternal(resolvedType, reader);
        } catch (Exception ex) {
            throw new HttpMessageNotReadableException("Could not read: " + ex.getMessage(), ex, inputMessage);
        }
    }

    @Override
    protected void writeInternal(Object object, @Nullable Type type, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        Writer writer = getWriter(outputMessage);
        try {
            this.writeInternal(object, type, writer);
        } catch (Exception ex) {
            throw new HttpMessageNotWritableException("Could not write: " + ex.getMessage(), ex);
        }
        writer.flush();
    }

    private static boolean canResolve(MediaType mediaType) {
        if (mediaType == null) {
            return false;
        }

        for (MediaType supportedType : SpringLensMediaType.getSpringLensSupportedMediaTypes()) {
            if (supportedType.getType().equals(mediaType.getType())
                    && supportedType.getSubtype().equals(mediaType.getSubtype())
                    && supportedType.getCharset() == mediaType.getCharset()) {
                return true;
            }
        }

        return false;
    }

    private static Reader getReader(HttpInputMessage inputMessage) throws IOException {
        return new InputStreamReader(inputMessage.getBody(), getCharset(inputMessage.getHeaders()));
    }

    private static Writer getWriter(HttpOutputMessage outputMessage) throws IOException {
        return new OutputStreamWriter(outputMessage.getBody(), getCharset(outputMessage.getHeaders()));
    }

    private static Charset getCharset(HttpHeaders headers) {
        Charset charset = headers.getContentType() != null ? headers.getContentType().getCharset() : null;
        return charset != null ? charset : DEFAULT_CHARSET;
    }

    protected abstract Object readInternal(Type resolvedType, Reader reader) throws Exception;

    protected abstract void writeInternal(Object object, Type type, Writer writer) throws Exception;
}
