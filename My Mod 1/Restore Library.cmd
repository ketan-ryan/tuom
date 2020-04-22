@ECHO OFF
SETLOCAL ENABLEEXTENSIONS,ENABLEDELAYEDEXPANSION
CLS

REM Get current path passed in by Eclipse from %0 and change to that folder
set CURRENT_PATH=%~p0
cd "%CURRENT_PATH%"

REM Restore the library files
rmdir /s /q  "src\main\java\library" > nul 2>&1
mkdir "src\main\java\library" > nul
xcopy /s /q /e /y "%CURRENT_PATH%\..\..\Resources\Starter Projects\assets\template\src\main\java\library" "src\main\java\library" > nul

ECHO Library files restored!
ECHO.
PAUSE
