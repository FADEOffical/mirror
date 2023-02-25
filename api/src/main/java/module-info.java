/**
 * Module definition for the mirror API.
 */
module fade.mirror {
    requires org.jetbrains.annotations;

    exports fade.mirror;
    exports fade.mirror.internal.impl.filter;
    exports fade.mirror.internal.impl;
    exports fade.mirror.internal.exception;
    exports fade.mirror.filter;
}
