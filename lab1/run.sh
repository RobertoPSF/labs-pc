SANDBOX=$(dirname $(readlink -fm $0))
cd $SANDBOX/bin

# Verifica se o número de argumentos é válido
if [ $# -ne 1 ]
then
  echo "Informe o tamanho da matriz"
  echo "Exemplo: $0 1024"
  exit 1
fi

# Executa o programa java
MATRIX_SIZE=$1
java Find $MATRIX_SIZE
