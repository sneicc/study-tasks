using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Longest_Common_Prefix
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine(LongestCommonPrefix(new string[] { "flower", "flow", "flight" }));
        }

        public static string LongestCommonPrefix(string[] strs)
        {
            //var sortStrs = strs.OrderBy(x => x).ToArray();
            StringBuilder sb = new StringBuilder();
            Array.Sort(strs, (x, y) => x.Length.CompareTo(y.Length));  
            string firstString = strs[0];

            for (int i = 0; i < firstString.Length; i++)
            {
                for (int j = 1; j < strs.Length; j++)
                {
                    if (firstString[i] != strs[j][i])
                        return sb.ToString();
                }
                sb.Append(firstString[i]);
            }                
            return sb.ToString();
        }
    }
}
