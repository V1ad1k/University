#pragma once
#include <cstdio>
#include <string>
#include <vector>
#include <iostream>
using namespace std;
class CFileThrowEx
{
public:
    CFileThrowEx();
    CFileThrowEx(string sFileName);
    ~CFileThrowEx();
    void vOpenFile(string sFileName);
    void vCloseFile(string sFileName);
    void vPrintLine(string sText);
    void vPrintManyLines(vector<char*> sText);
private:
    FILE *pf_file;
};

