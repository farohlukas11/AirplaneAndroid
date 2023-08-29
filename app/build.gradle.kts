plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
}

android {
    namespace = "com.faroh.airplaneandroid"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.faroh.airplaneandroid"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.github.bumptech.glide:glide:4.13.1")

    val room_version = "2.5.2"
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-rxjava2:$room_version")
    testImplementation("androidx.room:room-testing:$room_version")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    //noinspection LifecycleAnnotationProcessorWithJava8
    ksp("androidx.lifecycle:lifecycle-compiler:2.6.1")

    val lifecycle_ktx_version = "2.6.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_ktx_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_ktx_version")

    val rxjava_version = "2.2.19"
    implementation("io.reactivex.rxjava2:rxjava:$rxjava_version")
    val rxandroid_version = "2.1.1"
    implementation("io.reactivex.rxjava2:rxandroid:$rxandroid_version")
    implementation("android.arch.lifecycle:reactivestreams:1.1.1")
    implementation("android.arch.lifecycle:extensions:1.1.1")

    val data_store_version = "1.0.0"
    implementation("androidx.datastore:datastore-preferences:$data_store_version")
    implementation("androidx.datastore:datastore-preferences-rxjava2:$data_store_version")
    implementation("androidx.datastore:datastore-core:$data_store_version")

    val hilt_version = "2.47"
    implementation("com.google.dagger:hilt-android:$hilt_version")
    ksp("com.google.dagger:hilt-android-testing:$hilt_version")
    ksp("com.google.dagger:hilt-compiler:$hilt_version")

    val activity_ktx_version = "1.7.2"
    implementation("androidx.activity:activity-ktx:$activity_ktx_version")
    val fragment_ktx_version = "1.6.1"
    implementation("androidx.fragment:fragment-ktx:$fragment_ktx_version")

    implementation("me.relex:circleindicator:2.1.6")
}