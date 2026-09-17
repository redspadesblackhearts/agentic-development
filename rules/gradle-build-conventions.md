# Build & Dependency Conventions with Gradle

**Scope:** When modifying build scripts, version catalogs, or lint configurations.

## Use Bundles

Group related libraries into logical bundles in the version catalog (libs.versions.toml `[bundles]` to keep the dependency list manageable and reusable across modules.

### Example

1. In the `:app` module use bundles like  `android-ui`, `navigation`, `android-core`, `koin`, `room`.
2. Use a `test` bundle for unit testing and a `android-test`/`android-ui-test` bundle for `androidTest`.

## Clean up unused dependencies

When refactoring or removing features, always **clean up `libs.versions.toml`** by removing any unused version or library definitions.

## Build Logic

1. Use **convention plugins** to share common build logic across modules. 

### Example

If multiple UI modules share the same dependency set (Compose BOM, design system, bundles), it should be moved to the `AndroidUiConventionPlugin`.

## Lint & Quality

1. Respect project-specific lint rules defined in `gradle/lint/lint.xml`.
2. Do not override or remove lint suppressions (like ignoring AGP version warnings) unless specifically asked.

## Use Convention Plugins

Keep the build files small by moving reused logic into a Convention Plugin into the `build-logic` module of the project.
