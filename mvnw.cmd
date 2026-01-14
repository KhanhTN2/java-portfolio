@ECHO OFF
SETLOCAL

SET WRAPPER_DIR=%~dp0.mvn\wrapper
SET JAR=%WRAPPER_DIR%\maven-wrapper.jar
SET PROPS=%WRAPPER_DIR%\maven-wrapper.properties

IF NOT EXIST "%JAR%" (
  FOR /F "tokens=2 delims==" %%A IN ('findstr /R "^wrapperUrl=" "%PROPS%"') DO SET WRAPPER_URL=%%A
  IF "%WRAPPER_URL%"=="" SET WRAPPER_URL=https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.3.2/maven-wrapper-3.3.2.jar
  powershell -Command "(New-Object Net.WebClient).DownloadFile('%WRAPPER_URL%', '%JAR%')" || GOTO FALLBACK
)

SET JAVA_EXE=java
IF NOT "%JAVA_HOME%"=="" SET JAVA_EXE=%JAVA_HOME%\bin\java

 "%JAVA_EXE%" -jar "%JAR%" -v >NUL 2>&1
IF ERRORLEVEL 1 GOTO FALLBACK

"%JAVA_EXE%" -jar "%JAR%" %*
GOTO END

:FALLBACK
ECHO Maven wrapper is unavailable. Falling back to mvn.
mvn %*

:END
ENDLOCAL
