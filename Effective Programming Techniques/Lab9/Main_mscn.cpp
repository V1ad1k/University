#include "CMscnProblem.h"
#include "Main_mscn.h"
#include "CRandmSearch.h"
#include <iostream>
#include <string>
int main()
{
//    bool error1 = false;
//    CMscnProblem problemFile;
//    problemFile.bOdczytZPlik("problem.txt");
//    cout << endl << "bConstraintsSatisfied = " << problemFile.bConstraintsSatisfied(problemFile.getSolution(), error1) << endl;
//    cout << endl << "z = " << problemFile.dGetQuality(problemFile.getSolution(), error1) << endl;

    bool error = false;
    CMscnProblem *problem;
    problem = new CMscnProblem(2, 3, 2, 5);
    problem->vPrintHeader();
    cout << endl << "bConstraintsSatisfied = " << problem->bConstraintsSatisfied(problem->getSolution(), error) << endl;
    cout << endl << "z = " << problem->dGetQuality(problem->getSolution(), error) << endl;
    problem->bZapisWPlik();

    cout << "-------------Problem po optymizacji-----------------" << endl;
    CRandmSearch search(problem->iGetIloscD(), problem->iGetIloscF(), problem->iGetIloscM(), problem->iGetIloscS());
    search.getOptSolution(problem);
    cout << endl << "bConstraintsSatisfied = " << problem->bConstraintsSatisfied(problem->getSolution(), error) << endl;
    cout << endl << "z = " << problem->dGetQuality(problem->getSolution(), error) << endl;
    problem->bZapisWPlik();

    return 0;
}