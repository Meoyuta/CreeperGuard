@echo off
setlocal

set MAVEN_OPTS=-Dfile.encoding=UTF-8

where mvn >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo [ERROR] Maven not found. Please install Maven and add it to PATH.
    exit /b 1
)

echo [INFO] Cleaning previous build...
call mvn clean %*
if %ERRORLEVEL% neq 0 goto :fail

echo [INFO] Building CreeperGuard...
call mvn package -DskipTests %*
if %ERRORLEVEL% neq 0 goto :fail

echo.
echo [OK] Build complete!
for /f "delims=" %%i in ('dir /b /s target\CreeperGuard-*.jar 2^>nul') do echo [OK] Artifact: %%i
exit /b 0

:fail
echo [ERROR] Build failed.
exit /b 1
