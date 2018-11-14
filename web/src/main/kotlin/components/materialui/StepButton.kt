@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE")

package react.materialui

import components.materialui.ButtonProps
import react.RClass

@JsModule("@material-ui/core/StepButton/StepButton")
external val StepButtonImport: dynamic

external interface StepButtonProps : ButtonProps {
    var active: Boolean? get() = definedExternally; set(value) = definedExternally
    var alternativeLabel: Boolean? get() = definedExternally; set(value) = definedExternally
    var completed: Boolean? get() = definedExternally; set(value) = definedExternally
    var icon: dynamic get() = definedExternally; set(value) = definedExternally
    var last: Boolean? get() = definedExternally; set(value) = definedExternally
    var optional: dynamic get() = definedExternally; set(value) = definedExternally
    var orientation: dynamic get() = definedExternally; set(value) = definedExternally
}

var StepButton: RClass<StepButtonProps> = StepButtonImport.default
