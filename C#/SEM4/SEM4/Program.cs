using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NUnit.Framework;

namespace SEM4
{
    class Program
    {
        static void Main(string[] args)
        {
            //var arr = new int[] { 1, 2, 3, -1, 4 };
            //FindSumInInterval(arr, 1, 5);

            //Console.WriteLine(bargain(50, 100));
            //var rnd = new Random();
            //var arr = new int[50];

            //for (int i = 0; i < arr.Length; i++)
            //{
            //    arr[i] = rnd.Next(1, 10000);
            //}

            //Array.Sort(arr);
            //arr = arr.Distinct().ToArray();

            //ValueFromSumOfArrayElements(rnd.Next(1, 10000), arr);

            var arr2 = new int[] { 3, 2, 5, 7 };
            PrimeNumbers(arr2);
        }

        static void FindSumInInterval(int[] arr, int i, int j)
        {
            var sum = 0;
            for (i -= 1, j -= 1; i < arr.Length & i <= j; i++)
            {
                sum += arr[i];
            }
            Console.WriteLine(sum);
        }

        static int bargain(int A, int C)
        {
            int B = 10;
            int D = 10;

            while (A != B)
            {
                if (A + B > C)
                    return A;
                A += B;
                if (C - D < A)
                    return C;
                C -= D;
            }

            return A;
        }

        static void ValueFromSumOfArrayElements(int value, int[] arr)
        {
            if (arr.Length == 0) return;

            int maxIndex = 0;
            int minIndex = 0;
            for (int i = 0; i < arr.Length; i++)
            {
                if (arr[i] > value) break;
                maxIndex = i;
            }

            while (minIndex != maxIndex)
            {
                if (arr[minIndex] + arr[maxIndex] == value)
                {
                    Console.WriteLine($"{arr[minIndex]}+{arr[maxIndex]}={value}");
                    return;
                }

                if (arr[minIndex] + arr[maxIndex] < value) minIndex++;
                else
                {
                    maxIndex--;
                    minIndex = 0;
                }
            }

            Console.WriteLine($"овтета нет");
            return;
        }

        static void PrimeNumbers(int[] arr)
        {
            var ans = new List<int>();
            foreach (var item in arr)
            {
                ans.Add(PrimeNumbersFind(item));
            }

            foreach (var item in ans)
            {
                Console.WriteLine(item);
            }
        }

        private static int PrimeNumbersFind(int item)
        {
            int count = 0;
            int value = 2;
            bool flag = true;

            while (true)
            {
                for (int i = 2; i < value; i++)
                {
                    if (value % i == 0)
                    {
                        flag = false;
                        break;
                    }
                }

                if (flag) count++;

                if (count == item) break;

                flag = true;
                value++;
            }

            return value;
        }
    }
}
