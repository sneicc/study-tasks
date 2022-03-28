#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "QListWidgetItem"
#include <string>
#include <secondwindow.h>
using namespace std;
QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class UPmatrix{

public: int size;

public: double *elements;


};

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

private slots:
    void on_pushButton_clicked();

    void on_pushButton_2_clicked();

    void save();

    void draw(int choose);

    void on_listWidget_itemClicked(QListWidgetItem *item);

    void listup();

    int CElements(int size);

    void on_pushButton_4_clicked();

    void on_pushButton_3_clicked();

    void on_pushButton_5_clicked();

    int Neednumbers(int choose);

    void on_radioButton_2_clicked();

    void on_radioButton_clicked();

    void on_pushButton_6_clicked();

    void on_pushButton_7_clicked();

    void on_pushButton_8_clicked();

    void on_pushButton_9_clicked();

    void on_pushButton_10_clicked();

private:
    Ui::MainWindow *ui;
    secondwindow *window;

public: UPmatrix data[1000];

public: int OCspace = -1;

public: int row = -1;

};



#endif // MAINWINDOW_H
