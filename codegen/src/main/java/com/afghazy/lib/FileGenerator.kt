package com.afghazy.lib

import com.google.auto.service.AutoService
import java.io.File
import java.lang.Error
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic


/**
 * Created by Ahmed Fathy Ghazy on 11/25/19
 * email ahmedfathyghazy@gmail.com
 * find me at https://github.com/afghazy
 */

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(FileGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class FileGenerator : AbstractProcessor() {
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(GreetingGenerator::class.java.name)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latest()
    }

    override fun process(
        set: MutableSet<out TypeElement>?,
        roundEnvironment: RoundEnvironment?
    ): Boolean = with(roundEnvironment?.getElementsAnnotatedWith(GreetingGenerator::class.java)) {
        this?.forEach {
            val className = it.simpleName.toString()
            val pack = processingEnv.elementUtils.getPackageOf(it).toString()
            processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, "$className $pack")

            generateClass(className, pack)
        }
        true
    }

    private fun generateClass(className: String, pack: String) {
        val fileName = "Generated_$className"
        val fileContent = KotlinClassBuilder(fileName, pack).getContent()
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
        val file = File(kaptKotlinGeneratedDir, "$fileName.kt")
        processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, "${file.name}")
        processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, "${file.absoluteFile}")
        processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, "${file.absolutePath}")


        processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, "$fileContent")

        file.writeText(fileContent)
    }


    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }
}