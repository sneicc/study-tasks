using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SEM2
{
    class Program
    {
        static void Main(string[] args)
        {
            var arr1 = new int[] { 1, 5, 6};
            var arr2 = new int[] {1, 6};
            var binaryNumber = new int[] { 1, 1, 1, 1, 1};

            //ArrayIntersection(arr1, arr2);
            //ArrayDifference(arr1, arr2);
            //MoveArray(2, arr1);
            //MergeArrays(arr1, arr2);

            FromBinaryToDecimal(binaryNumber);

            Console.WriteLine(CityBlocks(3, 3));
        }

        static int NOD(int a, int b)
        {
            if (b == 0) return a;
            return NOD(b, a % b);
        }

        static int CityBlocks(int n, int m)
        {
            return (n- 1) + (m - 1)- NOD((n - 1), (m - 1));
        }

        static int ReversNumber(int toRevers)
        {
            int reversed = 0;
            while (toRevers != 0 )
            {
                reversed = reversed * 10 + toRevers % 10;
                toRevers /= 10;
            }
            return reversed;
        }

        static void MergeArrays(int[] arr1, int[] arr2)
        {
            var ans = new int[arr1.Length + arr2.Length];

            int i, j, k;
            i = j = k = 0;

            while (i < arr1.Length && j < arr2.Length)
            {
                if (arr1[i] <= arr2[j])
                    ans[k++] = arr1[i++];                            
                else 
                    ans[k++] = arr2[j++];                    
            }

            while (j < arr2.Length)
                ans[k++] = arr2[j++];

            while (i < arr1.Length)
                ans[k++] = arr1[i++];

            foreach (var item in ans)
            {
                Console.WriteLine(item);
            }
        }

        static void ArrayDifference(int[] arr1, int[] arr2)
        {
            var temp1 = arr1.Distinct().ToArray();
            var temp2 = arr2.Distinct().ToArray();

            int uniqueCount = 0;
            int i, j, k;
            i = j = k = 0;

            while (i < temp1.Length && j < temp2.Length)
            {
                if (temp1[i] == temp2[j]) 
                {
                    i++;
                    j++;
                }
                else if (temp1[i] < temp2[j])
                { 
                    i++;
                    uniqueCount++;
                }
                else j++;
            }
            while (i < arr1.Length) 
            {
                i++;
                uniqueCount++;
            }               

            var ans = new int[uniqueCount];
            i = j = 0;
            while (i < temp1.Length && j < temp2.Length)
            {
                if (temp1[i] == temp2[j])
                {
                    i++;
                    j++;
                }
                else if (temp1[i] < temp2[j]) ans[k++] = temp1[i++];                   
                else j++;
            }
            while (i < arr1.Length)
                ans[k++] = arr1[i++];

            foreach (var item in ans)
            {
                Console.WriteLine(item);
            }
        }

        static void ArrayIntersection(int[] arr1, int[] arr2)
        {
            var temp1 = arr1.Distinct().ToArray();
            var temp2 = arr2.Distinct().ToArray();

            int intersectionCount = 0;
            int  i, j, k;
            i = j = k = 0;

            while (i < temp1.Length && j < temp2.Length)
            {
                if (temp1[i] < temp2[j]) i++;
                else if (temp1[i] == temp2[j])
                {
                    intersectionCount++;
                    j++;
                }
                else j++;          
            }

            var ans = new int[intersectionCount];
            i = j = 0;
            while (i < temp1.Length && j < temp2.Length)
            {
                if (temp1[i] < temp2[j]) i++;
                else if (temp1[i] == temp2[j]) ans[k++] = temp2[j++];
                else j++;
            }

            foreach (var item in ans)
            {
                Console.WriteLine(item);
            }
        }
        
        static void MoveArray(int move, int[] arr)
        {         
            for (int i = 0; i < move; i++)
            {
                int buf = arr[0];
                for (int j = 1; j < arr.Length; j++)
                {
                    arr[j - 1] = arr[j];
                    
                }
                arr[arr.Length - 1] = buf;
            }

            foreach (var item in arr)
            {
                Console.WriteLine(item);
            }            
        }

        static void FromBinaryToDecimal(int[] number)
        {
            int ans = 0;
            for (int i = 0; i < number.Length; i++)
            {
                if (number[i] == 0) continue;
                ans += (int)Math.Pow(2, i);
            }

            Console.WriteLine(ans);
        }
        
        static void Foo()
        {
            for (int i = 1; i <= 27; i++)
            {
                for (int j = 1; j < 10; j++)
                {
                    for (int k = 0; k < 10; k++)
                    {
                        for (int l = 0; l < 10; l++)
                        {
                            if (j + k + l == i) Console.WriteLine($"{i} || {j}{k}{l}");
                        }
                    }
                }
            }
        } 
    }

   
}
