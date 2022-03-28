#ifndef SECONDWINDOW_H
#define SECONDWINDOW_H

#include <QMainWindow>


namespace Ui {
class secondwindow;
}

class secondwindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit secondwindow(QWidget *parent = nullptr);
    ~secondwindow();

private:
    Ui::secondwindow *ui;

public slots:
    void slot();

};

#endif // SECONDWINDOW_H
