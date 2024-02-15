SANDBOX=$(dirname $(readlink -fm $0))
cd $SANDBOX

# Compila o código direcionando a saída para a pasta bin
javac -d bin ./java/Matrix.java ./java/Find.java
