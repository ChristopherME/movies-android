![build](https://github.com/ChristopherME/movies-android/actions/workflows/workflow.yml/badge.svg)

# Movies

Movies is a simple project to study and play with some android components, architecture and tools for Android development.

## Tech Stack

This project uses feature modularization architecture, with some modules using MVVM and others just MVI.

## Development setup

You require the latest Android Studio 4.2 (stable channel) to be able to build the app.
For the compose branch use the latest Android Studio on the canary channel.

### Libraries

- Application entirely written in [Kotlin](https://kotlinlang.org)
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/)
- Uses [Koin](https://github.com/InsertKoinIO/koin) for dependency injection
- Uses [Github Actions](https://docs.github.com/en/actions/learn-github-actions)
- Uses [Jetpack Navigation](https://developer.android.com/guide/navigation) for navigation between modules
- Slowly being migrated to use [Jetpack Compose](https://developer.android.com/jetpack/compose) on compose branch

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
