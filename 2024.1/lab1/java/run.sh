SANDBOX=$(dirname $(readlink -fm $0))
cd $SANDBOX/bin

# Verifica se o número de argumentos é válido
if [ $# -ne 1 ]
then
  echo "Informe o total de arquivos"
  echo "Exemplo: $0 1024"
  exit 1
fi

# Executa o programa python
DIR_SIZE=$1

java CreateFiles $DIR_SIZE



echo serial:
time java ChecksumSerial
echo
echo concorrente:
time java ChecksumConcurrent