buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")

    }
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")

    kotlin("plugin.serialization") version "1.9.0"
    id ("kotlin-parcelize")
}


android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.test.jetpackcompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.test.jetpackcompose"
        minSdk = 24
        //noinspection EditedTargetSdkVersion
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // BuildConfig personalizado
        buildConfigField("String", "API_BASE_URL", "\"https://reqres.in/api/\"")


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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true

    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true
}


dependencies {

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Conversor Gson para manejar JSON
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// OkHttp para logging de peticiones/respuestas)
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")


    //ViewModel implmentation

   // implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
implementation ("com.google.dagger:hilt-android:2.44")
kapt ("com.google.dagger:hilt-compiler:2.44")

    //hiltViewModel
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
//Screen navigations
    implementation("androidx.navigation:navigation-compose:2.8.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    //Coil para mostrar imagenes desde URL
    implementation("io.coil-kt:coil-compose:2.2.2")



}

