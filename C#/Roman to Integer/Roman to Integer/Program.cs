using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Roman_to_Integer
{
    enum RomeNumber
    {
        I = 1,
        IV = 4,
        V = 5,
        IX = 9,
        X = 10,
        XL = 40,
        L = 50,
        XC = 90,
        C = 100,
        CD = 400,
        D = 500,
        CM = 900,
        M = 1000
    }

    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine(RomanToIntNew("MCMXIIIIIVCIV"));
        }

        static public int RomanToIntNew(string s)
        {
            s = s
            .Replace("CM", "DCCCC")
            .Replace("CD", "CCCC")
            .Replace("XC", "LXXXX")
            .Replace("XL", "XXXX")
            .Replace("IX", "VIIII")
            .Replace("IV", "IIII");
            int result = 0;
            for (int i = 0; i < s.Length; i++)
            {
                switch (s[i])
                {
                    case 'I':
                        result += 1;
                        break;
                    case 'V':
                        result += 5;
                        break;
                    case 'X':
                        result += 10;
                        break;
                    case 'L':
                        result += 50;
                        break;
                    case 'C':
                        result += 100;
                        break;
                    case 'D':
                        result += 500;
                        break;
                    case 'M':
                        result += 1000;
                        break;
                }
            }
            return result;
        }

        static public int RomanToIntOld(string s)
        {
            int ans = 0;
            int border = s.Length - 1;
            for (int i = 0; i < s.Length; i++)
            {  
                switch (s[i])
                {
                    case 'I':
                        if (border != i && s[i + 1] == 'V')
                        {
                            ans += 4;
                            i++;
                        }
                        else if (border != i && s[i + 1] == 'X')
                        {
                            ans += 9;
                            i++;
                        }
                        else
                            ans += 1;
                        break;
                    case 'V':
                        ans += 5;
                        break;
                    case 'X':
                        if (border != i && s[i + 1] == 'L')
                        {
                            ans += 40;
                            i++;
                        }
                        else if (border != i && s[i + 1] == 'C')
                        {
                            ans += 90;
                            i++;
                        }
                        else
                            ans += 10;
                        break;
                    case 'L':
                        ans += 50;
                        break;
                    case 'C':
                        if (border != i && s[i + 1] == 'D')
                        {
                            ans += 400;
                            i++;
                        }
                        else if (border != i && s[i + 1] == 'M')
                        {
                            ans += 900;
                            i++;
                        }
                        else
                            ans += 100;
                        break;
                    case 'D':
                        ans += 500;
                        break;
                    case 'M':
                        ans += 1000;
                        break;
                    default:
                        Console.WriteLine("Error");
                        break;
                }
            }          
            return ans;
        }
    }
}
