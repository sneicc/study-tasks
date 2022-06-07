using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NUnit.Framework;

namespace SEM4
{
    [TestFixture]
    public class Tests
    {
        [TestCase(1, 1)]
        [TestCase(1, 2)]
        [TestCase(3, 9)]
        public void test(int a, int b)
        {
            Assert.AreEqual(a, b);
        }
    }
}


