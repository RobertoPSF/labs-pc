import os
import hashlib

def calculate_checksum(file_path):
    """Calcula o checksum SHA-256 de um arquivo."""
    sha256_hash = hashlib.sha256()
    with open(file_path, 'rb') as f:
        # Leia o arquivo em blocos para economizar memória
        for byte_block in iter(lambda: f.read(4096), b""):
            sha256_hash.update(byte_block)
    return sha256_hash.hexdigest()

def checksum_files_in_directory(directory):
    """Calcula o checksum de todos os arquivos dentro do diretório."""
    for file_name in os.listdir(directory):
        file_path = os.path.join(directory, file_name)
        if os.path.isfile(file_path):
            checksum = int(calculate_checksum(file_path), 16)
        print(f"{file_path}: {checksum}")

# Exemplo de uso
if __name__ == "__main__":
    root_directory = './root_directory'
    checksum_sum = checksum_files_in_directory(root_directory)
