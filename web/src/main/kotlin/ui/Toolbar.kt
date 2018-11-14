package ui

import components.materialui.*
import components.materialui.icons.MenuIcon
import react.*
import react.dom.a
import react.dom.div
import ui.apps.apps
import ui.common.Navigation
import ui.media.MediaView
import ui.notification.NotificationView


interface ToolbarState : RState {
    var open: Boolean
}

class BaseToolbar : RComponent<RProps, ToolbarState>() {

    override fun ToolbarState.init(props: RProps) {
        open = false

    }


    override fun componentDidMount() {

    }

    override fun componentWillUnmount() {

    }

    private fun handleChange() {
        setState {
            open = !open
        }

    }

    override fun RBuilder.render() {

        Drawer {
            attrs {
                open = this@BaseToolbar.state.open
                onClose = { handleChange() }
            }

            a{
                +"HOME"
                attrs {
                    href=Navigation.navigateToHome
                }
            }

            a{
                +"APPS"
                attrs {
                    href=Navigation.navigateToApps
                }
            }

        }

        AppBar {
            attrs {
                position = "static"
            }
            Toolbar {
                IconButton {
                    attrs {
                        color = "inherit"

                        onClick = { handleChange() }

                    }
                    MenuIcon {

                    }

                }
Grid{
    attrs {
        container=true
        spacing=12
    }
    Grid{
        Typography {
            +"Sheasy"
            attrs {
                color = "inherit"
            }
        }
        attrs {
            item=true
            xs = 6
        }
    }
    Grid{
        div {
            MediaView()
            NotificationView()

        }
        attrs {
            item=true
            xs = 6
        }
    }
}





            }
        }

    }
}

fun RBuilder.toolbar() = child(BaseToolbar::class) {
}