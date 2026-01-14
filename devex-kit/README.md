# devex-kit

DevEx-focused module containing reusable build standards and quality configurations.

## What this demonstrates
- Centralized quality gates (Checkstyle, JaCoCo, enforcer)
- Reusable templates for consistent delivery
- Documentation-first approach to platform standards

## Contents
- `src/main/resources/checkstyle/checkstyle.xml`: shared style rules
- `src/main/resources/templates/quality-gate.md`: documentation template for quality gates

## Applying standards to another project
1. Copy the `devex-kit` module into your repo (or publish it as an internal artifact).
2. Point your Maven `maven-checkstyle-plugin` configuration to the shared rules file.
3. Inherit the root `pluginManagement` in your service parent POM.

## Why these standards exist
- Reduce variance between services
- Keep builds deterministic and CI-friendly
- Encourage operational maturity via consistent verification steps
