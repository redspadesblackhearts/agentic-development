import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            val versions = androidVersions()
            extensions.configure<LibraryExtension>("android") {
                compileSdk {
                    version = release(versions.compileSdk)
                }
                defaultConfig {
                    minSdk = versions.minSdk
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                compileOptions {
                    sourceCompatibility = versions.java
                    targetCompatibility = versions.java
                }
            }
        }
    }
}
