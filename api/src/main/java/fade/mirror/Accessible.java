package fade.mirror;

import fade.mirror.exception.InaccessibleException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.AccessibleObject;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Represents an object that can be accessed. This interface is used to provide methods to check the accessibility of an
 * object and to make it accessible.
 *
 * @param <T> The type of the object.
 * @author fade
 */
public interface Accessible<T extends Accessible<T>> {

    /**
     * Checks if the object is public.
     *
     * @return {@code true} if the object is public, {@code false} otherwise.
     */
    boolean isPublic();

    /**
     * Checks if the object is protected.
     *
     * @return {@code true} if the object is protected, {@code false} otherwise.
     */
    boolean isProtected();

    /**
     * Checks if the object is package-private.
     *
     * @return {@code true} if the object is package-private, {@code false} otherwise.
     */
    boolean isPackagePrivate();

    /**
     * Checks if the object is private.
     *
     * @return {@code true} if the object is private, {@code false} otherwise.
     */
    boolean isPrivate();

    /**
     * Checks if the object is static.
     *
     * @return {@code true} if the object is static, {@code false} otherwise.
     */
    boolean isStatic();

    /**
     * Checks whether the object is accessible, and if it is not, makes it accessible. If the object cannot be made
     * accessible, an exception is thrown.
     *
     * @return the wrapper.
     */
    @SuppressWarnings("unchecked")
    default @NotNull T requireAccessible() {
        this.makeAccessible().throwIfInaccessible(() -> InaccessibleException.from("Object is not accessible"));
        return (T) this;
    }

    default void throwIfInaccessible(@NotNull Supplier<? extends RuntimeException> exception) {
        if (!this.isAccessible()) throw exception.get();
    }


    @NotNull T makeAccessible(@Nullable Object instance);

    /**
     * Makes the object accessible. This method is equivalent to calling {@link AccessibleObject#trySetAccessible()} on
     * the wrapped object.
     *
     * @return the wrapper.
     */
    default @NotNull T makeAccessible() {
        return this.makeAccessible(null);
    }


    /**
     * Checks if the object is accessible. An object is accessible when
     * {@link java.lang.reflect.AccessibleObject#canAccess(Object)} returns {@code true} for the wrapped object.
     *
     * @return {@code true} if the object is accessible, {@code false} otherwise.
     */
    default boolean isAccessible() {
        return this.isAccessible(null);
    }

    boolean isAccessible(@Nullable Object instance);

    @SuppressWarnings("unchecked")
    default @NotNull T requireAccessible(@Nullable Object instance) {
        this.makeAccessible(instance)
                .throwIfInaccessible(instance, () -> InaccessibleException.from("Object is not accessible"));
        return (T) this;
    }

    default void throwIfInaccessible(@Nullable Object instance, @NotNull Supplier<? extends RuntimeException> exception) {
        if (!this.isAccessible(instance)) throw exception.get();
    }

    /**
     * Checks if the object is accessible, and if it is, performs the given action on the object.
     *
     * @param consumer the action to perform.
     * @return the wrapper.
     */
    @NotNull
    @SuppressWarnings("unchecked")
    default T ifAccessible(@NotNull Consumer<T> consumer) {
        if (this.isAccessible()) consumer.accept((T) this);
        return (T) this;
    }

    /**
     * Checks if the object is not accessible, and if it is not, performs the given action on the object.
     *
     * @param consumer the action to perform.
     * @return the wrapper.
     */
    @NotNull
    @SuppressWarnings("unchecked")
    default T ifNotAccessible(@NotNull Consumer<T> consumer) {
        if (!this.isAccessible()) consumer.accept((T) this);
        return (T) this;
    }
}
