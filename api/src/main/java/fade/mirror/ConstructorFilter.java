package fade.mirror;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.function.Predicate;

public final class ConstructorFilter implements Predicate<MConstructor<?>> {

    private Class<?>[] types;
    private Annotation[] annotations;

    private ConstructorFilter() {}

    public static @NotNull ConstructorFilter create() {
        return new ConstructorFilter();
    }

    public @NotNull ConstructorFilter withParameters(@NotNull Class<?>... types) {
        this.types = types.clone();
        return this;
    }

    public @NotNull ConstructorFilter andAnnotations(@NotNull Annotation... annotations) {
        this.annotations = annotations.clone();
        return this;
    }

    @Override
    public boolean test(@NotNull MConstructor<?> accessor) {
        return accessor.areParametersEqual(this.types) && (this.annotations == null || accessor.areAnnotationsPresent(this.annotations));
    }
}
