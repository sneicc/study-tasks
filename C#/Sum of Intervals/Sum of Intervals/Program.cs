using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Interval = System.ValueTuple<int, int>;

namespace Sum_of_Intervals
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine(SumIntervals(new Interval[] { (1, 2), (6, 10), (11, 15) }));
        }

        public static int SumIntervals((int, int)[] intervals)
        {
            int ans = 0;

            for (int i = 0; i < intervals.Length; i++)
            {
                for (int j = 0; j < intervals.Length; j++)
                {
                    if (j == i || ((intervals[j].Item1 == 0 && intervals[j].Item1 == 0)))
                       continue;
                    if (intervals[j].Item1 <= intervals[i].Item1 && intervals[i].Item1 <= intervals[j].Item2)
                    {
                        UpdateIntervals(ref intervals, i, j);
                    }
                    else if(intervals[j].Item1 <= intervals[i].Item2 && intervals[i].Item2 <= intervals[j].Item2)
                    {
                        UpdateIntervals(ref intervals, i, j);
                    }
                }
            }    
            foreach (var item in intervals)
            {
                ans += item.Item2 - item.Item1;
            }
            return ans;
        }
        public static void UpdateIntervals(ref Interval[] intervals, int i, int j)
        {
            int min = FindMin(intervals[j].Item1, intervals[i].Item1);
            int max = FindMax(intervals[j].Item2, intervals[i].Item2);
            intervals.SetValue((min, max), i);
            intervals.SetValue((0, 0), j);
        }
        public static int FindMin(int a, int b)
        {       
            return a < b ? a : b;
        }
        public static int FindMax(int a, int b)
        {
            return a > b ? a : b; ;
        }
    }
}
