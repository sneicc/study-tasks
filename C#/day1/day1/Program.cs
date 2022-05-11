using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace day1
{
    class Program
    {
        static void Main(string[] args)
        {
            //Console.WriteLine(BreakCamelCase("camelCaseGood"));

            //Anagrams("abba", new List<string> {"aabb", "abcd", "bbaa", "dada" });

            Console.WriteLine(IsPalindrome(121));
        }
        public static List<string> Anagrams(string word, List<string> words)
        {
            Dictionary<char, int> wordDict = CreateDict(word);
            List<string> ans = new List<string>();
            foreach (var item in words)
            {
                Dictionary<char, int> temp = CreateDict(item);
                if (temp.Count == wordDict.Count && !temp.Except(wordDict).Any())
                    ans.Add(item);
            }
            return ans;       
        }
        public static Dictionary<char, int> CreateDict(string word)
        {
            Dictionary<char, int> dict = new Dictionary<char, int>();
            foreach (var item in word)
            {
                if (dict.TryGetValue(item, out _))
                    dict[item]++;
                else
                    dict.Add(item, 1);
            }
            return dict;
        }
        public static string BreakCamelCase(string str)
        {
            // complete the function
            List<char> list = str.ToList();
            for (int i = 0; i < list.Count; i++)
            {
                if (Char.IsUpper(list[i]))
                    list.Insert(i++, ' ');                   
            }           
            return new string(list.ToArray());
        }
        public static string ToCamelCase(string str)
        {
            string[] strArr = str.Split('_', '-');
            strArr[0].ToLower();
            for (int i = 1; i < strArr.Length; i++)
                strArr[i] = strArr[i].Substring(0, 1).ToUpper() + strArr[i].Substring(1, strArr[i].Length - 1);
            return String.Join("", strArr);
        }
        public static string High(string s)
        {
            string[] arr = s.Split(' ');
            int[] index = new int[arr.Length];
            for (int i = 0; i < arr.Length; i++)
            {
                int summ = 0;
                char[] charArr = arr[i].ToCharArray();
                foreach (var item in charArr)
                {
                    summ += (int)item % 32;
                }
                index[i] = summ;
            }
            return arr[Array.IndexOf(index, index.Max())];
        }
        public static int Persistence(long n)
        {
            int count = 0;
            int mul = 1;
            while (n.ToString().Length > 1)
            {
                count++;
                foreach (var digit in n.ToString())
                    mul *= digit - '0';
                n = (long)mul;
                Console.WriteLine(n);
            }
            return count;
        }
        static void Resize(ref int[] arr, int size)
        {
            int[] myArr = (int[])arr.Clone();
            arr = new int[size];
            for (int i = 0; i < size && i < myArr.Length; i++)
                arr[i] = myArr[i];
        }

        static void addFirst(ref int[] arr)
        {
            int[] myArr = (int[])arr.Clone();
            arr = new int[arr.Length + 1];
            for (int i = 0; i < myArr.Length; i++)
                arr[i+1] = myArr[i];
        }

        static void addLast(ref int[] arr)
        {
            int[] myArr = (int[])arr.Clone();
            arr = new int[arr.Length + 1];
            for (int i = 0; i < myArr.Length; i++)
                arr[i] = myArr[i];
        }

        static void delFirst(ref int[] arr)
        {
            int[] myArr = (int[])arr.Clone();
            arr = new int[arr.Length - 1];
            for (int i = 0; i < arr.Length; i++)
                arr[i] = myArr[i + 1];
        }

        static void delLast(ref int[] arr)
        {
            int[] myArr = (int[])arr.Clone();
            arr = new int[arr.Length - 1];
            for (int i = 0; i < arr.Length; i++)
                arr[i] = myArr[i];
        }

        public static bool IsPalindrome(int x)
        {
            List<int> list = new List<int>();
            while (x != 0)
            {
                list.Add(x % 10);
                x /= 10;
            }

            for (int i = 0, j = list.Count - 1; i < list.Count; i++, j--)
            {
                if (list[i] != list[j])
                    return false;
            }
            return true;
        }
    }
}
