import math


def main(n):
    return -0.80 if n == 0 else (math.ceil(main(n-1))) ** 2 / 47 - main(n - 1)
print(main(4))
