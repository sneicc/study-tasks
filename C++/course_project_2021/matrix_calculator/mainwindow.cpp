#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <string>
#include<fstream>
#include <QMessageBox>
#include <QRegularExpressionValidator>
#include<QDebug>


using namespace std;


int changeM =-2;
int changerow= -2;
int changerow2=-2;
double *buf;
double flag = false;
UPmatrix m;

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    this->setFixedSize(903,849);
    this->setWindowTitle("Калькулятор матриц");

    string Data = "";

    ifstream fin;
    fin.open("data.txt");//считывание матриц из файла
    if(fin){
        while (!fin.eof()) {
            getline(fin,Data);
        }
    }
    fin.close();

    int size =0;
    for (int i =0;i < Data.size(); i++) {
        if(Data[i]== ' ') size++;
    }
    this->OCspace = size-1;

    int j =0;

    for (int i =0;i<size;i++ ) {//парсинг матриц и запись их в массив
        string num="";
        bool flag = false;
        int count =0;
        for (;j < Data.size(); ) {
            if(Data[j] == ' '){
                j++;
                break;
            } else if(flag == false){
                if(Data[j] == ','){
                    this->data[i].size = stoi(num);
                    num="";
                    flag = true;
                }else{
                    num += Data[j];
                }
            } else {
                this->data[i].elements = new double[CElements(this->data[i].size)];
                while(Data[j] !=' '){
                    if(Data[j] == ','){
                        this->data[i].elements[count] = stod(num);
                        count++;
                        num ="";
                    }else{
                        num+= Data[j];
                    }
                    j++;
                }
                this->data[i].elements[count] = stod(num);
                j--;
            }
            j++;
        }

    }

    listup();
    ui->lineEdit_6->hide();
    ui->label_10->hide();
    ui->pushButton_8->hide();

    ui->lineEdit->setValidator(new QIntValidator(1,1000,this));
    ui->lineEdit_2->setValidator(new QIntValidator(1,200,this));
    ui->lineEdit_3->setValidator(new QIntValidator(1,10000,this));
    ui->lineEdit_4->setValidator(new QIntValidator(1,10000,this));
    ui->lineEdit_5->setValidator(new QRegularExpressionValidator(QRegularExpression("^-?(\\d{1,7}([.]\\d{1,5}))"), this));
    ui->lineEdit_6->setValidator(new QIntValidator(1,1000,this));
    ui->lineEdit_7->setValidator(new QRegularExpressionValidator(QRegularExpression("^-?(\\d{1,7}([.]\\d{1,5})?( (-)?))+"), this));
    ui->lineEdit_8->setValidator(new QIntValidator(1,1000,this));
    ui->lineEdit_9->setValidator(new QIntValidator(1,1000,this));
    ui->lineEdit_10->setValidator(new QIntValidator(1,1000,this));
    ui->lineEdit_11->setValidator(new QIntValidator(1,1000,this));
    ui->lineEdit_12->setValidator(new QIntValidator(1,1000,this));



}

MainWindow::~MainWindow()
{
    delete ui;
}


void MainWindow::on_pushButton_clicked()//выбор матрицы для построчного ввода
{
    int choose =  ui->lineEdit->text().toInt()-1;
    int Modrow =  ui->lineEdit_6->text().toInt()-1;
    if(choose<= this->OCspace && choose>=0){
        draw(choose);


        if(!(ui->radioButton->isChecked())){
            if(Modrow == -1){
                QMessageBox::information(this,"Ошибка","Введите номер строки !");
                return;
            }else if (Modrow <-1 || Modrow >= this->data[choose].size){
                QMessageBox::information(this,"Ошибка","Строка не существует !");
                return;
            }else{
                changerow =Modrow;
                changerow2 = changerow;
                QMessageBox::information(this,"Успех","Выбрана матрица и строка");
            }
        }else{
            changerow =0;
            changerow2 = changerow;
            QMessageBox::information(this,"Успех","Матрица выбрана !");
        }
        changeM = choose;
        delete [] buf;
        buf = new double[CElements(this->data[choose].size)];

        QString s="Введите ";
        s+=QString::number(Neednumbers(choose));
        s+=" чисел";
        ui->label_9->setText(s);


    }else{
        QMessageBox::information(this,"Ошибка","Матрица не существует !");
    }

}

