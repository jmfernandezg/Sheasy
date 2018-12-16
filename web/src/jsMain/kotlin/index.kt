import kotlinext.js.require
import kotlinext.js.requireAll
import react.dom.render
import router.hashRouter
import router.route
import router.switch
import ui.App
import ui.about.AboutView
import ui.apps.AppsView
import ui.screenshare.ScreenShareView
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {

    window.onload = {

        kotlinext.js.require("bootstrap/dist/css/bootstrap.min.css")
        requireAll(require.context("kotlin", true, js("/\\.css$/")))

        render(document.getElementById("root")) {

            hashRouter {
                switch {
                    route("/", App::class, exact = true)
                    route("/apps", AppsView::class, exact = true)
                    route("/about", AboutView::class, exact = true)
                    route("/screenshare", ScreenShareView::class, exact = true)

                }
            }
        }
    }
}