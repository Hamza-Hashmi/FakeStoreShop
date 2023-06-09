plugins {
    id("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.fakestoreapi"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.fakestoreapi"
        minSdk = 24
        targetSdk =33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility =  JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.8.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // loggin interceptor
//    implementation "com.squareup.okhttp3:logging-interceptor:3.9.1"

    // JSON Converter
    implementation ("com.squareup.retrofit2:converter-gson:2.5.0")
    // ssp
    implementation ("com.intuit.ssp:ssp-android:1.1.0")

    //sdp
    implementation ("com.intuit.sdp:sdp-android:1.1.0")

    //viewmodel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // lifecycle
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    // livedata
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    // coil
    implementation ("io.coil-kt:coil:2.3.0")

    // dependency resoulation
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))

    // shimmer effect

    implementation ("com.facebook.shimmer:shimmer:0.5.0")

    // dataStore preferences

    implementation ("androidx.datastore:datastore-preferences:1.1.0-alpha01")

    // hilt
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-compiler:2.44")


    implementation ("androidx.activity:activity-ktx:1.7.1")
}