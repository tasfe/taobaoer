@echo off


set "SrcDir=D:\kooks\workspace\speed\target"
cd D:\kooks\workspace\speed
d:
for %%a in (speed0312) do (
mvn clean resources:resources  -Pproduct -Dumeng-channel=%%a package   
for /r "%SrcDir%\" %%i in (*.apk) do (
echo f| xcopy /y  %%i E:\apk\speed\%%a-%%~nxi  )
)

   

