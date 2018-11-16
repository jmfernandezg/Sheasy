package de.jensklingenberg.sheasy.network


import de.jensklingenberg.sheasy.data.SheasyPreferences
import de.jensklingenberg.sheasy.network.routes.apps
import de.jensklingenberg.sheasy.network.routes.file
import de.jensklingenberg.sheasy.network.routes.general
import de.jensklingenberg.sheasy.utils.AppsRepository
import de.jensklingenberg.sheasy.utils.FileRepository
import de.jensklingenberg.sheasy.utils.IAppsRepostitoy
import de.jensklingenberg.sheasy.utils.NotificationUtils
import io.ktor.application.ApplicationCallPipeline
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.*
import io.ktor.gson.gson
import io.ktor.http.content.file
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine


fun getNetty(
    sheasyPref: SheasyPreferences,
    notificationUtils: NotificationUtils,
    futils: FileRepository,
    iAppsRepostitoy: IAppsRepostitoy
): NettyApplicationEngine {



    return embeddedServer(Netty, sheasyPref.port) {

        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }

        install(Compression) {
            gzip()
        }
        install(PartialContent) {
            maxRangeCount = 10
        }


        routing {
            route("") {

                intercept(ApplicationCallPipeline.Call) {

                    if (sheasyPref.authorizedDevices.contains(call.request.origin.remoteHost)) {

                    } else {
                        notificationUtils.showConnectionRequest(call.request.origin.remoteHost)
                        sheasyPref.addAuthorizedDevice(call.request.origin.remoteHost)

                    }


                }


                general(futils)

                route(sheasyPref.APIV1) {
                    apps(iAppsRepostitoy)
                    file(iAppsRepostitoy)

                }
            }


        }
    }
}

