---
name: scoped-module
description: Scoped module development. Only change files within the given module.
example: /soped-module :app Fix the navigation to the details screen
---

# Scoped Module Development
When this skill is used, the agent should:
1. Only edit files within the directory of the specified module.
2. Only run Gradle commands scoped to that specific module (e.g., `./gradlew :module:task`).
3. Maintain strict isolation from other modules.

## Success criteria
After the agent is completed there are no git changes in the project outside the given module.
