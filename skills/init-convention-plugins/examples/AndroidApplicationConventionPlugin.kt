import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            val versions = androidVersions()
            extensions.configure<ApplicationExtension>("android") {
                compileSdk {
                    version = release(versions.compileSdk)
                }
                defaultConfig {
                    minSdk = versions.minSdk
                    targetSdk = versions.targetSdk
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
