# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation


# R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>


-keep class com.phonegap.voyo.utils.classes.** { *; }

-keep class com.phonegap.voyo.utils.websocket.** { *; }
-keep class com.google.gson.** { *; }
#1. Glide

-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}



#3. Apollo GraphQL
-keep class com.apollographql.apollo.** { *; }
-dontwarn com.apollographql.apollo.**

#4. Retrofit & OkHttp
-keep class retrofit2.* { *; }
-keep class okhttp3.* { *; }
-keep class okio.* { *; }
-dontwarn okio.**
-dontwarn javax.annotation.**
-keepattributes Signature
-keepattributes Exceptions

#5 RxJava
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.*LinkedArrayQueue* {
    long producerIndex;
    long consumerIndex;
}
#6. Sentry
-keep class io.sentry.** { *; }
-dontwarn io.sentry.**

#7. Firebase
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**
-keepattributes *Annotation*
-keepattributes Signature

#8. Navigation Component
-keepclassmembers class * extends androidx.fragment.app.Fragment {
    public void setEnterTransition(java.lang.Object);
    public void setExitTransition(java.lang.Object);
}
#9. Media3 (ExoPlayer)
-dontwarn com.google.android.exoplayer2.**
-keep class com.google.android.exoplayer2.** { *; }
-dontwarn com.google.android.exoplayer2.ui.**
-keep class com.google.android.exoplayer2.ui.** { *; }

#10. WorkManager
-keep class androidx.work.** { *; }
-dontwarn androidx.work.impl.**

#11. Kotlin Coroutines
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**

#12. Leanback
-keep class androidx.leanback.** { *; }
-dontwarn androidx.leanback.**

#13.General Rule
-keepclassmembers class * {
    @androidx.annotation.Keep <methods>;
}
-keepclassmembers class * {
    @androidx.annotation.Keep <fields>;
}

# Keep JMX classes (used by Ktor)
-keep class java.lang.management.** { *; }

# Keep SLF4J classes
-keep class org.slf4j.impl.** { *; }

# Additional rules for missing classes
-dontwarn java.lang.management.ManagementFactory
-dontwarn java.lang.management.RuntimeMXBean
-dontwarn org.slf4j.impl.StaticLoggerBinder
-dontwarn org.slf4j.impl.StaticMDCBinder
-dontwarn org.slf4j.impl.StaticMarkerBinder