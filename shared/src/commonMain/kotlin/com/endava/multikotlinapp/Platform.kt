package com.endava.multikotlinapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform