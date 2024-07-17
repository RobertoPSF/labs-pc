import os
import random
import string
import sys
import shutil

def create_random_string(length):
    """Cria uma string aleatória de comprimento especificado."""
    letters = string.ascii_letters + string.digits
    return ''.join(random.choice(letters) for _ in range(length))

def create_directories_and_files(root, N):
    """Cria o diretório root e N arquivos em cada diretório."""
    os.makedirs(root, exist_ok=True)
    
    for j in range(1, N + 1):
        file_path = os.path.join(root, f'file_{j}.txt')
        with open(file_path, 'w') as f:
            random_string = create_random_string(100)  # Pode ajustar o comprimento da string
            f.write(random_string)

def clean_directories_and_files(root):
    """Remove recursivamente o diretório root e todo o seu conteúdo."""
    try:
        shutil.rmtree(root)
        print(f"Conteúdo de '{root}' removido com sucesso.")
    except OSError as e:
        print(f"Erro ao remover conteúdo de '{root}': {e}")

N = int(sys.argv[1])

# Exemplo de uso
root_directory = 'root_directory'
clean_directories_and_files(root_directory)
create_directories_and_files(root_directory, N)

print(f"Diretórios e arquivos criados em '{root_directory}'")
