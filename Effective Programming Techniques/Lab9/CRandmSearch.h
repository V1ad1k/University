#ifndef CRandmSearch_H
#define CRandmSearch_H
#include "CMscnProblem.h"
#include <iostream>
#include <string>
class CRandmSearch
{
private:
    CMscnProblem *problem;
    bool error;
public:
    CRandmSearch(int pDost, int pFabr, int pMagaz, int pSklep);
    ~CRandmSearch();
    void getOptSolution(CMscnProblem *pProblem);
};

#endif