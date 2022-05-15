using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Valid_Parentheses
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine(IsValid("[(()[]{})]"));
        }

        public static bool IsValid(string s)
        {
            var stack = new Stack<char>();

            foreach (var item in s)
            {
                if (item == ')' || item == ']' || item == '}')
                {
                    if (stack.Count == 0)
                        return false;
                }
                
                switch (item)
                {
                    case ')':
                        if ('(' != stack.Pop())
                            return false;
                        break;
                    case '}':
                        if ('{' != stack.Pop())
                            return false;
                        break;
                    case ']':
                        if ('[' != stack.Pop())
                            return false;
                        break;
                    default:
                        stack.Push(item);
                        break;
                }
            }
            return stack.Count == 0;
        }
    }
}
