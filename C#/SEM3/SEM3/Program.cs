using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SEM3
{
    class Program
    {
        static void Main(string[] args)
        {

            Console.WriteLine(OptimizeContacts());

        }

        private static string OptimizeContacts()
        {
			var commands = new string[] { "push Привет каво чё а лооооо", "pop 11", "push некст строка", "pop 6"};
			var sb = new StringBuilder();
			foreach (var line in commands)
			{
				if (line == string.Empty) continue;

				var command = line.Substring(0, line.IndexOf(' '));
				if (command == "pop")
				{
					var countForDelete = int.Parse(line.Substring(3, line.Length - 3));
					sb.Remove(sb.Length - countForDelete, countForDelete);
				}
				else if (command == "push") sb.Append(line.Substring(5, line.Length - 5));
				else sb.Append(line);
			}
			return sb.ToString();
		}
    }
}
