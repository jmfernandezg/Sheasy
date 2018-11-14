@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE")

package react.materialui

import react.RClass
import react.RProps

@JsModule("@material-ui/core/CardActions/CardActions")
external val CardActionsImport: dynamic

external interface CardActionsProps : RProps {
    var disableActionSpacing: Boolean? get() = definedExternally; set(value) = definedExternally
}

var CardAction: RClass<CardActionsProps> = CardActionsImport.default
