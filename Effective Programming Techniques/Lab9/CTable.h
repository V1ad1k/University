#ifndef CTable_H
#define CTable_H
#include <iostream>
#include <string>
#include <algorithm>
#include <iomanip>
#include "CRandom.h"

using namespace std;

class CTable
{
private:
    int n, m;
    double** pd_table;
public:
    CTable() { n = m = 1; pd_table = new double*[n]; for (int i = 0; i < 1; i++) { pd_table[i] = new double[m]; }; };
    CTable(const int n_size, const int m_size);
    CTable(const int m_size);
    ~CTable();
    void vShow();
    void vFill(const int pZakres1, const int pZakres2);
    bool bSetNewSize(const bool pPos, const int new_size);
    int getN() { return n; }
    int getM() { return m; }
    int getSize() { return n * m; }
    double getValueAt(const int pN, const int pM) { return pd_table[pN][pM]; }
    void vSetValueAt(const double pValue, const int pN, const int pM);
    void vPrintTab(FILE *pFile);
    CTable operator=(const CTable &cOther);
};

#endif