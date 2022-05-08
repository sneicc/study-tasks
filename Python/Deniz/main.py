import math


def main(x, z):
    summ = 0
    n = 5
    for i in range(0, n):
        a = x[(n + 1 - math.ceil((i + 1) / 3)) - 1] ** 2 / 29
        b = -7 - z[i] ** 3
        summ += (a + b) ** 5
    return summ * 85

print(main([-0.09, 0.21, 0.65, 0.25, 0.76],
[0.98, -0.83, 0.2, 0.65, 0.04]))