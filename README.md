# Dont Merge this file

### I will explain the important Sync's to make sure that everyone knows what i am contributing with this fork

# Changes

## 1. Logging once when generating empty code!
This change is mostly a Quality of Life.
<br>
I made a Bool Variable that checks if the `"Please add a starting node for proper code generation"` has been logged and if it hasn't it would log it and put the Variable to True. that way it would only log it once and keep the `log` clean

### Merges related to this Change
* [Logging the Generated code only Once](https://github.com/SanForgeStudio/LuaNodeEditor/commit/2da7992aa70fe42a06fb0e9298967867a5f6007b)
* [Giving a Warning when copying empty code](https://github.com/SanForgeStudio/LuaNodeEditor/commit/fd70c467d5dd5a6f81e3863a59940d283771a701)
* [Grammer issues](https://github.com/SanForgeStudio/LuaNodeEditor/commit/2b529450daffc5a8776b7da117e5f82ecb7eb5eb)
* [If the `copy` log is the last log it will relog the failed generation](https://github.com/SanForgeStudio/LuaNodeEditor/commit/206bcf246e2510ec9dfadf2d511c303b6d111977)
  
## 2. Giving a Warning/Logging when trying to copy ungenerated Code!
This change is mostly a Quality of Life.
<br>
I made a Bool Variable that checks if the User has generated code when pressing the `Copy Code` button. That way if the User hasn't generated any code yet it will give a Warning that you haven't generated code to copy yet in stead of just doing nothing.

### Merges related to this Change
* [Giving a Warning when copying empty code](https://github.com/SanForgeStudio/LuaNodeEditor/commit/fd70c467d5dd5a6f81e3863a59940d283771a701)
* [Grammer issues + logging](https://github.com/SanForgeStudio/LuaNodeEditor/commit/2b529450daffc5a8776b7da117e5f82ecb7eb5eb)
* * [If the `generate` log is the last log it will relog the failed copying + it only logs the copy code error once](https://github.com/SanForgeStudio/LuaNodeEditor/commit/206bcf246e2510ec9dfadf2d511c303b6d111977)

## 3. Added Copy Code to the Help Modal!
I have expended the Help Modal a little bit because i saw you forgot to add the `Copy Code` button.

### Merges related to this Change
* [Added `Copy Code` to the help modal](https://github.com/SanForgeStudio/LuaNodeEditor/commit/ed67272c12baa38c92f86512ea5ee9a50b32465a)

## 4. Made a Simple Bug Report Layout!
When creating an issue you can now click on the Bug Report Layout and only answer some questions. that way the coders have a way more in depth explenation about the bug and how to fix it.

### Merges related to this Change
* [Created the layout](https://github.com/SanForgeStudio/LuaNodeEditor/commit/8571053e5ff3f75c6779688c980e06faae90b59b)
* [fixed the label](https://github.com/SanForgeStudio/LuaNodeEditor/commit/35f8f8a951a1c6b1050e455cd5562b405b6c9010)
* [fixed a typo](https://github.com/SanForgeStudio/LuaNodeEditor/commit/799bfcd3b2232f4ddf7976b08183672579c7f12d)