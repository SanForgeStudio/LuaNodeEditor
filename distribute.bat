@echo off
pyinstaller --onefile --name LuaNodeEditor --icon="src/assets/images/icon.ico" --noconsole "src/main.py"
