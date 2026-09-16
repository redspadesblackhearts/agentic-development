---
name: new-feature-module
description: Create a Gradle feature module according to [clean-architecture.md](../../rules/clean-architecture.md).
example: /new-feature-module :feature:entry-form
---

# New Feature Module

When this phrase is used, the agent should create exactly three Gradle modules for the feature:

| Module | Path | Gradle project |
|--------|------|----------------|
| `ui` | `tracking/<featureName>/ui` | `:tracking:<featureName>:ui` |
| `domain` | `tracking/<featureName>/domain` | `:tracking:<featureName>:domain` |
| `data` | `tracking/<featureName>/data` | `:tracking:<featureName>:data` |

## Required steps

1. Create the three module directories under `tracking/<featureName>/`.
2. Add a `build.gradle.kts` for each module that applies `alias(libs.plugins.trxe.android.library)` (and other plugins only as needed), following existing library-module conventions in this repo.
3. In each of the three modules, create the package
   `src/main/kotlin/de/redspadesblackhearts/trxe/<featureName>/`
   (Kotlin package: `de.redspadesblackhearts.trxe.<featureName>`).
4. Register all three modules in `settings.gradle.kts` with:
    - `include(":tracking:<featureName>:ui")`
    - `include(":tracking:<featureName>:domain")`
    - `include(":tracking:<featureName>:data")`
5. Do not create other modules or paths for this trigger unless the user explicitly asks.