void MainWindow::on_pushButton_2_clicked()//Создание верхней треугольной матрицы заданного размера
{
    if(this->OCspace+1 >1000){
       QMessageBox::information(this,"Ошибка","Достигнут предел количества матриц");
       return;
    }
    int size = ui->lineEdit_2->text().toInt();
    if(size > 200 || size<=0){
        QMessageBox::information(this,"Ошибка","Допустимы размер матрицы от 1 до 200");
        return;
    }
    this->OCspace++; 
    this->data[this->OCspace].size = size;

    this->data[this->OCspace].elements = new double[CElements(size)];
    double element=0;

    int count=0;
    for (int i =0;i < size; i++ ) {
        for (int k= size - i;k!=0 ;k-- ) {
            if(!(ui->radioButton_6->isChecked()))
            element = rand()% (100 - 0 + 1) + 0;
            this->data[this->OCspace].elements[count]=element;
            count++;
        }

    }

    save();
}

void MainWindow::save(){//сохранение матриц в файл
    ofstream fout;
    fout.open("data.txt");
    for (int i =0;i <= this->OCspace; i++ ) {
        fout << this->data[i].size;
        int count=0;
        for (int g =0;g < this->data[i].size; g++ ) {
            for (int k= this->data[i].size - g;k!=0 ;k-- ) {
                fout << ',';
                fout << this->data[i].elements[count];
                count++;
            }
        }
        fout << ' ';

    }
    fout.close();
    listup();
}

void MainWindow::draw(int choose){//отрисовка матрицы

    QString matrix = "";
    QString zero="";

    int count=0;
    for (int i =0;i < this->data[choose].size; i++ ) {
        for (int j = i;j!=0 ;j-- ) {
            matrix+= '0';
            matrix+= '\t';

        }
        for (int k= this->data[choose].size - i;k!=0 ;k-- ) {

            matrix+= QString::number(this->data[choose].elements[count]);
            matrix+= '\t';

            count++;
        }
        matrix+= '\n';

    }

    ui->textEdit->setText(matrix);

}

void MainWindow::on_listWidget_itemClicked(QListWidgetItem *item)//выбор матрицы из списка
{
    int row = ui->listWidget->row(item);
    draw(row);

    this->row=row;
    ui->lineEdit->setText(QString::number(row+1));
    ui->lineEdit_8->setText(QString::number(row+1));
    if(ui->lineEdit_9->text()=="")
    ui->lineEdit_9->setText(QString::number(row+1));
    else
    ui->lineEdit_10->setText(QString::number(row+1));
}

int MainWindow::CElements(int size){//количество ненулевых элементов
    int celement=0;
    for (int i =0;i < size; i++ ) {
        for (int k= size - i;k!=0 ;k-- ) {
            celement++;
        }
    }
    return celement;
}

void MainWindow::listup(){//обновление списка матриц
    ui->listWidget->clear();

    for (int i =0;i<=this->OCspace;i++) {
        QString name = "Матрица ";
        name+= QString::number(i+1);
        name+= " (";
        name+= QString::number(this->data[i].size);
        name+= "x";
        name+= QString::number(this->data[i].size);
        name+= ")";
        ui->listWidget->addItem(name);
    }
}



void MainWindow::on_pushButton_4_clicked()//получение i j элемента
{
    if(this->row!= -1){
        int I = ui->lineEdit_3->text().toInt() -1;
        int J = ui->lineEdit_4->text().toInt() -1;
        if(I >= 0 && J >= 0){
            int count=0;

            for (int i =0;i < this->data[this->row].size; i++ ) {
                for (int k= 0 + i;k < this->data[this->row].size ;k++ ) {
                    if(i == I && k == J){
                        ui->lineEdit_5->setText(QString::number(this->data[this->row].elements[count]));
                        return;
                    }
                    count++;
                }
            }

            QMessageBox::information(this,"Ошибка","Неверный номер элемента");

        }else{

            QMessageBox::information(this,"Ошибка","Введите значения больше 0");

        }
    }else{
        QMessageBox::information(this,"Ошибка","Выберите матрицу");
    }

}


