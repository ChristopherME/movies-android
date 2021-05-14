![GitHub Cards Preview](https://github.com/ChristopherME/movies-android/blob/master/art/movies_app.jpg)

[![build](https://github.com/ChristopherME/movies-android/workflows/Test%20and%20build/badge.svg)](https://github.com/ChristopherME/movies-android/actions?branch=master)

# Movies

Movies is a simple project to study and play with some android components, architecture and tools for Android development.

## Tech Stack

This project uses [feature modularization architecture](https://proandroiddev.com/intro-to-app-modularization-42411e4c421e).
The movies feature module uses MVI as software design patter for presentation layer, and the actors feature module uses MVVM.
You can checkout how was the migration from MVVM to MVI in this [pull request](https://github.com/ChristopherME/movies-android/pull/19).

## Medium blogs

I write about the process of coding this project in the following blogss:
 - [Understanding MVVM](https://christopher-elias.medium.com/understanding-mvvm-pattern-for-android-in-2021-98b155b37b54)
 - [Integrating Paging3](https://christopher-elias.medium.com/pagination-in-android-with-paging-3-retrofit-and-kotlin-flow-2c2454ff776e)

## Development setup

You require the latest Android Studio 4.2 (stable channel) to be able to build the app.
For the compose branch use the latest Android Studio on the canary channel.

### Libraries

- Application entirely written in [Kotlin](https://kotlinlang.org)
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/)
- Uses [Koin](https://github.com/InsertKoinIO/koin) for dependency injection
- Uses [Github Actions](https://docs.github.com/en/actions/learn-github-actions)
- Uses [Jetpack Navigation](https://developer.android.com/guide/navigation) for navigation between modules
- Uses [Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview), see this [PR](https://github.com/ChristopherME/movies-android/pull/17).

### API keys

You need to supply API / client keys for the service the app uses.

- [TMDb](https://developers.themoviedb.org)

Once you obtain the key, you can set them in your `~/local.properties`:

```
# Get this from TMDb
tmdb.key=<insert>
```

## 📃 License

```
Copyright 2021 Christopher Elias

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
