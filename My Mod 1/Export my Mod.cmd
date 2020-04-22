@ECHO OFF
SETLOCAL ENABLEEXTENSIONS,ENABLEDELAYEDEXPANSION
CLS
ECHO;

REM Get current path passed in by Eclipse from %0 and change to that folder
set CURRENT_PATH=%~p0
cd "%CURRENT_PATH%"

REM Do the export and forge installer if required
"C:\Users\Ketan\Desktop\Youth Digital\Mod Design 2\Resources\Software\nodejs\node.exe" "C:\Users\Ketan\Desktop\Youth Digital\Mod Design 2\Resources\Software\scripts\export-mod\index.js" ^
    --destination "C:\Users\Ketan\Desktop\Youth Digital\Mod Design 2\Exports" ^
    --minecraft-dir "C:\Users\Ketan\AppData\Roaming/.minecraft" ^
    --java-exe "C:\Users\Ketan\Desktop\Youth Digital\Mod Design 2\Resources\Software\java\bin\java.exe" ^
    --forge-installer-jar "C:\Users\Ketan\Desktop\Youth Digital\Mod Design 2\Resources\Software\forge\forge-1.12.2-14.23.0.2491-installer.jar" ^
    --forge-version-required "1.12.2-14.23.0.2491"

if %errorlevel% neq 0 PAUSE
