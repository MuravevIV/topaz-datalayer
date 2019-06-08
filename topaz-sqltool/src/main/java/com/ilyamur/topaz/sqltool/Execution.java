package com.ilyamur.topaz.sqltool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

public class Execution {

    private Function<Mapper<?>, List<?>> mappingFunction;

    public Execution(Function<Mapper<?>, List<?>> mappingFunction) {
        this.mappingFunction = mappingFunction;
    }

    public <T> List<T> asMany(Class<T> c) {
        Mapper<T> mapper = getClassMapper(c);
        return (List<T>) mappingFunction.apply(mapper);
    }

    private <T> Mapper<T> getClassMapper(Class<T> c) {
        try {
            Method method = c.getMethod("getMapper");
            Object object = method.invoke(null);
            return (Mapper<T>) object;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassCastException e) {
            throw new RuntimeException("Can not find mapper associated with the entity class: " + c.getName(), e);
        }
    }

    public <T> T asSingle(Class<T> c) {
        List<T> list = asMany(c);
        if (list.isEmpty()) {
            throw new RuntimeException(); // todo specify
        } else if (list.size() > 1) {
            throw new RuntimeException(); // todo specify
        }
        return list.get(0);
    }
}
