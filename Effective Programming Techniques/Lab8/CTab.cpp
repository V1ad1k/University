#include "CTab.h"
#include <iostream>

CTab::CTab(const CTab& cOther)
{
    v_copy(cOther);
    std::cout << "Copying \n";
}

CTab::CTab(CTab&& cOther)
{
    pi_tab = cOther.pi_tab;
    i_size = cOther.i_size;
    cOther.pi_tab = NULL;
    std::cout << " After Move  \n";

}

CTab CTab::operator=(const CTab& cOther)
{
    if (pi_tab != NULL) delete pi_tab;
    v_copy(cOther);
    std::cout << "operator = \n";
    return(*this);
}

CTab::~CTab()
{
    if (pi_tab != NULL) delete pi_tab;
    std::cout <<"remove tab with size = " << i_size << "\n";

}

bool CTab::bSetSize(int iNewSize) {
    delete pi_tab;
    pi_tab = new int[iNewSize];
    i_size = iNewSize;
    return true;
}

void CTab::v_copy(const CTab& cOther)
{
    pi_tab = new int[cOther.i_size];
    i_size = cOther.i_size;
    for (int j = 0; j < cOther.i_size; j++)
        pi_tab[j] = cOther.pi_tab[j];
}