void MainWindow::on_pushButton_3_clicked()//установка i j элемента
{
    if(this->row!= -1){
        int I = ui->lineEdit_3->text().toInt() -1;
        int J = ui->lineEdit_4->text().toInt() -1;
        double element = ui->lineEdit_5->text().toDouble();
        if(I >= 0 && J >= 0){
            bool pass = false;
            int count=0;

            for (int i =0;i < this->data[this->row].size; i++ ) {
                for (int k= 0 + i;k < this->data[this->row].size ;k++ ) {
                    if(i == I && k == J){
                        this->data[this->row].elements[count] = element;
                        save();
                        draw(this->row);
                        return;

                    }
                    count++;
                }
            }
            if(pass == false){
                QMessageBox::information(this,"Ошибка","Неверный номер элемента");
            }
        }else{

             QMessageBox::information(this,"Ошибка","Введите значения больше 0");

        }
    }else{
         QMessageBox::information(this,"Ошибка","Выберите матрицу");
    }
}


void MainWindow::on_pushButton_5_clicked()//посточный ввод матрицы
{
    if(!(ui->radioButton->isChecked()) &&  (changeM==-2)){
        QMessageBox::information(this,"Ошибка","Нажмите кнопку <<Выбрать матрицу>>");
        return;
    }
    if(changeM==-2){
        QMessageBox::information(this,"Ошибка","Нажмите кнопку <<Выбрать матрицу>>");
        return;
    }

    QString data = ui->lineEdit_7->text();
    string Data = data.toStdString();

    if(Data[Data.length()-1]==' '){
        QMessageBox::information(this,"Ошибка","Неверное формат, в конце строки не должно быть пробелов");
        return;
    }
    int sz = Neednumbers(changeM);
    int datasize =0;
    for (int j =0;j < Data.size();j++ ) {
       if(Data[j] == ' '){
            datasize++;
        }
    }
    if(sz != datasize+1){
        QMessageBox::information(this,"Ошибка","Неверное количество элементов");
        return;
    }
    string num="";
    int count=0;
    if(!(ui->radioButton->isChecked())){
        for (int j =0;j < Data.size();j++ ) {
           if(Data[j] == ' '){
                buf[count] = stod(num);
                count++;
                num ="";
            }else{
                num+= Data[j];
            }
        }
        buf[count] = stod(num);
        int count2 =0;
        int bufcount=0;
        for (int i =0;i < this->data[changeM].size; i++ ) {
            for (int k= 0 + i;k < this->data[changeM].size ;k++ ) {
                if(i == changerow2){
                    this->data[changeM].elements[count2] = buf[bufcount];
                    bufcount++;
                }
                count2++;
            }
            if(bufcount > 0)break;
        }

        QString s="Матрица не выбрана";
        ui->lineEdit_7->setText("");
        ui->label_9->setText(s);
        QMessageBox::information(this,"Успех","Значения успешно установлены");
        changerow=-2;
        save();
        draw(changeM);
    }else{
        for (int j =0;j < Data.size();j++ ) {
            if(Data[j] == ' '){
                buf[count] = stod(num);
                count++;
                num ="";
            }else{
                num+= Data[j];
            }
        }
        buf[count] = stod(num);
        int count2 =0;
        int bufcount=0;
        for (int i =0;i < this->data[changeM].size; i++ ) {
            for (int k= 0 + i;k < this->data[changeM].size ;k++ ) {
                if(i == changerow2){
                    this->data[changeM].elements[count2] = buf[bufcount];
                    bufcount++;
                }
                count2++;
            }
            if(bufcount > 0)break;
        }
        changerow2++;
        ui->lineEdit_7->setText("");
        if(changerow2 == this->data[changeM].size){

            QString s="Матрица не выбрана";
            ui->label_9->setText(s);
            QMessageBox::information(this,"Успех","Все значения введены");
            changerow2=-2;
            save();
            draw(changeM);
            return;
        }
        QString s="Введите ";
        s+=QString::number(Neednumbers(changeM));
        s+=" чисел";
        ui->label_9->setText(s);
        save();
        draw(changeM);

    }
}




