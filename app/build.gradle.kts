plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.cinemaestudantil_otes06"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.cinemaestudantil_otes06"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation 'com.google.android.gms:play-services-auth:20.7.0'
    implementation 'com.google.api-client:google-api-client:1.32.1'
    implementation 'com.google.api-client:google-api-client-android:1.32.1'
    implementation 'com.google.apis:google-api-services-calendar:v3-rev411-1.25.0'
    
    // Para trabalhar com datas
    implementation 'com.jakewharton.threetenabp:threetenabp:1.4.0'
}
