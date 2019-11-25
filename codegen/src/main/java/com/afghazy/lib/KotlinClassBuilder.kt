package com.afghazy.lib


/**
 * Created by Ahmed Fathy Ghazy on 11/25/19
 * email ahmedfathyghazy@gmail.com
 * find me at https://github.com/afghazy
 */

class KotlinClassBuilder(
    className: String,
    packageName: String,
    name: String,
    greeting: String
) {
    private val contentTemplate = """
        package $packageName
        
        class $className {
            fun greeting() = "$greeting $name"
        }
    """.trimIndent()

    fun getContent() =
        contentTemplate
}