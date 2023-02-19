package fade.mirror.filter;

import fade.mirror.Copyable;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;

/**
 * Represents a filter for fields. The filter can be used to filter fields by type, name and annotations.
 * <p>
 * A new filter can be created using {@link Filter#forFields()} or constructed using the {@link ParameterFilter#copy()}
 * field on an existing filter.
 * </p>
 *
 * @author fade
 */
public interface ParameterFilter
        extends Copyable<ParameterFilter> {

    /**
     * Adds required annotations to this filter. The field filter will only keep fields with the specified
     * annotations.
     * <p>
     * If the annotations are already set, the new annotations will replace the old ones.
     * <br>
     * If the annotations are empty, the filter ignores the annotations.
     * <br>
     * If the annotations are not empty, the filter will only keep fields with the specified annotations.
     * <br>
     * The filter will only keep fields with the specified annotations but ignores all other annotations on the
     * field.
     * </p>
     *
     * @param annotations the annotations of the field
     * @return this filter
     */
    @NotNull ParameterFilter withAnnotations(@NotNull Annotation... annotations);

    /**
     * Clears the annotations of this filter. The filter will not filter by annotations anymore.
     *
     * @return this filter
     */
    @NotNull ParameterFilter clearAnnotations();

    /**
     * Adds a required return type to this filter. The field filter will only keep fields with the specified return
     * type.
     * <p>
     * If the return type is already set, the new return type will replace the old one.
     * </p>
     *
     * @param type the return type of the field
     * @return this filter
     */
    @NotNull ParameterFilter ofType(@NotNull Class<?> type);

    /**
     * Clears the return type of this filter. The filter will not filter by return type anymore.
     *
     * @return this filter
     */
    @NotNull ParameterFilter clearType();

    /**
     * Adds a required name to this filter. The field filter will only keep fields with the specified name.
     * <p>
     * If the name is already set, the new name will replace the old one.
     * <br>
     * If the name is empty, the filter will not keep any fields.
     * <br>
     * If the name is not empty, the filter will only keep fields with the specified name.
     * <br>
     * </p>
     *
     * @param name the name of the field
     * @return this filter
     */
    @NotNull ParameterFilter withName(@NotNull String name);

    /**
     * Clears the name of this filter. The filter will not filter by name anymore.
     *
     * @return this filter
     */
    @NotNull ParameterFilter clearName();

}
