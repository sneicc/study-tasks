using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HM_Recursion
{
    class Program
    {
        static void Main(string[] args)
        {
            //int[] arr = new int[] { 1, 2, 3, 4, 5 };
            //int a = 0;
            //DrawArr(arr);
            //SummArr(arr);
            //SummOfDigitsInDigit(a);
            //string mem = "всем привет , учусь в МИРЭА , мемы смешные";
            string mem = "всем привет , учусь в МИРЭА , мемы смешные";
            List<string> lst = mem.Split(' ').ToList<string>();
            //meme(mem.Split(' '));
            meme(lst);


        }

        static void DrawArr(int[] arr, int i = 0)
        {
            if (i < arr.Length)
            {
                Console.WriteLine(arr[i]);
                DrawArr(arr, ++i);
            }
        }

        static void SummArr(int[] arr, int i = 0, int summ = 0)
        {
            if (i < arr.Length)
            {
                summ += arr[i];
                SummArr(arr, ++i, summ);
            }
            else
            {
                Console.WriteLine($"Сумма элементов массива = {summ}");
            }
        }

        static void SummOfDigitsInDigit(int a, int summ = 0)
        {
            summ += a % 10;
            if (a / 10 > 0)             
                SummOfDigitsInDigit(a / 10, summ);
            else
                Console.WriteLine($"Сумма чисел, составляющих число = {summ}");          
        }

        static void meme(string[] arr, int index = 0, string ans = "")
        {
            if (index >= arr.Length)
            {
                //IEnumerable<string> allWords = ans.Split(' ');
                //IEnumerable<string> uniqueWords = allWords.GroupBy(w => w).Where(g => g.Count() == 1).Select(g => g.Key);
                //string answer = "";
                //foreach (var item in uniqueWords)
                //{
                //    answer += item + " ";
                //}
                Console.WriteLine(ans + " \r\n");
                //File.AppendAllText(@"C:\Users\andre\source\repos\HM_Recursion\HM_Recursion\mem.txt", ans + " \r\n");
                return;
            }

            for (int i = 0; i < arr.Length; i++)
            {
                meme(arr, index + 1, ans += arr[i] + " ");
                ans = ans.Substring(0, ans.Length - (arr[i].Length + 1));
            }
        }

        static void meme(List<string> lst, string ans = "")
        {

            if (lst.Count == 0)
            {
                Console.WriteLine(ans + " \r\n");
                File.AppendAllText(@"C:\Users\andre\source\repos\HM_Recursion\HM_Recursion\mem.txt", ans + " \r\n");
                return;
            }

            for (int i = 0; i < lst.Count; i++)
            {
                string temp = lst[i];
                lst.RemoveAt(i);
                meme(lst, ans + " " + temp);
                lst.Insert(i, temp);
            }
        }
    }
}
