plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.proyectoapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.proyectoapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
    implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    val room_version = "2.5.0"

    implementation ("com.google.android.gms:play-services-maps:18.1.0")
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    implementation ("com.google.code.gson:gson:2.10.1")

// To use Kotlin Symbol Processing (KSP)
//ksp("androidx.room:room-compiler:$room_version")

// optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

// optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:$room_version")

// optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:$room_version")

// optional - Guava support for Room, including Optional and ListenableFuture
    implementation("androidx.room:room-guava:$room_version")

// optional - Test helpers
    testImplementation("androidx.room:room-testing:$room_version")

// optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$room_version")

}