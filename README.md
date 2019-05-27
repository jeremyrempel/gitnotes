## Github Notes

**Take Back Your Notes!**

A mobile competitor to proprietary cloud based note solutions such as Evernote and One Note utilizing git and Markdown formats. All advantages of such as atomic history, brancing, merging and synchronization along the advantages of utilizing a common formatting language and cloud sync.

The system is broken down into 3 modules:

- **Common:** Common code utilized by both Android and iOS
- **Android:** Android specific Kotlin code
- **iOS:** Kotlin native and Swift

**Current Status:** Pre-alpha in development

## Technical Notes

- iOS: Kotlin Native, Swift
- Android: Kotlin

## Http Client

- Utilizes Jetbrains Ktor client. Provided in common module as ktor-client-core
- Android implementation provided OKHttp via ktor-client-okhttp dependency
- iOS implementation provided via NSUrlSession via ktor-client-ios

## Dependency Injection

- **Android:** Dagger. Dagger was used due to flexiblity and desire to integrate closely with the Android specific dependencies
- **iOS**: Custom common code implementation

## Build Status

|  Android | iOS  |
| -- | -- |
| ![Android Build Status](https://build.appcenter.ms/v0.1/apps/33fa0e88-79c5-4c08-b67a-a6c55bf56ab5/branches/master/badge) | ![iOS Build Status](https://build.appcenter.ms/v0.1/apps/b56b1aed-2605-4d96-80c8-34eeeeb554ae/branches/master/badge) |

