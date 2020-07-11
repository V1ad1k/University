#include "CRandmSearch.h"
#include <iostream>
#include <string>

CRandmSearch::CRandmSearch(int pDost, int pFabr, int pMagaz, int pSklep)
{
    error = false;
    problem = new CMscnProblem(pDost, pFabr, pMagaz, pSklep);
}

CRandmSearch::~CRandmSearch()
{
    if (problem != NULL) delete problem;
}

void CRandmSearch::getOptSolution(CMscnProblem *pProblem)
{
    for (int i = 0; i < 100; i++)
    {
        problem->vRefill();
        if (problem->dGetQuality(problem->getSolution(), error) > pProblem->dGetQuality(pProblem->getSolution(), error)) pProblem->vCopy(problem);
    }
}
