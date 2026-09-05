# Fix build error: File name must end with .xml or .png

The build is failing because a Kotlin source file (`FeatureItem.kt`) was incorrectly placed in the `res/drawable` directory. Android resource directories only support XML and image files.

## Proposed Changes

### Welcome Feature

#### [NEW] [WelcomeScreen.kt](file:///C:/Users/DELL/Documents/IIT%20WORK/EKATAYAN%20TEST%201/EkataYan-Front-End/app/src/main/java/com/ekatayan/app/feature/welcome/WelcomeScreen.kt)
Create the correct feature directory and move the Kotlin code there with proper package declaration and `R` class imports.

#### [DELETE] [FeatureItem.kt](file:///C:/Users/DELL/Documents/IIT%20WORK/EKATAYAN%20TEST%201/EkataYan-Front-End/app/src/main/res/drawable/FeatureItem.kt)
Remove the misplaced Kotlin file from the resources directory.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:packageDebugResources` to verify that the resource packaging error is resolved.
- Run a full build `./gradlew assembleDebug` to ensure the moved Kotlin file compiles correctly.
