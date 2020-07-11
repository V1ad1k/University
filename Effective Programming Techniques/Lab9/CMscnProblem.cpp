#include "CMscnProblem.h"
#include <iostream>
#include <string>
CMscnProblem::CMscnProblem()
{
    srand(time(NULL));
    z = 0;
    iloscDostawcow = ILOSC_DOSTAWCOW;
    iloscFabryk = ILOSC_FABRYK;
    iloscMagazynow = ILOSC_MAGAZYNOW;
    iloscSklepow = ILOSC_SKLEPOW;
    vGenerateInstance();
}

CMscnProblem::CMscnProblem(const int pDost, const int pFabr, const int pMagaz, const int pSklep)
{
    srand(time(NULL));
    z = 0;
    iloscDostawcow = pDost;
    iloscFabryk = pFabr;
    iloscMagazynow = pMagaz;
    iloscSklepow = pSklep;
    vGenerateInstance();
}

CMscnProblem::~CMscnProblem()
{
    if (cd != NULL) delete cd;
    if (cf != NULL) delete cf;
    if (cm != NULL) delete cm;
    if (sd != NULL) delete sd;
    if (sf != NULL) delete sf;
    if (sm != NULL) delete sm;
    if (ss != NULL) delete ss;
    if (ud != NULL) delete ud;
    if (uf != NULL) delete uf;
    if (um != NULL) delete um;
    if (ps != NULL) delete ps;
    if (xd != NULL) delete xd;
    if (xf != NULL) delete xf;
    if (xm != NULL) delete xm;
    if (xdminmax != NULL) delete xdminmax;
    if (xfminmax != NULL) delete xfminmax;
    if (xmminmax != NULL) delete xmminmax;
    if (solution != NULL) delete solution;
}

void CMscnProblem::vRefill()
{
    xdminmax->vFill(8, 10);
    sd->vFill(100, 200);
    xd->vFill(8, 10);
    ud->vFill(0, 30);
    cd->vFill(0, 5);

    xfminmax->vFill(4, 5);
    sf->vFill(100, 180);
    xf->vFill(5, 6);
    uf->vFill(0, 30);
    cf->vFill(0, 2);

    xmminmax->vFill(1, 2);
    sm->vFill(100, 190);
    xm->vFill(1, 2);
    um->vFill(0, 30);
    cm->vFill(0, 2);

    ss->vFill(100, 220);
    ps->vFill(100, 210);

    vCreateSolution();
}

void CMscnProblem::vGenerateInstance()
{
    xdminmax = new CTable(2 * iloscDostawcow * iloscFabryk);
    xdminmax->vFill(8, 10);
    sd = new CTable(iloscDostawcow);
    sd->vFill(100, 200);
    xd = new CTable(iloscDostawcow, iloscFabryk);
    xd->vFill(8, 10);
    ud = new CTable(iloscDostawcow);
    ud->vFill(0, 30);
    cd = new CTable(iloscDostawcow, iloscFabryk);
    cd->vFill(0, 5);

    xfminmax = new CTable(2 * iloscFabryk * iloscMagazynow);
    xfminmax->vFill(4, 5);
    sf = new CTable(iloscFabryk);
    sf->vFill(100, 180);
    xf = new CTable(iloscFabryk, iloscMagazynow);
    xf->vFill(5, 6);
    uf = new CTable(iloscFabryk);
    uf->vFill(0, 30);
    cf = new CTable(iloscFabryk, iloscMagazynow);
    cf->vFill(0, 2);

    xmminmax = new CTable(2 * iloscMagazynow * iloscSklepow);
    xmminmax->vFill(1, 2);
    sm = new CTable(iloscMagazynow);
    sm->vFill(100, 190);
    xm = new CTable(iloscMagazynow, iloscSklepow);
    xm->vFill(1, 2);
    um = new CTable(iloscMagazynow);
    um->vFill(0, 30);
    cm = new CTable(iloscMagazynow, iloscSklepow);
    cm->vFill(0, 2);

    ss = new CTable(iloscSklepow);
    ss->vFill(100, 220);
    ps = new CTable(iloscSklepow);
    ps->vFill(100, 210);

    vCreateSolution();
}

