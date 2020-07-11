#include "CTable.h"

CTable::CTable(int n_size, int m_size)
{
    n = n_size;
    m = m_size;
    pd_table = new double*[n];
    for (int i = 0; i < n; i++)
    {
        pd_table[i] = new double[m];
    }
}

CTable::CTable(int m_size)
{
    m = m_size;
    n = 1;
    pd_table = new double*[n];
    pd_table[0] = new double[m];
}

CTable::~CTable()
{
    for (int i = 0; i < n; i++)
    {
        delete[] pd_table[i];
    }
    delete[] pd_table;
};

void CTable::vShow()
{
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cout << setprecision(3) << pd_table[i][j] << "; ";
        }
        cout << endl;
    }
}

void CTable::vFill(int pZakres1, int pZakres2)
{
    CRandom random;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            pd_table[i][j] = random.getRandomDouble(pZakres1, pZakres2);
        }
    }
}

bool CTable::bSetNewSize(const bool pPos, const int new_size)
{
    if (new_size > 0)
    {
        int old_n = n;
        int old_m = m;
        (!pPos) ? n = new_size : m = new_size;
        double **new_pr = new double*[n];
        for (int i = 0; i < n; i++)
        {
            new_pr[i] = new double[m];
        }
        for (int i = 0; i < min(old_n, n); i++)
        {
            for (int j = 0; j < min(old_m, m); j++)
            {
                new_pr[i][j] = pd_table[i][j];
            }
        }

        if (old_n < n)
        {
            for (int i = old_n; i < n; i++)
            {
                for (int j = 0; j < m; j++)
                {
                    new_pr[i][j] = 0.0;
                }
            }
        }
        else if (old_m < m)
        {
            for (int i = 0; i < n; i++)
            {
                for (int j = old_m; j < m; j++)
                {
                    new_pr[i][j] = 0.0;
                }
            }
        }
        delete pd_table;
        pd_table = new_pr;
        return true;
    }
    else return false;
}

void CTable::vSetValueAt(const double pValue, const int pN, const int pM)
{
    if(pValue >=0 && pN >= 0 && pN < n && pM >= 0 && pM < m)
        pd_table[pN][pM] = pValue;
}

void CTable::vPrintTab(FILE * pFile)
{
    if (pFile != NULL)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                fprintf(pFile, "%g", getValueAt(i, j));
                fputs(" ", pFile);
            }
            fputs("\n", pFile);
        }
    }
}

CTable CTable::operator=(const CTable & cOther)
{
    if (pd_table != NULL) delete pd_table;
    pd_table = new double*[cOther.n];
    n = cOther.n;
    m = cOther.m;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            pd_table[i][j] = cOther.pd_table[i][j];
        }
    }
    return *this;
}
