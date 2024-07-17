SANDBOX=$(dirname $(readlink -fm $0))
cd $SANDBOX

# Verifica se o número de argumentos é válido
if [ $# -ne 1 ]
then
  echo "Informe o total de arquivos"
  echo "Exemplo: $0 1024"
  exit 1
fi

# Executa o programa python
DIR_SIZE=$1

python3 createFiles.py $DIR_SIZE

echo serial:
time python3 serial/checksum.py
echo 
echo concorrente:
time python3 concurrent/checksum.py