bool CMscnProblem::bSetIloscDostawcow(const int pDost)
{
    if (pDost > 0 && iloscDostawcow != pDost)
    {
        iloscDostawcow = pDost;
        cd->bSetNewSize(0, iloscDostawcow);
        sd->bSetNewSize(1, iloscDostawcow);
        vCreateSolution();
        return true;
    }
    else return false;

}

bool CMscnProblem::bSetIloscFabryk(const int pFabr)
{
    if (pFabr > 0)
    {
        iloscFabryk = pFabr;
        cd->bSetNewSize(1, iloscFabryk);
        cf->bSetNewSize(0, iloscFabryk);
        sf->bSetNewSize(1, iloscFabryk);
        vCreateSolution();
        return true;
    }
    else return false;
}

bool CMscnProblem::bSetIloscMagazynow(const int pMagaz)
{
    if (pMagaz > 0)
    {
        iloscMagazynow = pMagaz;
        cf->bSetNewSize(1, iloscMagazynow);
        cm->bSetNewSize(0, iloscMagazynow);
        sm->bSetNewSize(1, iloscMagazynow);
        vCreateSolution();
        return true;
    }
    else return false;
}

bool CMscnProblem::bSetIloscSklepow(const int pSklep)
{
    if (pSklep > 0)
    {
        iloscSklepow = pSklep;
        cm->bSetNewSize(1, iloscSklepow);
        ss->bSetNewSize(1, iloscSklepow);
        vCreateSolution();
        return true;
    }
    else return false;
}

bool CMscnProblem::bSetValueToMatrix(CTable *pTab, const double pValue, const int pN, const int pM)
{
    if (pTab != NULL && pValue >= 0 && pN >= 0 && pN < pTab->getN() && pM >= 0 && pN < pTab->getM())
    {
        pTab->vSetValueAt(pValue, pN, pM);
        return true;
    }
    else return false;
}

void CMscnProblem::vPrintHeader()
{
    cout << "Ilosc dostawcow  = " << iloscDostawcow << " | Ilosc fabryk = " << iloscFabryk << " | Ilosc magazynow = " << iloscMagazynow << " | Ilosc sklepow = " << iloscSklepow << endl;
    cout << "\nMacierz cd <koszt wytworzenia i przewiezienia surowca od dostawcy d do fabryki f>" << endl;
    cd->vShow();
    cout << "\nTablica sd <mocy produkcyjnych>" << endl;
    sd->vShow();
    cout << "\nTablica ud <jednorazowy koszt korzystania z uslug dostawcy>" << endl;
    ud->vShow();
    cout << "\nMacierz cf <koszt wyprodukowania produktu w fabryce f i przewiezienia go do magazynu m>" << endl;
    cf->vShow();
    cout << "\nTablica sf <mocy produkcyjnych>" << endl;
    sf->vShow();
    cout << "\nTablica uf <jednorazowy koszt korzystania z fabryki>" << endl;
    uf->vShow();
    cout << "\nMacierz cm <koszt przechowania produktu w magazynie m i przewiezienia go do sklepu s>" << endl;
    cm->vShow();
    cout << "\nTablica sm <pojemnosci>" << endl;
    sm->vShow();
    cout << "\nTablica um <jednorazowy koszt korzystania z magazynu>" << endl;
    um->vShow();
    cout << "\nTablica ss <zapotrzebowania rynkowego>" << endl;
    ss->vShow();
    cout << "\nTablica ps <przedanie produktu w sklepie>" << endl;
    ps->vShow();
}

