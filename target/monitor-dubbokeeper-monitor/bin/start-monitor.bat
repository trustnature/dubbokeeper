setlocal
call "%~dp0setting-monitor.bat"

SET DUBBO_PROPERTIES=dubbo-monitor.properties
echo on
java "-Dmonitor.log.home=%LOG_HOME%" "-Ddubbo.properties.file=%DUBBO_PROPERTIES%" -cp "%CLASSPATH%" "%MAINCLASS%" start
endlocal
