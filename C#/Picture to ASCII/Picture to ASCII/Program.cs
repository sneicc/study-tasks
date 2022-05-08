using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Drawing;

namespace Picture_to_ASCII
{
    class Program
    {
        private const double WIDTH_OFFSET = 1.5;
        private const int MAX_WIDTH = 350;

        [STAThread]
        static void Main(string[] args)
        {
            var openFileDialog = new OpenFileDialog
            {
                Filter = "Images | *.bmp; *.png; *.jpg; *.JPEG"
            };

            Console.WriteLine("Нажмите Enter\n");

            while (true)
            {
                Console.ReadLine();

                if (openFileDialog.ShowDialog() != DialogResult.OK)
                    continue;

                Console.Clear();

                var bitmap = new Bitmap(openFileDialog.FileName);
                bitmap = Resize(bitmap);
                bitmap.ToGrayColor();

                var converter = new BmapToASCII(bitmap);
                var rows = converter.Convert();

                foreach (var row in rows)
                {
                    Console.WriteLine(row);
                }
                Console.SetCursorPosition(0, 0);
            }
        }

        private static Bitmap Resize(Bitmap bitmap)
        {
            var maxWidth = MAX_WIDTH;
            var newHeight = bitmap.Height / WIDTH_OFFSET * maxWidth / bitmap.Width;
            if (bitmap.Width > maxWidth || bitmap.Width > newHeight)
                bitmap = new Bitmap(bitmap, new Size(maxWidth, (int)newHeight));
            return bitmap;
        }
    }
}
