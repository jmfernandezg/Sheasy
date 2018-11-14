package de.jensklingenberg.sheasy.network.routes

import com.squareup.moshi.Moshi
import de.jensklingenberg.sheasy.data.SheasyPreferences
import de.jensklingenberg.sheasy.utils.AppUtils
import de.jensklingenberg.sheasy.utils.FUtils
import de.jensklingenberg.sheasy.utils.extension.toJson
import io.ktor.application.call
import io.ktor.http.ContentDisposition
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.http.content.streamProvider
import io.ktor.request.receiveMultipart
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import java.io.File
import java.io.FileInputStream


fun Route.file(
    appUtils: AppUtils,
    moshi: Moshi,
    sheasyPref: SheasyPreferences,
    futils: FUtils
) {
    route("file") {
        param("apk") {
            get {

                val packageName = call.parameters["apk"] ?: ""

                val apk = appUtils.returnAPK(packageName)
                val fileInputStream = FileInputStream(apk?.sourceDir)
                with(call) {
                    response.header(
                        HttpHeaders.ContentDisposition, ContentDisposition.Attachment.withParameter(
                            ContentDisposition.Parameters.FileName,
                            "$packageName.apk"
                        ).toString()
                    )
                    respond(fileInputStream.readBytes())
                }


            }
        }

        param("upload") {
            post {
                val filePath = call.parameters["upload"] ?: ""


                val multipart = call.receiveMultipart()
                multipart.forEachPart { part ->
                    when (part) {
                        is PartData.FormItem -> {

                        }
                        is PartData.FileItem -> {
                            val ext = File(part.originalFileName).extension

                            val sourceFile = File(filePath)
                            val destinationFile = File(filePath)
                            sourceFile.copyTo(destinationFile, true)

                            part.streamProvider().use { its ->
                                its.copyTo(sourceFile.outputStream())
                            }
                        }

                    }

                }
            }
        }


        param("download") {
            get {
                val filePath = call.parameters["download"] ?: ""

                if (filePath.startsWith("/storage/emulated/0/") == false) {
                    call.respondText(
                        "path not allowed",
                        ContentType.Text.JavaScript
                    )
                }


                if (filePath.contains(".")) {

                    val fileInputStream = FileInputStream(File(filePath))

                    call.respond(
                        fileInputStream.readBytes()
                    )
                } else {
                    //appUtils.sendBroadcast(EventCategory.REQUEST, filePath)

                    val fileList = FUtils.getFilesReponseList(filePath)

                    if (fileList.isEmpty()) {
                        call.respondText(
                            "path not found",
                            ContentType.Text.JavaScript
                        )

                    } else {


                        call.apply {
                            response.header(
                                HttpHeaders.AccessControlAllowOrigin,
                                "*"
                            )
                            respondText(
                                moshi.toJson(fileList),
                                ContentType.Text.JavaScript
                            )
                        }

                    }


                }

            }
        }


        param("shared") {
            get {
                val shared = "/storage/emulated/0/Music"

                val filePath = call.parameters["shared"] ?: ""

                if (filePath.startsWith(shared) == false) {
                    call.respondText(
                        "path not allowed",
                        ContentType.Text.JavaScript
                    )
                }


                if (filePath.contains(".")) {

                    val fileInputStream = FileInputStream(File(filePath))

                    call.respond(
                        fileInputStream.readBytes()
                    )
                } else {

                    val fileList = FUtils.getFilesReponseList(filePath)

                    if (fileList.isEmpty()) {
                        call.respondText(
                            "path not found",
                            ContentType.Text.JavaScript
                        )

                    } else {


                        call.apply {
                            response.header(
                                HttpHeaders.AccessControlAllowOrigin,
                                "*"
                            )
                            respondText(
                                moshi.toJson(fileList),
                                ContentType.Text.JavaScript
                            )
                        }

                    }


                }

            }
        }

    }
}
