object Releases {
    val versionCode = 5
    val versionName = "0.1.2"
}

object DefaultConfig {
    val buildToolsVersion = "30.0.3"
    val appId = "com.christopher_elias.movies"
    val minSdk = 23
    val targetSdk = 30
    val compileSdk = 30
}

object Modules {
    val functionalProgramming = ":functional-programming"
    val testShared = ":test-shared"
    val utils = ":utils"
}

object Core {
    // For Styles, colors & anything COMMON view related.
    val views = ":core:views" // TODO: Rename this to designSystem.
    val network = ":core:network"
    val navigation = ":core:navigation"
}

object Features {
    val movies = ":features:movies"
    val actors = ":features:actors"
}

object Common {
    val models = ":common:models"
    val uiComponents = ":common:ui-components"
}

object Versions {

    const val KOTLIN_VERSION = "1.5.0"
    const val COROUTINES_VERSION = "1.5.0"
    const val GRADLE_TOOLS_VERSION = "4.2.1"

    const val FRAGMENT_VERSION = "1.3.3"
    const val LIFECYCLE_VERSION = "2.3.1"
    const val JETPACK_NAVIGATION_VERSION = "2.3.5"

    const val RETROFIT_VERSION = "2.9.0"
    const val LOGGING_INTERCEPTOR_VERSION = "4.9.0"
    const val MOCK_WEB_SERVER_VERSION = "4.9.0"

    const val COIL_VERSION = "1.1.1"

    const val TIMBER_VERSION = "4.7.1"

    const val TEST_MOCKK_VERSION = "1.11.0"
    const val TEST_JUNIT_VERSION = "4.13.2"

    const val LEAK_CANARY_VERSION = "2.6"

    const val ANDROIDX_CORE_VERSION = "1.3.2"
    const val ANDROIDX_APP_COMPAT_VERSION = "1.2.0"
    const val GOOGLE_MATERIAL_VERSION = "1.3.0"
    const val ANDROIDX_CONSTRAINT_LAYOUT_VERSION = "2.0.4"
    const val ANDROIDX_SWIPE_REFRESH_LAYOUT_VERSION = "1.1.0"
    const val ANDROIDX_FRAGMENT_TESTING_VERSION = "1.3.3"

    const val DI_KOIN_VERSION = "3.0.2"

}

object Libs {

    val gradleTools = "com.android.tools.build:gradle:${Versions.GRADLE_TOOLS_VERSION}"

    // Kotlin
    val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_VERSION}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN_VERSION}"

    // Coroutines
    val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_VERSION}"
    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_VERSION}"
    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_VERSION}"

    val androidxCore = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE_VERSION}"
    val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APP_COMPAT_VERSION}"
    val googleMaterial = "com.google.android.material:material:${Versions.GOOGLE_MATERIAL_VERSION}"
    val androidXConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.ANDROIDX_CONSTRAINT_LAYOUT_VERSION}"

    // Retrofit
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT_VERSION}"
    val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.LOGGING_INTERCEPTOR_VERSION}"

    // MockWebServer
    val mockwebServer = "com.squareup.okhttp3:mockwebserver:${Versions.MOCK_WEB_SERVER_VERSION}"

    // Timber
    val timber = "com.jakewharton.timber:timber:${Versions.TIMBER_VERSION}"

    // Coil
    val coil = "io.coil-kt:coil:${Versions.COIL_VERSION}"

    // DI Koin
    val koinAndroidx = "io.insert-koin:koin-android:${Versions.DI_KOIN_VERSION}"
    val koinJvmTest = "io.insert-koin:koin-test-junit4:${Versions.DI_KOIN_VERSION}"

    // Fragments
    val fragments = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_VERSION}"
    val viewModels = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VERSION}"

    // SwipeRefresh layout
    val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.ANDROIDX_SWIPE_REFRESH_LAYOUT_VERSION}"

    //Navigation
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.JETPACK_NAVIGATION_VERSION}"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.JETPACK_NAVIGATION_VERSION}"
    val navigationSafeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.JETPACK_NAVIGATION_VERSION}"

    // ||
    // TESTING
    // ||

    // Mockk
    val testMockk = "io.mockk:mockk:${Versions.TEST_MOCKK_VERSION}"
    val testMockkInstrumented = "io.mockk:mockk-android:${Versions.TEST_MOCKK_VERSION}"

    // Junit 4
    val junit4 = "junit:junit:${Versions.TEST_JUNIT_VERSION}"
    val junitExt = "androidx.test.ext:junit:1.1.2"

    // Leak Canary
    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.LEAK_CANARY_VERSION}"

    // Espresso
    val espresso = "androidx.test.espresso:espresso-core:3.3.0"

    // Fragments
    val fragmentTest = "androidx.fragment:fragment-testing:${Versions.ANDROIDX_FRAGMENT_TESTING_VERSION}"

}