void CMscnProblem::vCreateSolution()
{
    i_size = 0;
    if (solution != NULL)
    {
        delete solution;
    }
    s_size = xdminmax->getSize() + sd->getSize() + ud->getSize() + cd->getSize() + xfminmax->getSize() + sf->getSize() + uf->getSize() + cf->getSize() +
             xmminmax->getSize() + sm->getSize() + um->getSize() + cm->getSize() + ss->getSize() + ps->getSize() + xd->getSize()+ xf->getSize()+ xm->getSize();

    solution = new double[s_size];
    vAddTab(cd);
    vAddTab(xd);
    vAddTab(ud);
    vAddTab(sd);
    vAddTab(xdminmax);

    vAddTab(cf);
    vAddTab(xf);
    vAddTab(uf);
    vAddTab(sf);
    vAddTab(xfminmax);

    vAddTab(cm);
    vAddTab(xm);
    vAddTab(um);
    vAddTab(sm);
    vAddTab(xmminmax);

    vAddTab(ss);
    vAddTab(ps);
}

string CMscnProblem::getTableName(int pIndex)
{
    switch (pIndex)
    {
        default:
            return "cd";
        case 1:
            return "xd";
        case 2:
            return "ud";
        case 3:
            return "sd";
        case 4:
            return "xdminmax";
        case 5:
            return "cf";
        case 6:
            return "xf";
        case 7:
            return "uf";
        case 8:
            return "sf";
        case 9:
            return "xfminmax";
        case 10:
            return "cm";
        case 11:
            return "xm";
        case 12:
            return "um";
        case 13:
            return "sm";
        case 14:
            return "xmminmax";
        case 15:
            return "ss";
        case 16:
            return "ps";
    }
}
int CMscnProblem::getTableSize(int pIndex)
{
    switch (pIndex)
    {
        default:
            return iloscDostawcow * iloscFabryk;
        case 1:
            return iloscDostawcow * iloscFabryk;
        case 2:
            return iloscDostawcow;
        case 3:
            return iloscDostawcow;
        case 4:
            return 2 * iloscDostawcow * iloscFabryk;
        case 5:
            return iloscFabryk * iloscMagazynow;
        case 6:
            return iloscFabryk * iloscMagazynow;
        case 7:
            return iloscFabryk;
        case 8:
            return iloscFabryk;
        case 9:
            return 2 * iloscFabryk * iloscMagazynow;
        case 10:
            return iloscMagazynow * iloscSklepow;
        case 11:
            return iloscMagazynow * iloscSklepow;
        case 12:
            return iloscMagazynow;
        case 13:
            return iloscMagazynow;
        case 14:
            return 2 * iloscMagazynow * iloscSklepow;
        case 15:
            return iloscSklepow;
        case 16:
            return iloscSklepow;
    }
}

void CMscnProblem::vAddTab(CTable *pTab)
{
    for (int i = 0; i < pTab->getN(); i++)
    {
        for (int j = 0; j < pTab->getM(); j++)
        {
            solution[i_size] = pTab->getValueAt(i, j);
            i_size++;
        }
    }
}

bool CMscnProblem::bCheckToOdd(double *pdSolution)
{
    for (int i = 0; i < s_size; i++)
    {
        if (pdSolution[i] < 0) return true;
    }
    return false;
}

double CMscnProblem::oblStep(int pMode, int pS, int pK, double *pdSolution)
{
    double val = 0.0;
    if (pMode == 0)
    {
        for (int i = pS; i < pK; i++)
        {
            val += pdSolution[i] * pdSolution[i + pK - pS];
        }
    }
    else if (pMode == 1)
    {
        int counter = 0;
        int counterUd = pK;
        double sums = 0.0;
        bool wykorzyst = true;
        for (int i = pS; i < pK; i++)
        {
            counter++;
            sums += pdSolution[i];
            if (pdSolution[i] <= 0) wykorzyst = false;
            if (counter == iloscFabryk)
            {
                counter = 0;
                (wykorzyst) ? sums = sums : sums = 0.0;
                sums *= pdSolution[counterUd];
                counterUd++;
                val += sums;
                sums = 0.0;
                wykorzyst = true;
            }
        }
    }
    else
    {
        int j = s_size - iloscSklepow;
        for (int i = pS; i < pK; i++)
        {
            if (j == s_size)
            {
                j = s_size - iloscSklepow;
            }
            val += pdSolution[i] * pdSolution[j];
            ++j;
        }
    }
    return val;
}

