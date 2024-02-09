import sys
import numpy as np

def criar_matriz(tamanho):
    return np.random.randint(1, 29001, size=(tamanho, tamanho), dtype=int)

def salvar_matriz(matriz, nome_arquivo):
    np.savetxt(nome_arquivo, matriz, fmt='%d', delimiter=' ')

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Uso: python3 criar_matriz.py <tamanho>")
        sys.exit(1)

    try:
        tamanho_matriz = int(sys.argv[1])
    except ValueError:
        print("O tamanho da matriz deve ser um número inteiro.")
        sys.exit(1)

    matriz = criar_matriz(tamanho_matriz)
    nome_arquivo = f'matriz.txt'
    salvar_matriz(matriz, nome_arquivo)

    print(f"Matriz de tamanho {tamanho_matriz}x{tamanho_matriz} foi escrita no arquivo {nome_arquivo}.")
