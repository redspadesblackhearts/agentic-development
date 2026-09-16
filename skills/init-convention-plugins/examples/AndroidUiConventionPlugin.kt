import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidUiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("trxe.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<LibraryExtension>("android") {
                buildFeatures {
                    compose = true
                }
                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                    }
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", project(":shared:design-system"))
                
                val bom = libs.findLibrary("androidx-compose-bom").get()
                add("implementation", platform(bom))
                
                add("implementation", libs.findBundle("android-ui").get())
                add("debugImplementation", libs.findBundle("android-ui-tooling").get())
                add("testImplementation", libs.findBundle("android-ui-test").get())
            }
        }
    }
}
