using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Numerics;

namespace Perimeter
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine(FibPerimeter(1000000) * 4);
        }
        public static BigInteger FibPerimeter(int n)
        {
            BigInteger before1 = 0;
            BigInteger before2 = 1;
            BigInteger now;
            BigInteger ans = 0;
            for (int i = 0; i < n + 1; i++)
            {
                now = before1 + before2;
                before2 = before1;
                before1 = now;
                ans += now;
            }
            return ans;
        }
    }
}
