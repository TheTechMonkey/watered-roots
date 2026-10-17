# Watered Roots

A small Fabric mod for Minecraft 26.3 that lets underground water keep farmland
hydrated.

Vanilla watering still works normally. Water beside farmland continues to
hydrate it up to four blocks away. This mod additionally checks for water one
block below the farmland and up to two blocks horizontally in every direction,
giving each underground water block a 5x5 watering area.

Because the check uses Minecraft's water fluid tag, waterlogged blocks in that
underground 5x5 area also work. Hydration timing and crop growth speed remain
unchanged from vanilla.

## Build

Requires Java 25.

On Windows PowerShell:

```powershell
.\gradlew.bat build
```

The playable mod is created in `build/libs/`. Use the JAR whose name does not
contain `sources`.
