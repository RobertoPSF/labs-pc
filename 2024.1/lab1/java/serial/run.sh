SANDBOX=$(dirname $(readlink -fm $0))
cd $SANDBOX/bin

# Verifica se pelo menos um argumento foi passado
if [ "$#" -lt 1 ]; then
  echo "Uso: $0 arquivo1 [arquivo2 ...]"
  exit 1
fi

# Passa todos os argumentos para o script Python
time java Checksum "$@"

