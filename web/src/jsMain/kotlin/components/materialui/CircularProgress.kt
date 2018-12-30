@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION",
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE"
)

package components.materialui


import de.jensklingenberg.react.ui.common.LayoutProps
import react.RClass
import react.RProps
import react.ReactElement

@JsModule("@material-ui/core/CircularProgress/CircularProgress")
external val CircularProgressImport: dynamic

interface CircularProgressProps : RProps, LayoutProps {

}


var CircularProgress: RClass<CircularProgressProps> = CircularProgressImport.default
