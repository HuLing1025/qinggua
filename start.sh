echo "[INFO]----------------------------------------------------------------------- KILL PROCESS ------------------------------------------------------------"

echo "[INFO]"

PORT=8088

PROSSNAME="qinggua.jar"

FILEPATH="/var/lib/jenkins/workspace/qinggua/target/"

sudo netstat -nlp | grep :$PORT | awk '{print $7}' | awk -F"/" '{print "sudo kill -9 "$1}' | sh

#lsof -i:$PORT | grep $PROSSNAME | awk '{print "kill -9 "$2}' | sh

count=`sudo lsof -i:$PORT | grep $PORT | wc -l`

#echo "[INFO] pid number:" + $count

if [ $count -ne  +0 ] ;then
        return
fi
#pids=`lsof -i:$PORT | grep $PORT | awk '{print $2}'`
#pids=`cat -f123 $pids`
#echo "[INFO] PIDS:" + $pids
#for pid in pids
#do
#       echo "[INFO] kill pid:" + "$pid"
#       kill -9 $pid
#done

echo "[INFO]----------------------------------------------------------------------- KILL PID SUCESSED --------------------------------------------------------"

echo "[INFO]"

echo "[INFO]------------------------------------------------------------------------ BEGIN TO DEPLOY ---------------------------------------------------------"

echo "[INFO]"

cd $FILEPATH

sudo nohup java -jar $PROSSNAME >>/logs/nohup.out &

echo "[INFO]------------------------------------------------------------------------- DEPLOY SUCESSED --------------------------------------------------------"
