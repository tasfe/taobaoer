@echo off


set "SrcDir=D:\kooks\workspace\market\target"
cd D:\kooks\workspace\market
d:
for %%a in (market33) do (
mvn clean resources:resources  -Pproduct -Dumeng-channel=%%a package   
for /r "%SrcDir%\" %%i in (*.apk) do (
echo f| xcopy /y  %%i E:\apk\market\%%a-%%~nxi  )
)

   

