---
name: init-kmp
description: Bootstrap a Kotlin Multiplatform Gradle project for selected app platforms with convention plugins, shared design-system, and a Hello World feature slice.
example: /init-kmp de.example.app android web
---

# Initialize a KMP project

When `/init-kmp` is used, create a Kotlin Multiplatform project in the **same shape as this repository**.

## Arguments

Application id and platforms after the command.

Examples:

- `/init-kmp com.example.app android web` → `:app:android` + `:app:web`, KMP targets `android` + `wasmJs`

If no Package/Application Id was provided ask for it. Same for missing modules.

## Orchestrate existing skills

Do not invent a second bootstrap path. Use:

1. [init-android-studio-gitignore](../init-android-studio-gitignore/SKILL.md) — then add KMP/Wasm ignores (`kotlin-js-store/`, `*.wasm`).
2. [init-convention-plugins](../init-convention-plugins/SKILL.md) — plugin IDs below.
3. [new-feature-module](../new-feature-module/SKILL.md) — `:feature:hello-world` with `:ui`, `:domain`, `:data`, `:di`.
4. Keep [clean-architecture](../../rules/clean-architecture.md), [compose-ui-conventions](../../rules/compose-ui-conventions.md), [gradle-build-conventions](../../rules/gradle-build-conventions.md), and [testing-conventions](../../rules/testing-conventions.md) aligned with KMP.

## Gradle graph

```
:app:android     (only if android/app requested)  <applicationId>.android.application
:app:web         (only if web requested)          <applicationId>.web.application
:shared:design-system                             <applicationId>.kmp.compose
:feature:hello-world:ui                           <applicationId>.kmp.compose
:feature:hello-world:domain                       <applicationId>.kmp.library
:feature:hello-world:data                         <applicationId>.kmp.library
:feature:hello-world:di                           <applicationId>.kmp.di
```

No `composeApp` module.

Convention plugin IDs:

- `<applicationId>.kmp.library`
- `<applicationId>.kmp.compose`
- `<applicationId>.kmp.di`
- `<applicationId>.android.application`
- `<applicationId>.web.application`

Library targets match the requested platforms (`android` + `wasmJs` for `android web`). Do not add empty other targets source sets.

## Hello World sample

- `:domain`: `SayHelloWorldUseCase`, `GreetingProvider`
- `:data`: `StaticGreetingProvider` returns `"World"` (no `Hello` in data)
- `:ui`: `HelloWorldScreen` + `HelloWorldViewModel` + `HelloWorld`; CMP string `Hello %1$s`; no Koin
- `:di`: Koin composition root; `expect`/`actual` `helloWorldDataModule`; export `helloWorldModule`
- Apps: `startKoin`, `LocalSocialTheme`, `HelloWorldScreen(koinViewModel())`
- `:shared:design-system`: `LocalSocialColors`, `LocalSocialTheme` (Material 3 host), `composeResources`
- Tests: domain/data `commonTest`; UI `androidDeviceTest` with a test-double ViewModel, no `startKoin`

## Required root files

Gradle wrapper, `settings.gradle.kts` (`includeBuild("build-logic")`, no `composeApp`), `gradle/libs.versions.toml` (current stable Kotlin, Compose Multiplatform, AGP, Koin), root `build.gradle.kts`, `gradle.properties`, `local.properties` (sdk.dir, gitignored).
