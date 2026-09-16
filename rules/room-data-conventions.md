# Data Conventions with Room

**Scope:** In `:data` modules, `**/src/**`, `Room*.kt`, `*Dao.kt`, `*Entity.kt`, `*Mapper.kt`"

> [!IMPORTANT]
> These rules apply ONLY when creating or modifying files within the scope listed above.

## Data structure
1. Keep Room entities/Dao as persistence details in the `:data` module.
2. Mind the [clean-architecture.md](clean-architecture.md) rules.

## Room domain adapters

1. Implement **one Room adapter class per port**:
    - `RoomEntryCreator` : `EntryCreator`
    - `RoomEntryFinder` : `EntryFinder`
2. Do **not** combine multiple ports into one “repository” class.
3. Adapters **orchestrate** DAO + mapper; they do **not** inline `toDomain` / `toEntity` mapping.

## Mappers

1. Put domain ↔ persistence conversion in dedicated mapper classes:
    - `*ToEntityMapper` — domain/input → Room entities (e.g. `NewTrackingEntryToEntityMapper`)
    - `*ToDomainMapper` — Room models → domain (e.g. `EntryWithValuesToDomainMapper`)
2. Prefer **one mapping direction per class** (do not mix `toDomain` and `toEntity` in the same type).
3. Give mappers their **own** focused test class (pure unit tests; no Room required).
