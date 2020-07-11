#ifndef CMscnProblem_H
#define CMscnProblem_H
#include<iostream>
#include<string>
#include<vector>
#include "CTable.h"
#include "CRandom.h"

using namespace std;

#define ILOSC_DOSTAWCOW 2
#define ILOSC_FABRYK 3
#define ILOSC_MAGAZYNOW 3
#define ILOSC_SKLEPOW 5

class CMscnProblem
{
private:
    int iloscDostawcow, iloscFabryk, iloscMagazynow, iloscSklepow, i_size, s_size;
    CTable *cd, *cf, *cm, *xd, *xf, *xm,
            *sd, *sf, *sm, *ss, *ud, *uf, *um, *ps,
            *xdminmax, *xfminmax, *xmminmax;
    double *solution, z;
public:
    CMscnProblem();
    CMscnProblem(const int pDost, const int pFabr, const int pMagaz, const int pSklep);
    ~CMscnProblem();
    bool bSetIloscDostawcow(const int pDost);
    bool bSetIloscFabryk(const int pFabr);
    bool bSetIloscMagazynow(const int pMagaz);
    bool bSetIloscSklepow(const int pSklep);
    bool bSetValueToMatrix(CTable *pTab, const double pValue, const int pN, const int pM);
    void vPrintHeader();

    void vRefill();
    void vGenerateInstance();
    void vAddTab(CTable *pTab);
    void vCreateSolution();
    bool bCheckToOdd(double * pdSolution);
    double oblStep(int pMode, int pS, int pK, double * pdSolution);
    double dGetQuality(double *pdSolution, bool &error);
    bool bConstraintsSatisfied(double *pdSolution, bool &error);
    bool checkWarunek(int pMode, int &pSizeParam, int pS, int pK, double * pdSolution);
    bool checkWarunekForMatrix(int pSizeParam, int pSizeParam1, int pSizeParam2, int pS, int pK, int pS1, int pK1, double * pdSolution);
    void vSprawdzenieMinMax(double *pdSolution);
    bool bZapisWPlik();
    bool bOdczytZPlik(string pPath);
    void vCopy(CMscnProblem *pProblem);

    double *getSolution() { return solution; };
    string getTableName(int pIndex);
    int getTableSize(int pIndex);
    int iGetIloscD() { return iloscDostawcow; };
    int iGetIloscF() { return iloscFabryk; };
    int iGetIloscM() { return iloscMagazynow; };
    int iGetIloscS() { return iloscSklepow; };
};

#endif