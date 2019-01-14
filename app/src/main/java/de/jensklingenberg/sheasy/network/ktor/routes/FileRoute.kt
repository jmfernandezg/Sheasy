package de.jensklingenberg.sheasy.network.ktor.routes

import de.jensklingenberg.sheasy.network.HttpMethod
import de.jensklingenberg.sheasy.network.extension.ktorApplicationCall
import de.jensklingenberg.sheasy.network.routehandler.FileRouteHandler
import de.jensklingenberg.sheasy.utils.extension.debugCorsHeader
import de.jensklingenberg.sheasy.model.Resource
import de.jensklingenberg.sheasy.model.checkState
import io.ktor.application.call
import io.ktor.http.ContentDisposition
import io.ktor.http.HttpHeaders
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.http.content.streamProvider
import io.ktor.request.receiveMultipart
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.param
import io.ktor.routing.post
import io.ktor.routing.route
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx2.await
import java.io.InputStream


fun Route.handleFile(fileRouteHandler: FileRouteHandler) {
    route("file") {

        get("getApps") {
            fileRouteHandler
                .getApps()
                .await()
                .run {
                call.response.debugCorsHeader()
                call.respond(Resource.success(this))
            }

        }

        param("apk") {
            get {
                val packageName = call.parameters
                fileRouteHandler
                    .apk(HttpMethod.GET,call.ktorApplicationCall())
                    .checkState(onSuccess = {resource->
                    launch {
                        call.response.header(
                            HttpHeaders.ContentDisposition,
                            ContentDisposition.Attachment.withParameter(
                                ContentDisposition.Parameters.FileName,
                                "$packageName.apk"
                            ).toString()
                        )
                        call.respond(resource.data!!)
                    }
                })
            }
        }

        param("download") {
            get {
                fileRouteHandler.getDownload(call.ktorApplicationCall())
                    .checkState(onSuccess = {
                    launch {
                        call.respond(it)
                    }
                })
            }
        }

        route("shared"){
           get {
               fileRouteHandler
                   .getShared(call.ktorApplicationCall("shared"))
                   .checkState(onSuccess = {
                       launch {
                           call.response.debugCorsHeader()
                           call.respond(it)
                       }
                   })
           }


        }

        param("upload") {
            post {
                val filePath = call.parameters["upload"] ?: ""

                var resource = Resource.error("", "")
                val multipart = call.receiveMultipart()
                multipart.forEachPart { part ->
                    when (part) {
                        is PartData.FormItem -> {

                        }
                        is PartData.FileItem -> {
                            part.streamProvider().use { its: InputStream ->
                                fileRouteHandler.postUpload(filePath, filePath, its)
                            }
                        }
                    }
                }

                resource.checkState(onError = {
                    launch {
                        call.respond(resource)
                    }
                })
            }
        }
    }

}
