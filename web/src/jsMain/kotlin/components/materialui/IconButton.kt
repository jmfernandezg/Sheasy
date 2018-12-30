@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION",
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE"
)

package components.materialui

import components.StandardProps
import org.w3c.dom.events.Event
import react.RClass
import react.RProps


@JsModule("@material-ui/core/IconButton/IconButton")
external val IconButtonImport: dynamic

external interface IconButtonProps : RProps, StandardProps {
    var color: dynamic /* PropTypes.Color | String /* "textSecondary" */ | String /* "error" */ */ get() = definedExternally; set(value) = definedExternally

}

var IconButton: RClass<IconButtonProps> = IconButtonImport.default
