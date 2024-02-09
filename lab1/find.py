from matrix import generate_matrix, max, min
import sys, time

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Use: python3 find.py <size>")
        sys.exit(1)

    try:
        size = int(sys.argv[1])
    except ValueError:
        print("The size of the matrix must be an integer.")
        sys.exit(1)

    start_time = time.time()
    matrix = generate_matrix(size)
    end_time = time.time()
    print(f'Result time: {(end_time-start_time):.2f}')
    print(f'Max value: {max(matrix)}')
    print(f'Min value: {min(matrix)}')