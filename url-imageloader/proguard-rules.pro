-keep class retrofit2.** { *; }

-keep class com.atech.urlimageloader.** { *; }

-keep class com.stripe.android.** { *; }

-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.** { *; }