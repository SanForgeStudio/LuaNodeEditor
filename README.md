# Dont Merge this file

### I will explain the important Sync's to make sure that everyone knows what i am contributing with this fork

# Changes

## 1. Logging when generating empty code!

``` Python
# Variable list
hasStartingNodeBeenLogged = False
```

``` Python
def generate_code():
    code = ""
    # add variable declaration code
    global hasStartingNodeBeenLogged
    for node in globals.nodes:
        if isinstance(node, LuaVariableNode) or isinstance(node, LuaTable):
        # if isinstance(node, LuaVariableNode):
            if not node.has_from_node():
                code += node.generate_code()

    # add global functions code
    for node in globals.nodes:
        if isinstance(node, LuaNodeFunction):
            if not node.has_from_node() and not node.is_inline():
                code += node.generate_code()

    start_node = get_starting_node()
    if start_node is None:
        show_modal("Warning", "Start node not found! Please add one.")
        if not hasStartingNodeBeenLogged:
            call_threaded(add_log, ("Please add a starting node for proper code generation",))
            hasStartingNodeBeenLogged = True
    else:
        code += start_node.generate_code()

    dpg.configure_item("generated_code", default_value=code)
  ```
This change is mostly a Quality of Life.
<br>
I made a Bool Variable that checks if the `"Please add a starting node for proper code generation"` has been logged and if it hasn't it would log it and put the Variable to True. that way it would only log it once and keep the `log` clean

### Merges related to it
* [Logging QOL](https://github.com/SanForgeStudio/LuaNodeEditor/commit/2da7992aa70fe42a06fb0e9298967867a5f6007b)
