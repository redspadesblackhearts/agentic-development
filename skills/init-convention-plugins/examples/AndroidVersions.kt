import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal data class AndroidVersions(
    val compileSdk: Int,
    val minSdk: Int,
    val targetSdk: Int,
    val java: JavaVersion,
)

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.androidVersions(): AndroidVersions {
    val catalog = libs
    return AndroidVersions(
        compileSdk = catalog.findVersion("androidSdk").get().requiredVersion.toInt(),
        minSdk = catalog.findVersion("androidMinSdk").get().requiredVersion.toInt(),
        targetSdk = catalog.findVersion("androidSdk").get().requiredVersion.toInt(),
        java = JavaVersion.toVersion(catalog.findVersion("java").get().requiredVersion),
    )
}
