import os
import sys

def calculate_checksum(file_path):
    checksum = 0
    with open(file_path, 'rb') as f:
        byte = f.read(1)
        while byte:
            checksum += int.from_bytes(byte, byteorder='big', signed=False)
            byte = f.read(1)
    return checksum

def checksum_files(path):
    """Calcula o checksum dos arquivos recebidos como parametro."""
    if os.path.isfile(path):
        checksum = calculate_checksum(path)
        return checksum
    raise ValueError("Arquivo irregular: " + path)

if __name__ == "__main__":
    paths = sys.argv[1:]
    for path in paths:
        try:
            checksum = checksum_files(path)
            print(path + " : " + str(checksum))
        except ValueError as e:
            print(f"Erro: {e}")
