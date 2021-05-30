# Navigation Extensions

This library is built as an abstraction of the [FragmentTransaction](https://developer.android.com/reference/androidx/fragment/app/FragmentTransaction) API.
It doesn't add any extra functions, just some extension functions for make the api easier to use.

## Features
 - Extension functions for ADD & REPLACE fragments.
 - Load classes (Fragments, Activities, Services, etc...) by qualified name.

## TODO
Create a BottomNavigationView adapter that handles retain fragment. Probably do something like this
 - Load initial fragments (Lazy load)
 - First fragment is added
 - User clicks another tab
 - Hide the current fragment
 - Verify the incoming fragment is not added
  - Is ALREADY added: Show the incoming fragment
  - Is NOT added: Add & Show the new fragment