bool CMscnProblem::checkWarunek(int pMode, int &pSizeParam, int pS, int pK, double *pdSolution)
{
    double sum = 0.0;
    int counter = 0;
    if (pMode == 0)
    {
        int counterJ;
        if (&pSizeParam == &iloscMagazynow)
        {
            counterJ = pK + iloscFabryk;
        }
        else if(&pSizeParam == &iloscSklepow)
        {
            counterJ = pK + iloscMagazynow;
        }
        else
        {
            counterJ = pK + iloscDostawcow;
        }
        for (int i = pS; i < pK; i++)
        {
            counter++;
            sum += pdSolution[i];
            if (counter == pSizeParam)
            {
                counter = 0;
                if (sum > pdSolution[counterJ]) return false;
                sum = 0.0;
                counterJ++;
            }
        }
    }
    else
    {
        for (int j = 0; j < pSizeParam; j++)
        {
            for (int i = pS + j; i < pK; i = iloscSklepow + i)
            {
                counter++;
                sum += pdSolution[i];
                if (counter == pSizeParam)
                {
                    counter = 0;
                    if (sum > pdSolution[s_size - iloscSklepow * 2 + j]) return false;
                    sum = 0.0;
                }
            }
        }
    }
    return true;
}

bool CMscnProblem::checkWarunekForMatrix(int pSizeParam, int pSizeParam1, int pSizeParam2, int pS, int pK, int pS1, int pK1, double *pdSolution)
{
    int counter = 0;
    vector<double> vector1;
    vector<double> vector2;
    double sum = 0.0;
    for (int j = 0; j < pSizeParam; j++)
    {
        for (int i = pS + j; i < pK; i = pSizeParam1 + i)
        {
            counter++;
            sum += pdSolution[i];
            if (counter == pSizeParam)
            {
                counter = 0;
                vector1.push_back(sum);
                sum = 0.0;
            }
        }
    }
    for (int i = pS1; i < pK1; i++)
    {
        counter++;
        sum += pdSolution[i];
        if (counter == pSizeParam2)
        {
            counter = 0;
            vector2.push_back(sum);
            sum = 0.0;
        }
    }
    for (size_t i = 0; i < min(vector1.size(), vector2.size()); i++)
    {
        if (vector1.at(i) < vector2.at(i)) return false;;
    }
    return true;
}

double CMscnProblem::dGetQuality(double *pdSolution, bool &error)
{
    if (pdSolution == NULL || i_size != s_size || bCheckToOdd(pdSolution))
    {
        error = true;
        return -1;
    }
    bConstraintsSatisfied(pdSolution, error);

    double Kt = oblStep(0, 0, iloscDostawcow * iloscFabryk, pdSolution) + oblStep(0, iloscDostawcow * iloscFabryk * 4 + iloscDostawcow * 2, iloscDostawcow * iloscFabryk * 4
                                                                                                                                            + iloscDostawcow * 2 + iloscFabryk * iloscMagazynow, pdSolution) + oblStep(0, iloscDostawcow * iloscFabryk * 4 + iloscDostawcow * 2 + iloscFabryk * iloscMagazynow * 4 +
                                                                                                                                                                                                                          iloscFabryk * 2, iloscDostawcow * iloscFabryk * 4 + iloscDostawcow * 2 + iloscFabryk * iloscMagazynow * 4 + iloscFabryk * 2 + iloscMagazynow * iloscSklepow, pdSolution);

    double Ku = oblStep(1, iloscDostawcow * iloscFabryk, 2 * iloscDostawcow * iloscFabryk, pdSolution) + oblStep(1, iloscDostawcow * iloscFabryk * 4 + iloscDostawcow * 2 +
                                                                                                                    iloscFabryk * iloscMagazynow, iloscDostawcow * iloscFabryk * 4 + iloscDostawcow * 2 + 2 * iloscFabryk * iloscMagazynow, pdSolution) + oblStep(1, iloscDostawcow * iloscFabryk * 4
                                                                                                                                                                                                                                                                     + iloscDostawcow * 2 + iloscFabryk * iloscMagazynow * 4 + iloscFabryk * 2 + iloscMagazynow * iloscSklepow, iloscDostawcow * iloscFabryk * 4 + iloscDostawcow * 2
                                                                                                                                                                                                                                                                                                                                                                                + iloscFabryk * iloscMagazynow * 4 + iloscFabryk * 2 + iloscMagazynow * iloscSklepow * 2, pdSolution);

    double P = oblStep(2, iloscDostawcow * iloscFabryk * 4 + iloscDostawcow * 2 + iloscFabryk * iloscMagazynow * 4 + iloscFabryk * 2 + iloscMagazynow * iloscSklepow,
                       iloscDostawcow * iloscFabryk * 4 + iloscDostawcow * 2 + iloscFabryk * iloscMagazynow * 4 + iloscFabryk * 2 + 2 * (iloscMagazynow * iloscSklepow), pdSolution);

    z = P - Kt - Ku;
    return z;
}

