@echo off
REM Snakes and Ladders Game Launcher
REM This script runs the Snakes and Ladders game

echo.
echo ================================================
echo   SNAKES AND LADDERS - Game Launcher
echo ================================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 11 or higher and add it to your PATH
    pause
    exit /b 1
)

REM Get the directory where this script is located
set SCRIPT_DIR=%~dp0

REM Run the JAR file
echo Starting the game...
echo.
java -jar "%SCRIPT_DIR%build\libs\SnakesAndLaddersGame.jar"

if errorlevel 1 (
    echo.
    echo ERROR: Failed to run the game
    echo Please make sure the JAR file exists at:
    echo %SCRIPT_DIR%build\libs\SnakesAndLaddersGame.jar
    pause
    exit /b 1
)
