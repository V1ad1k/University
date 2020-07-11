//
// Created by Vlad on 11/7/2019.
//
// #pragma once is a non-standard but widely supported preprocessor directive designed to cause the current source file to be included only once in a single compilation
#pragma once
#include <cstdio>
#include <string>
#include <vector>
#include <iostream>
using namespace std;

class CFileErrorCode
{
public:
    CFileErrorCode();
    CFileErrorCode(string sFileName);
    ~CFileErrorCode();
    bool bOpenFile(string sFileName);
    bool bCloseFile(string sFileName);
    bool bPrintLine(string sText);
    bool bPrintManyLines(vector<string> sText);
private:
    FILE *pf_file;
};

