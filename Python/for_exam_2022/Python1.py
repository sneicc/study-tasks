import math


def main(z, x, y):
    a = 67 * (29 * z ** 2) ** 4
    b = (71 * x ** 2 - y ** 3 - y / 72) ** 5
    c = (64 - 66 * y ** 3 - z / 11) ** 2
    d = math.log10(x ** 2 / 84 + 1 + y ** 3) ** 7
    return math.sqrt(a + b) - (c + d)

print(format(main(-0.59, 0.82, 0.2), '.2e'))
