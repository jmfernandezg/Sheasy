package de.jensklingenberg.sheasy.legacy.utils.extension

/**
 * Created by jens on 30/3/18.
 */

fun String.remove(string: String): String {
    return this.replace(string, "")
}