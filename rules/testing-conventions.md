# Testing Conventions

**Scope:** `**/src/test/**`, `**/src/androidTest/**`

> [!IMPORTANT]
> These rules apply ONLY when creating or modifying files within the test directories listed above.

## Use Fakes or Mocks
Dependencies should not be tests within a test class. Use Mocks or Fakes for any dependency.
### Mocked external dependencies
Use MockK to mock dependencies that are external or have complex behavior and cannot be easily faked.
Use also MockK if verification that dependencies are called is needed.
### Faked interface behavior
For fun interfaces use abstract implementation within the test to provide a behavior.
### Faked test data
   1. To provide test data use Fakes. Use a `Test**` object for functions.
   2. Functions explain what they are faking, e.g. `aValidUser()`.
   3. An `any**()` function should be provided when the values are not tested.
   4. Parameters default when they are not needed.
   5. The `Test**` object is imported statically to improve readability.

#### Example
```kotlin
object TestUser {
   
   fun anyUser() = aUserWith()

   fun aUserWith(
      name:String = "any",
      email:String = "any",
   ) = User(name, email)
   
   fun anInvalidUser(
       name:String = "any"
   ) = User(name, null)
}

fun test() {
    // Given a list of users
    val users = listOf(
       anyUser(), 
       anInvalidUser("Max"), 
       aUserWith(name = "Simon", email = "simon@says.com")
    )
   //...
}
```

## Given - When - Then
   1. Use the `// Given ...`, `// When ...`, `// Then ...` structure.
   2. Describe what each Given/When/Then block does
   3. Use `// And ...` if a block does more than one thing.

### Example

```kotlin
   // Given a user
   val user = TestUser.aUserwith(name = "Max", email = "max@example.com")

   // When the user is mapped
   val uiState = UserMapper().toUiState(user)

   // Then the name should be mapped
   assertEquals(user.name, uiState.name)
   // And the email should be mapped
   assertEquals(user.email, uiState.email)
```

## Function naming
   1. For Unit Tests (`src/test`) use backticks with readable names: ``fun `test subject should expected behaviour`() {...} ``
   2. For Android UI tests (`src/androidTest`), use underscores to avoid DEX errors: `fun test_subject_should_expected_behaviour() {...}`.

## 100% coverage by definition
The tests should cover 100% of all code lines or be excluded from the coverage.

### Exclusion rules
   1. The class is a wrapper for Android or other plattform components
   2. There is a technical limitation to test a function with full coverage
