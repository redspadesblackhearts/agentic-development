---
name: init-convention-plugins
description: Create Gradle convention plugins to keep build files small.
version: 2
---

# Initialize Convention plugins

When this phrase is used, the agent should create Gradle convention plugins.
The goal is to keep the build files small and reusable.


## Examples
- AndroidApplicationConventionPlugin.kt for Android Apps
- AndroidUiConventionPlugin.kt for :feature:ui modules
- AndroidDataConventionPlugin.kt for :feature:data modules
See [examples](examples)

## Versioning

1. Use Versions from the VersionCatalogsExtension, see [AndroidVersions.kt](examples/AndroidVersions.kt)
2. Put versions into the `gradle/libs.versions.toml` file.
