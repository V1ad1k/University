//
// Created by Vlad on 11/7/2019.
//

#include "pch.h"
#include "CFileThrowEx.h"
using namespace std;



CFileThrowEx::CFileThrowEx()
{

    pf_file = NULL;
}
// Konstruktor error
// I use class string instead of Cstring
CFileThrowEx::CFileThrowEx(string sFileName)
{

    pf_file = fopen(&sFileName[0], "w+");
    throw 1;
    if (pf_file == NULL);

}
// Dekonstr error
CFileThrowEx::~CFileThrowEx()
{
    if (pf_file != NULL)
        throw 2;
}
// OpenFile error
void CFileThrowEx::vOpenFile(string sFileName)
{

    pf_file = fopen(&sFileName[0], "w+");
    if (pf_file == NULL)
        throw 3;
}
// CloseFile error
void CFileThrowEx::vCloseFile(string sFileName)
{
    if (pf_file != NULL)
        fclose(pf_file);
    if (pf_file == NULL)
        throw 4;

}
//  Print error
void CFileThrowEx::vPrintLine(string sText)
{

    if (pf_file == NULL) {
        throw 5;
    }
    else {
        fprintf(pf_file, &sText[0]);
    }
}
//  Manylines-print error
void CFileThrowEx::vPrintManyLines(vector<char*> sText)
{

    for (int i = 0; i < sText.size(); i++) {
        if (pf_file == NULL) {
            throw 6;
        }
        else {
            fprintf(pf_file, sText[i]);
            cout << "Done MultiPrinting" << endl;
        }

    }
}