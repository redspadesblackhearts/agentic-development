# DateTime Conventions

**Scope:** Throughout the entire project when handling dates, times, or time zones.

## Standard Library

1. Always use **`kotlinx-datetime`** for domain logic, data models, and business rules.
2. **Do not** use `java.time.*` (e.g., `java.time.LocalDate`) in domain or data layers unless required by a specific platform API that does not yet support `kotlinx-datetime`.

## Common Types

- Use `kotlinx.datetime.LocalDate` for calendar dates.
- Use `kotlinx.datetime.LocalDateTime` for dates with times (without time zone).
- Use `kotlinx.datetime.Instant` for moments in time (UTC).
- Use `kotlinx.datetime.TimeZone` for handling time zone conversions.

## Dependency Injection

- Implement a `CurrentDateTimeProvider` interface instead of directly accessing `kotlinx.datetime.Clock`
- Inject `CurrentDateTimeProvider` into ViewModels or UseCases that need the current time.
- Use `Clock.System` for the default implementation in production.
- Use `todayIn(TimeZone.currentSystemDefault())` to get the current `LocalDate`.
