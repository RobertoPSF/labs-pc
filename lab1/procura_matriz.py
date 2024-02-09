import numpy as np

def encontrar_maior(matriz):
    maior = float('-inf')

    # Percorrer a matriz usando loops for aninhados
    for i in range(matriz.shape[0]):
        for j in range(matriz.shape[1]):
            elemento = matriz[i, j]
            if elemento > maior:
                maior = elemento

    return maior

def encontrar_menor(matriz):
    menor = float('inf')

    for i in range(matriz.shape[0]):
        for j in range(matriz.shape[1]):
            elemento = matriz[i, j]
            if elemento < menor:
                menor = elemento
    return menor

# Ler a matriz do arquivo e armazenar em memória usando NumPy
nome_arquivo = 'matriz.txt'
matriz_lida = np.loadtxt(nome_arquivo, dtype=int)

# Encontrar o menor e o maior número na matriz
menor_numero, maior_numero = encontrar_menor(matriz_lida), encontrar_maior(matriz_lida)

print(f"Menor número na matriz: {menor_numero}")
print(f"Maior número na matriz: {maior_numero}")
