using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Strip_Comments
{
    class Program
    {
        static void Main(string[] args)
        {
            StripComments("apples, pears # and bananas\ngrapes\n#\nbananas !apples", new string[] { "#", "!"});
        }

        public static string StripComments(string text, string[] commentSymbols)
        {
            bool flag1 = false;
            string ans = "";
            foreach (var comm in commentSymbols)
            {
                foreach (var item in text)
                {            
                    if (item == Convert.ToChar(comm) || flag1 == true)
                    {
                        flag1 = true;
                        if (item == '\n')
                        {
                            flag1 = false;
                            ans = ans.TrimEnd(' ');
                            ans += '\n';
                        }
                    }
                    else
                    {
                        if (item == '\n')
                            ans = ans.TrimEnd(' ');
                        ans += item;
                    }
                }
                text = ans;
                ans = "";
            }
            Console.WriteLine(text);
            text = text.TrimEnd(' ');
            return text;
        }

    }
}
