SANDBOX=$(dirname $(readlink -fm $0))
cd $SANDBOX

# Verifica se o número de argumentos é válido
if [ $# -ne 1 ]
then
  echo "Informe o tamanho da matriz"
  echo "Exemplo: $0 1024"
  exit 1
fi

# Executa o programa python
MATRIX_SIZE=$1
time python3 find.py $MATRIX_SIZE
