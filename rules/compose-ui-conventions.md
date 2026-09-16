# Compose UI Conventions

**Scope:** When creating or modifying Jetpack Compose UI

## Structure

1. Build UI from **small, reusable composables** (one clear responsibility each).
2. Assemble them in a parent composable that receives state and event callbacks.
3. Do **not** name view composables `*Screen`.
   - A **Screen** owns UI state via a `ViewModel` (collects state, forwards events).
   - Presentational views take a `UiState` (+ callbacks) and stay ViewModel-free.
   - Prefer names like `EntryForm`, `ValueRow`, `SaveEntryButton`.
4. Files should be grouped in packages by domain/functionality if applicable, like `entry/value`

## UI state

1. Every interactive view is driven by an immutable `*UiState`.
2. UiStates should not be nested but have separate files below
3. Keep editable fields as UI-friendly types (e.g. amounts as `String` while typing).
4. Provide **sample UI states** in a dedicated `*UiStateSamples` (or similar) object for previews and tests.
5. Sample values should be named by their content not by their usage.

## Previews

1. Add `@Preview` for reusable composables and parent views.
2. Previews **must** use sample UI states — do not invent one-off inline state in previews when a sample exists.
3. Wrap previews in the app theme (`TrxeTheme`).
4. Preview functions are private.

## Strings / labels

1. All user-visible labels, titles, content descriptions, and button texts come from **string resources** (`stringResource(R.string.*)`).
2. Do **not** hardcode UI copy in composables (including preview-only labels that appear in the real UI).
3. Add new strings in the module’s `res/values/strings.xml` (or the appropriate module resources).

# Dropdowns and non previewable content

1. If a Composable shows a dropdown the displayed dropdown should have its own private function.
2. The content of a dropdown should have its own Preview Composable.