int MainWindow::Neednumbers(int choose){//количество ненулевых элементов в строке
    bool flag = false;
    for (int i =0;i < this->data[choose].size; i++ ) {
        int count=0;
        for (int k= 0 + i;k < this->data[choose].size ;k++ ) {
            if(i == changerow2 ){
                count++;
                flag = true;
            }
        }
        if(flag == true){
            return count;
        }
    }
    return -1;

}


void MainWindow::on_radioButton_2_clicked()//выбор режма построчного ввода матриц
{
    ui->lineEdit_6->show();
    ui->label_10->show();
    QString s="Матрица не выбрана";
    ui->label_9->setText(s);
    changeM = -2;
}


void MainWindow::on_radioButton_clicked()//выбор режма построчного ввода матриц
{
    ui->lineEdit_6->hide();
    ui->label_10->hide();
    QString s="Матрица не выбрана";
    ui->label_9->setText(s);
    changeM = -2;
}


void MainWindow::on_pushButton_6_clicked()//калькулятор матриц
{

    int choose = ui->lineEdit_9->text().toInt()-1;
    int choose2 = ui->lineEdit_10->text().toInt()-1;

    if((choose == -1 || choose2 == -1)||(choose > this->OCspace || choose2 > this->OCspace)){
        QMessageBox::information(this,"Ошибка","Матрица не существует");
        return;
    }
    if(this->data[choose].size != this->data[choose2].size){
        QMessageBox::information(this,"Ошибка","Размеры матриц не совпадают");
        return;
    }

    delete [] m.elements;
    m.elements = new double[CElements(this->data[choose].size)];
    m.size=this->data[choose].size;

    if(ui->radioButton_3->isChecked()){ //сложение
        for(int i=0; i< CElements(this->data[choose].size); i++)
            m.elements[i]= this->data[choose].elements[i] + this->data[choose2].elements[i];

    }else if(ui->radioButton_4->isChecked()){//вычитание
        for(int i=0; i< CElements(this->data[choose].size); i++)
            m.elements[i]= this->data[choose].elements[i] - this->data[choose2].elements[i];

    }else {//умножение
        double **arrA = new double*[this->data[choose].size];
        double **arrB = new double*[this->data[choose].size];
        double **arrC = new double*[this->data[choose].size];
        for (int i =0;i<this->data[choose].size ;i++ ) {
            arrA[i]=new double[this->data[choose].size];
            arrB[i]=new double[this->data[choose].size];
            arrC[i]=new double[this->data[choose].size];
        }
        int count=0;

        for (int i =0;i < this->data[choose].size; i++ ) {
            for (int j = 0;j!=i ;j++ ) {
                arrA[i][j] = 0;

            }
            for (int k= 0 + i;k < this->data[choose].size ;k++ ) {
                arrA[i][k]= this->data[choose].elements[count];
                count++;
            }

        }

        count =0;
        for (int i =0;i < this->data[choose2].size; i++ ) {
            for (int j = 0;j!=i ;j++ ) {
                arrB[i][j] = 0;

            }
            for (int k= 0 + i;k < this->data[choose2].size ;k++ ) {
                arrB[i][k]= this->data[choose2].elements[count];
                count++;
            }

        }

        for (int i = 0; i < this->data[choose].size; i++){
            for (int j = 0; j < this->data[choose].size; j++){
                arrC[i][j] = 0;
                for (int k = 0; k < this->data[choose].size; k++){
                    arrC[i][j] += arrA[i][k] * arrB[k][j];
                }
            }
        }

        count=0;
        for (int i =0;i < this->data[choose].size; i++ ) {
            for (int k= 0 + i;k < this->data[choose].size ;k++ ) {
                m.elements[count]= arrC[i][k];
                count++;
            }

        }

        for (int i =0;i<this->data[choose].size ;i++ ) {
            delete [] arrA[i];
            delete [] arrB[i];
            delete [] arrC[i];
        }
        delete [] arrA;
        delete [] arrB;
        delete [] arrC;


    }

    QString matrix = "";
    QString zero="";

    int countd=0;
    for (int i =0;i < this->data[choose].size; i++ ) {
        for (int j = i;j!=0 ;j-- ) {
            matrix+= '0';
            matrix+= '\t';

        }
        for (int k= this->data[choose].size - i;k!=0 ;k-- ) {

            matrix+= QString::number(m.elements[countd]);
            matrix+= '\t';

            countd++;
        }
        matrix+= '\n';

    }

    ui->textEdit->setText(matrix);
    ui->pushButton_8->show();


}