bool CMscnProblem::bConstraintsSatisfied(double *pdSolution, bool &error)
{
    if (pdSolution == NULL || i_size != s_size || bCheckToOdd(pdSolution))
    {
        error = true;
        return false;
    }
    if (!checkWarunek(0, iloscFabryk, iloscDostawcow * iloscFabryk, 2 * iloscDostawcow * iloscFabryk, pdSolution)) return false;
    if (!checkWarunek(0, iloscMagazynow, 4 * iloscDostawcow * iloscFabryk + 2 * iloscDostawcow + iloscFabryk * iloscMagazynow,
                      4 * iloscDostawcow * iloscFabryk + 2 * iloscDostawcow + iloscFabryk * iloscMagazynow * 2, pdSolution)) return false;
    if (!checkWarunek(0, iloscSklepow, 4 * iloscDostawcow * iloscFabryk + 2 * iloscDostawcow + iloscFabryk * iloscMagazynow * 4 + iloscFabryk * 2 + iloscMagazynow * iloscSklepow,
                      4 * iloscDostawcow * iloscFabryk + 2 * iloscDostawcow + iloscFabryk * iloscMagazynow * 4 + iloscFabryk * 2 + iloscMagazynow * iloscSklepow  * 2, pdSolution)) return false;
    if (!checkWarunek(1, iloscMagazynow, 4 * iloscDostawcow * iloscFabryk + 2 * iloscDostawcow + iloscFabryk * iloscMagazynow * 4 + iloscFabryk * 2 + iloscMagazynow * iloscSklepow,
                      4 * iloscDostawcow * iloscFabryk + 2 * iloscDostawcow + iloscFabryk * iloscMagazynow * 4 + iloscFabryk * 2 + iloscMagazynow * iloscSklepow  * 2, pdSolution)) return false;
    if (!checkWarunekForMatrix(iloscDostawcow, iloscFabryk, iloscMagazynow, iloscDostawcow * iloscFabryk, 2 * iloscDostawcow * iloscFabryk, 4 * iloscDostawcow * iloscFabryk + 2 * iloscDostawcow
                                                                                                                                            + iloscFabryk * iloscMagazynow, 4 * iloscDostawcow * iloscFabryk + 2 * iloscDostawcow + iloscFabryk * iloscMagazynow * 2, pdSolution)) return false;
    if (!checkWarunekForMatrix(iloscFabryk, iloscMagazynow, iloscSklepow, 4 * iloscDostawcow * iloscFabryk + 2 * iloscDostawcow+ iloscFabryk * iloscMagazynow, 4 * iloscDostawcow * iloscFabryk + 2 * iloscDostawcow
                                                                                                                                                               + iloscFabryk * iloscMagazynow * 2, 4 * iloscDostawcow * iloscFabryk + 2 * iloscDostawcow + iloscFabryk * iloscMagazynow * 4 + iloscFabryk * 2 + iloscMagazynow * iloscSklepow, 4 * iloscDostawcow * iloscFabryk
                                                                                                                                                                                                                                                                                                                                               + 2 * iloscDostawcow + iloscFabryk * iloscMagazynow * 4 + iloscFabryk * 2 + iloscMagazynow * iloscSklepow * 2, pdSolution)) return false;
    return true;
}

