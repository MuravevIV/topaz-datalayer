package com.ilyamur.topaz.sqltool;

import java.util.List;
import java.util.function.Function;

public interface BatchMapper<T> extends Function<T, List<Param>> {
}
