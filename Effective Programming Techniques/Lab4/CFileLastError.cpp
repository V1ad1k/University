//
// Created by Vlad on 11/7/2019.
//

#pragma once
//#pragma warning(disable:4996)
#include <iostream>
#include <string>
#include <vector>
#include <cstdio>
using namespace std;

class CFileLastError
{
private:
    bool b_last_error = true;
public:
    bool bGetLastError() { return(b_last_error); }
    CFileLastError();
    CFileLastError(string sFileName);
    ~CFileLastError();
    void vOpenFile(string sFileName);
    void vCloseFile(string sFileName);
    void vPrintLine(string sText);
    void vPrintManyLines(vector<string> sText);
private:
    FILE *pf_file;
};


