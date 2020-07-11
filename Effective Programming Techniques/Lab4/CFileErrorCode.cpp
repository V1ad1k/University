//
// Created by Vlad on 11/7/2019.
//

#include "pch.h"
#include "CFileErrorCode.h"
//#define _CRT_SECURE_NO_DEPRECATE
using namespace std;


CFileErrorCode::CFileErrorCode()
{
    pf_file = NULL;
}

CFileErrorCode::CFileErrorCode(string sFileName)
{
    pf_file = fopen(&sFileName[0], "w+");
}


CFileErrorCode::~CFileErrorCode()
{
    if (pf_file != NULL)
        fclose(pf_file);
}

bool CFileErrorCode::bOpenFile(string sFileName)
{
    pf_file = fopen(&sFileName[0], "w+");

    cout << "openFile error: \n";
    return true;

}

bool CFileErrorCode::bCloseFile(string sFileName)
{
    if (pf_file != NULL)
        fclose(pf_file);
    if (pf_file == NULL)
        return false;
    else cout << "closeFile error: ";
    return true;
}

bool CFileErrorCode::bPrintLine(string sText)
{
    int result = fprintf(pf_file, &sText[0]);
    if (result > 0) return false;
    else cout << "print error: ";
    return true;
}

bool CFileErrorCode::bPrintManyLines(vector<string> sText)
{
    return false;
}
