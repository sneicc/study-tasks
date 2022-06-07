import math


def main (z, x, y):
    ans = 0
    n = 3
    for i in range (n):
        ans += ((84 * y[(n + 1 - math.ceil((i + 1) / 2)) - 1])
                + 68 * (x[(n + 1 - math.ceil((i + 1) / 2)) - 1]) ** 3
                + (z[math.ceil((i + 1) / 2) - 1]) ** 2) ** 6 / 13
    return(ans)

print(format(main([-0.05, 0.88, 0.12],
[-0.39, -0.47, -0.01],
[0.94, -0.64, 0.63]), '.2e'))