void MainWindow::on_pushButton_7_clicked()//удаление матрицы
{
    int choose = ui->lineEdit_8->text().toInt()-1;
    if(this->OCspace < choose || choose<0){
        QMessageBox::information(this,"Ошибка","Матрица не существует");
        return;
    }

    UPmatrix *arr = new UPmatrix[this->OCspace+1];
    for (int i =0;i<= this->OCspace; i++ ) {
        arr[i].size = this->data[i].size;
        arr[i].elements = new double [CElements(arr[i].size)];
        for (int k =0;k< CElements(arr[i].size);k++ ) {
          arr[i].elements[k] = this->data->elements[k];
        }
    }
    for (int i =choose;i<= this->OCspace;i++ ) {
      delete [] this->data[i].elements;
    }
    for(int i = choose; i < this->OCspace; i++ ){
        this->data[i].size=arr[i+1].size;
        this->data[i].elements = new double [CElements(arr[i+1].size)];
        for (int k =0;k < CElements(arr[i+1].size);k++ ) {
           this->data[i].elements[k]=arr[i+1].elements[k];
        }

    }


    for(int i = 0; i <= this->OCspace; i++ ){
        delete [] arr[i].elements;
    }

    ui->textEdit->clear();

    this->OCspace--;
    delete[] arr;
    this->row = -1;
    save();


}



void MainWindow::on_pushButton_8_clicked()//сохранение результирующей матрицы
{
    ui->pushButton_8->hide();
    if(this->OCspace+1 >1000){
       QMessageBox::information(this,"Ошибка","Достигнут предел количества матриц");
       return;
    }

    this->OCspace++;
    this->data[this->OCspace].elements = new double[CElements(m.size)];
    for (int i =0;i < CElements(m.size) ;i++ ) {
        this->data[this->OCspace].elements[i]=m.elements[i];
    }
    this->data[this->OCspace].size=m.size;
    save();

}


void MainWindow::on_pushButton_9_clicked()//присвоение матриц
{
    int choose = ui->lineEdit_11->text().toInt()-1;
    int choose2 = ui->lineEdit_12->text().toInt()-1;
    if((choose == -1 || choose2 == -1)||(choose > this->OCspace || choose2 > this->OCspace)){
        QMessageBox::information(this,"Ошибка","Матрица не существует");
        return;
    }
    if(this->data[choose].size != this->data[choose2].size){
        QMessageBox::information(this,"Ошибка","Размеры матриц не совпадают");
        return;
    }

    for (int i=0;i < CElements(this->data[choose].size);i++ ) {
        this->data[choose2].elements[i]=this->data[choose].elements[i];
    }
    save();

}


void MainWindow::on_pushButton_10_clicked()//сравнение матриц
{
    int choose = ui->lineEdit_11->text().toInt()-1;
    int choose2 = ui->lineEdit_12->text().toInt()-1;
    if((choose == -1 || choose2 == -1)||(choose > this->OCspace || choose2 > this->OCspace)){
        QMessageBox::information(this,"Ошибка","Матрица не существует");
        return;
    }
    if(this->data[choose].size != this->data[choose2].size){
        QMessageBox::information(this,"Ошибка","Размеры матриц не совпадают");
        return;
    }
    for (int i=0;i < CElements(this->data[choose].size);i++ ) {
        if(fabs(this->data[choose2].elements[i] - this->data[choose].elements[i]) > 1.0e-6){
            QMessageBox::information(this,"Результат","Матрицы не равны");
            return;
        }
    }
    QMessageBox::information(this,"Результат","Матрицы равны");
}

