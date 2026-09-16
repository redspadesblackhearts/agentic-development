---
name: init-convention-plugins
description: Create Gradle convention plugins to keep build files small.
example: /init-convention-plugins
---

# Initialize Convention plugins

When this phrase is used, the agent should create the following Gradle convention plugins:
- AndroidApplicationConventionPlugin.kt
- AndroidLibraryConventionPlugin.kt
- AndroidUiConventionPlugin.kt
- AndroidDataConventionPlugin.kt
- AndroidTestConventionPlugin.kt

## Examples
See [examples](examples)

## Versioning

1. Use Versions from the VersionCatalogsExtension, see [AndroidVersions.kt](examples/AndroidVersions.kt)
2. Put versions into the `gradle/libs.versions.toml` file.
