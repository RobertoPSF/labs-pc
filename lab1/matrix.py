import numpy as np

def generate_matrix(size):
    return np.random.randint(1, 29001, size=(size, size), dtype=int)

'''def save_matrix(matrix, file_name):
    np.savetxt(file_name, matrix, fmt='%d', delimiter=' ')'''

def min(matrix):
    smallest = float('inf')

    for i in range(matrix.shape[0]):
        for j in range(matrix.shape[1]):
            element = matrix[i, j]
            if element < smallest:
                smallest = element

    return smallest

def max(matrix):
    largest = float('-inf')

    for i in range(matrix.shape[0]):
        for j in range(matrix.shape[1]):
            element = matrix[i, j]
            if element > largest:
                largest = element

    return largest
