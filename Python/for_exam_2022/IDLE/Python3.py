import math


def main(m, a, b):
    ans = 0
    for i in range(1, b+1):
        for c in range(1, a+1):
            for j in range(1, m+1):
                ans += (j ** 2 - 8 * c) ** 5 - j ** 8 - math.atan(i) ** 3          
    return ans

print(format(main(8,6,3),'.2e'))
