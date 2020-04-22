@ECHO OFF
SETLOCAL ENABLEEXTENSIONS,ENABLEDELAYEDEXPANSION
CLS
ECHO Backing up your project
ECHO.

REM Get current path passed in by Eclipse from %0 and change to that folder
set CURRENT_PATH=%~p0
cd "%CURRENT_PATH%"

REM Get the current folder name
REM Remove the trailing slash
set CURRENT_PATH=%CURRENT_PATH:~0,-1%
for %%f in ("%CURRENT_PATH%") do set CURRENT_FOLDER_NAME=%%~nxf

REM Do the backup
"C:\Users\Ketan\Desktop\Youth Digital\Mod Design 2\Resources\Software\nodejs\node.exe" "C:\Users\Ketan\Desktop\Youth Digital\Mod Design 2\Resources\Software\scripts\backup-files\index.js" ^
    --include "src" ^
    --backup-name "%CURRENT_FOLDER_NAME%" ^
    --restore-script-template "C:\Users\Ketan\Desktop\Youth Digital\Mod Design 2\Resources\Software\scripts\Restore my Project.cmd" ^
    --destination "C:\Users\Ketan\Desktop\Youth Digital\Mod Design 2\Backups"

if %errorlevel% neq 0 PAUSE