void CMscnProblem::vSprawdzenieMinMax(double *pdSolution)
{
    cout << endl;
    int counter = 0;
    double min = pdSolution[counter];
    double max = pdSolution[counter];
    for (int i = 0; i < 17; i++)
    {
        min = pdSolution[counter];
        max = pdSolution[counter];

        for (int j = counter; j < counter + getTableSize(i); j++)
        {
            if (min > pdSolution[j]) min = pdSolution[j];
            if (max < pdSolution[j]) max = pdSolution[j];
        }
        counter += getTableSize(i);
        cout << getTableName(i) << " - " << "<min = " << min << " | max = " << max << ">" << endl;
    }
}

bool CMscnProblem::bZapisWPlik()
{
    FILE *pFile;
    pFile = fopen("solution.txt", "w");

    if (pFile != NULL)
    {
        fputs("D ", pFile);
        fprintf(pFile, "%d", iloscDostawcow);
        fputs("\nF ", pFile);
        fprintf(pFile, "%d", iloscFabryk);
        fputs("\nM ", pFile);
        fprintf(pFile, "%d", iloscMagazynow);
        fputs("\nS ", pFile);
        fprintf(pFile, "%d", iloscSklepow);

        fputs("\n\nxd\n", pFile);
        xd->vPrintTab(pFile);
        fputs("\nxf\n", pFile);
        xf->vPrintTab(pFile);
        fputs("\nxm\n", pFile);
        xm->vPrintTab(pFile);

        fputs("\nZ = ", pFile);
        fprintf(pFile, "%g", z);
        fclose(pFile);
    }
    return true;
}

bool CMscnProblem::bOdczytZPlik(string pPath)
{
    FILE *file = fopen(pPath.c_str(), "r");
    if (file == NULL) return false;

    double number = 0;
    int len = 4;
    vector<int> sizes;
    for (int i = 0; i < len; i++)
    {
        fscanf(file, " %lf", &number);
        sizes.push_back(number);
    }
    iloscDostawcow = sizes.at(0);
    iloscFabryk = sizes.at(1);
    iloscMagazynow = sizes.at(2);
    iloscSklepow = sizes.at(3);


    if (solution != NULL)
    {
        delete solution;
    }

    s_size = 4 * sizes.at(0) * sizes.at(1) + 2 * sizes.at(0) + 4 * sizes.at(1) * sizes.at(2) + 2 * sizes.at(1) + 4 * sizes.at(2) * sizes.at(3) + 2 * sizes.at(2)
             + 2 * sizes.at(3);

    solution = new double[s_size];
    cout << endl;
    for (i_size = 0; i_size < s_size; i_size++)
    {
        fscanf(file, " %lf", &number);
        solution[i_size] = number;
        cout << solution[i_size] << " ";
    }
    fclose(file);
    return true;
}

void CMscnProblem::vCopy(CMscnProblem *pProblem)
{
    xdminmax = pProblem->xdminmax;
    sd = pProblem->sd;
    xd = pProblem->xd;
    ud = pProblem->ud;
    cd = pProblem->cd;

    xfminmax = pProblem->xfminmax;
    sf = pProblem->sf;
    xf = pProblem->xf;
    uf = pProblem->uf;
    cf = pProblem->cf;

    xmminmax = pProblem->xmminmax;
    sm = pProblem->sm;
    xm = pProblem->xm;
    um = pProblem->um;
    cm = pProblem->cm;

    ss = pProblem->ss;
    ps = pProblem->ps;

    vCreateSolution();
}
