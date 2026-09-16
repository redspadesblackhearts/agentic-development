# Clean architecture

**Scope:** All coding-related files and directories.

## Structured modules

1. Features are horizontally sliced into modules, typically with a `:feature:` module
2. Each feature module is vertically sliced into `:domain`, `:data` and `ui`.
3. Use domain-centric terminology for packages, classes and methods.

## Use cases

1. Name use cases `*UseCase` (e.g. `CreateEntryUseCase`).
2. A use case depends on **narrow ports** (fun interfaces), not a fat repository (Interface Segregation Principle)
3. **Do not** put validation or mapping logic in the use case — inject a `*Validator` (Dependency Inversion Principle)

```kotlin
class CreateTrackingEntryUseCase(
    private val newTrackingEntryValidator: NewEntryValidator,
    private val trackingEntryCreator: EntryCreator,
)
```

## Single responsibility

1. Each class does **one thing** (Clean Code).
2. Typical split:
    - **Use case**: The actual user flow without implementation details (`*UseCase`)
    - **`*Provider`, `*Finder`, `*Creator`, `*Updater`**: Instead of combined Repository. Use interfaces for implementation outside of domain module.
    - **Domain logic like `*Handler`, `*Validator`, `*Filter`**
    - **Mapper**: Conversion for each model only (`*ToEntityMapper`, `*ToDomainMapper`)
3. Prefer `New*` input models for write operations (e.g. `NewEntry`, `NewValue`) instead of nullable ids.

## One type per file

1. Put each class, interface, or fun interface in its **own file**.
2. Exception: **sealed** hierarchies may share a file with their subtypes.

## Tests

1. **One test class per production class** under test (smaller, focused suites).
2. Use-case tests cover orchestration (valid → port called; invalid → port not called).
3. Validator tests cover all validation rules.
4. Mapper tests cover field mapping and edge cases (e.g. blank name → `null`).
5. Creator tests verify persistence (e.g. via DAO read-back); finder tests seed data via DAO and assert the returned domain model.
6. Follow [testing-conventions](./testing-conventions.md) (`Given`/`When`/`Then`, naming).
