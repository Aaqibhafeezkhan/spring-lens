# Contributing to Spring Lens

Thank you for your interest in contributing to Spring Lens! Contributions such as bug fixes, new features, tests, and documentation improvements are welcome.

Please read this guide before starting work to help us keep contributions organized and avoid duplicated effort.

## 1. Choose an Issue Before Starting

**Do not start working on an issue or submit a pull request for it until the issue has been assigned to you by the repository management team.**

We sometimes receive multiple pull requests for the same issue because contributors begin working without coordinating first. This creates unnecessary duplicate work for everyone.

Please follow this process:

1. Browse the [open issues](https://github.com/sdlc-pro/spring-lens/issues) and find one you'd like to work on.
2. Comment on the issue to express your interest and request assignment.
3. Wait for the repository management team to review your request and assign the issue to you.
4. Once assigned, start working on the issue.
5. Submit your pull request when your work is ready.

Please do not begin implementation while waiting for assignment. If an issue is already assigned to someone else, choose another issue or ask the team before proceeding.

Pull requests for unassigned issues or issues assigned to another contributor may be closed to prevent duplicate work.

## 2. Development Workflow

Spring Lens uses `master` as the starting point for working branches and `dev` as the target branch for pull requests.

Follow this workflow:

1. Update your local `master` branch.
2. Create your working branch from `master`.
3. Implement your changes and add or update tests as needed.
4. Run the relevant tests and verify your changes.
5. Open a pull request targeting **`dev`**, not `master`.

For example:

```bash
git checkout master
git pull upstream master
git checkout -b feat/SL-587-transaction-observation
```

### Branch Naming Convention

Use a descriptive branch name that reflects the work you're doing. Whenever possible, include the associated issue number using the `SL-<issue-number>` format.

Recommended formats:

- `feat/SL-587-transaction-observation` — new feature.
- `fix/SL-587-bean-metadata` — bug fix.
- `docs/SL-587-contributing-guide` — documentation changes.
- `refactor/SL-587-filter-conversion` — code refactoring.
- `test/SL-587-transaction-tests` — test improvements.

Here, `SL-587` refers to issue #587 in the Spring Lens repository. Replace `587` with your assigned issue number and use a short description relevant to the task.

If a task has no associated issue, use a descriptive branch name, such as `refactor/filter-conversion`.

After completing your work, push your branch and open a pull request against `dev`, referencing the issue assigned to you.

## 3. Development Setup

Spring Lens is a Maven-based Java project. Check the repository configuration for the required Java version and build instructions.

Clone your fork and build the project from the root directory:

```bash
git clone https://github.com/<your-username>/spring-lens.git
cd spring-lens
mvn clean install
```

Run the relevant tests before submitting your pull request:

```bash
mvn test
```

## 4. Contribution Guidelines

Please keep contributions focused, maintainable, and consistent with the existing codebase.

- Follow the project's Java naming conventions and package structure.
- Respect the responsibilities of existing Maven modules.
- Add or update tests for behavior you introduce or change.
- Avoid unnecessary dependencies and abstractions.
- Consider performance, thread safety, and failure handling when working on runtime instrumentation.
- Update documentation when your changes affect public APIs, configuration, or user-facing behavior.

For significant architectural changes, discuss the approach with the maintainers before implementation.

## 5. Pull Request Guidelines

When opening a pull request:

- Target the `dev` branch.
- Reference the issue assigned to you.
- Clearly explain what changed and why.
- Describe how you tested your changes.
- Keep the pull request focused on the assigned issue.

Pull requests are subject to review, and maintainers may request changes before merging.

## 6. Be Respectful

Please keep discussions constructive and respectful. Feedback and technical disagreements are a normal part of open-source development.

If you are unsure about an issue, the architecture, or the contribution process, ask in the relevant issue before proceeding.

Thank you for helping make Spring Lens better!

