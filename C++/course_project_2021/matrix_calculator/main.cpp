#include "mainwindow.h"
#include <fstream>
#include <string>
#include <QApplication>

//#include <sstream>
using namespace std;

int main(int argc, char *argv[])
{
    setlocale(LC_ALL,"ru");
    QApplication a(argc, argv);
    MainWindow w;//создание главного окна
    w.show();

    return a.exec();
}
