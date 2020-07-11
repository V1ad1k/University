//

#include "pch.h"
#include <iostream>
#include "CFileLastError.h"
#include "CFileThrowEx.h"
#include "CFileErrorCode.h"
#include "fstream"
using namespace std;
void callCFileLastError (){
    CFileLastError cFileLastError = CFileLastError();
    for (int i=0;i<10;i++){
        cFileLastError.vOpenFile("BestTextEver.txt");
        cout << cFileLastError.bGetLastError() << endl;
    }
    }
void callCFileErrorCode (){
    CFileErrorCode cFileErrCode = CFileErrorCode();
    for(int i = 0; i < 10; i ++){
        cout << cFileErrCode.bOpenFile("textfile.txt") << endl;
    }

}
void callCFileThrowEx (){
    CFileThrowEx cFileThrowEx = CFileThrowEx();
        for(int i = 0; i < 10; i ++){
            cFileThrowEx.vOpenFile("BestTextEver.txt");
    }

}
int main()
{

    callCFileLastError();
    callCFileErrorCode();
    //callCFileThrowEx ();
    CFileLastError x("BestTextEver.txt");
    x.vOpenFile("BestTextEver.txt");
    if (x.bGetLastError() == 1) cout << "True"<<endl;
    if (x.bGetLastError() == 0) cout << "False"<<endl;
    x.vPrintLine("Last Error");
    if (x.bGetLastError() == 1) cout << "True"<<endl;
    if (x.bGetLastError() == 0) cout << "False"<<endl;
    x.vCloseFile("BestTextEver.txt");
    if (x.bGetLastError() == 1) cout << "True"<<endl;
    if (x.bGetLastError() == 0) cout << "False"<<endl;

    try
    {
        CFileThrowEx b("BestTextEver.txt");
        b.vOpenFile("BestTextEver.txt");
    }
    catch  (int e)
    {
        cout << "Exeption # "<<e<<endl;
    }
    CFileErrorCode y("BestTextEver.txt");
    cout << y.bOpenFile("BestTextEver.txt")<<endl;
    //cout << y.bPrintLine("BestTextEver.txt")<<endl;
    //cout << y.bCloseFile("BestTextEver.txt")<<endl;
    std::ofstream out("Result.txt");
    std::cout.rdbuf(out.rdbuf());
    return 0;
}


