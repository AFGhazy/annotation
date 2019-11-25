package com.afghazy.lib

import javax.annotation.processing.SupportedOptions


/**
 * Created by Ahmed Fathy Ghazy on 11/25/19
 * email ahmedfathyghazy@gmail.com
 * find me at https://github.com/afghazy
 */

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class GreetingGenerator(val name: String, val greeting: String)