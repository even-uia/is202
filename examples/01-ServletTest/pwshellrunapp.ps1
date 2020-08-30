
#Powershell script for studenter med windows.
#run script

((docker container stop roro) -and  (docker container rm roro) -or  (true))
Start-Sleep -Seconds 5

 docker build -t=roro .

docker run --name roro -d -p 4848:4848 -p 8080:8080 roro
Start-Sleep -Seconds 5
$url = 'http://localhost:8080/ServletDemo-1.0/'
$chrome = (Get-ItemProperty 'HKCU:\SOFTWARE\Microsoft\Windows\CurrentVersion\App Paths\chrome.exe').'(Default)'
Start-Process "$chrome" $url
