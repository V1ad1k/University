#pragma once
#include <iostream>
#include <string>

template <typename T>
class CTable
{
public:
    CTable() { i_size = 0; pt_table = NULL; }
    ~CTable() { if (pt_table != NULL) delete[] pt_table; }
private:
    int i_size;
    T* pt_table;
};

