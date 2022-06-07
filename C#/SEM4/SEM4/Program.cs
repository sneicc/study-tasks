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
            var arr = new int[] { 1, 2, 3, -1, 4 };
            FindSumInInterval(arr, 1, 5);
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
    }
}
