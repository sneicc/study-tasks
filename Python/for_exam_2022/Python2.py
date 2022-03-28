import math


def main(x):
    a = (x ** 3 / 8 + x ** 2) ** 3 / 40
    if(x < 39):
        return(math.tan(x) - 56 * x ** 9)
    elif(39 <= x < 112):
        return(x ** 7)
    elif(112 <= x < 123):
        return(80 * math.cos(x) ** 6 + a + 8 * x ** 4)
    elif(123 <= x < 168):
        return(80 * x)
    elif(x >= 168):
        return((42 - x ** 2 / 77) ** 3)
