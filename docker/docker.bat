@echo off


REM 使用说明，用来提示输入参数
echo  input (port base modules stop rm):
set /p choice=""
if "%choice%"=="port" call :port
if "%choice%"=="base" call :base
if "%choice%"=="modules" call :modules
if "%choice%"=="stop" call :stop
if "%choice%"=="rm" call :rm



exit /b

REM 开启所需端口
:port
echo Adding firewall rules...
REM 添加端口规则，请根据实际情况调整端口号和防火墙配置命令
netsh advfirewall firewall add rule name="Open Port 80" dir =in  protocol=TCP localport=80 action=allow
netsh advfirewall firewall add rule name="Open Port 8080" dir =in protocol=TCP localport=8080 action=allow
netsh advfirewall firewall add rule name="Open Port 8848" dir =in protocol=TCP localport=8848 action=allow
netsh advfirewall firewall add rule name="Open Port 9848" dir =in protocol=TCP localport=9848 action=allow
netsh advfirewall firewall add rule name="Open Port 9849" dir =in protocol=TCP localport=9849 action=allow
netsh advfirewall firewall add rule name="Open Port 6379" dir =in protocol=TCP localport=6379 action=allow
netsh advfirewall firewall add rule name="Open Port 3306" dir =in protocol=TCP localport=3306 action=allow
netsh advfirewall firewall add rule name="Open Port 9100" dir =in protocol=TCP localport=9100 action=allow
netsh advfirewall firewall add rule name="Open Port 9200" dir =in protocol=TCP localport=9200 action=allow
netsh advfirewall firewall add rule name="Open Port 9201" dir =in protocol=TCP localport=9201 action=allow
netsh advfirewall firewall add rule name="Open Port 9202" dir =in protocol=TCP localport=9202 action=allow
netsh advfirewall firewall add rule name="Open Port 9203" dir =in protocol=TCP localport=9203 action=allow
netsh advfirewall firewall add rule name="Open Port 9300" dir =in protocol=TCP localport=9300 action=allow
echo Restarting firewall service...
net stop "MpsSvc"
net start "MpsSvc"
pause
goto :eof

REM 启动基础环境（必须）
:base
echo Starting basic environment...
docker-compose -p ruoyi up -d ruoyi-mysql ruoyi-redis ruoyi-nacos
pause
goto :eof

REM 启动程序模块（必须）
:modules
echo Starting program modules...
docker-compose -p ruoyi up -d ruoyi-nginx ruoyi-gateway ruoyi-auth ruoyi-modules-system
pause
goto :eof

REM 关闭所有环境/模块
:stop
echo Stopping all environments/modules...
docker-compose stop
pause
goto :eof

REM 删除所有环境/模块
:rm
echo Removing all environments/modules...
docker-compose rm
pause
goto :eof


