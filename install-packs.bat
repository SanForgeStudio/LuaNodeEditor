@echo off
echo ============================================
echo Installing required Python packages...
echo ============================================

:: Upgrade pip first
python -m pip install --upgrade pip

:: Install packages
pip install dearpygui
pip install pyperclip
pip install pyinstaller
pip install lupa
pip install pillow


:: pprint and threading are standard Python libraries — no install needed
echo Skipping pprint and threading (already included in Python)

echo.
echo ============================================
echo All packages installed successfully!
echo ============================================

pause
