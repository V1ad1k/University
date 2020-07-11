//
// Created by Vlad on 11/21/2019.
//

#pragma once
#include <vector>
#include <iostream>
//#include "CTreeDynamic.h"

class CNodeDynamic
{
public:
    CNodeDynamic() { i_val = 0; };
    ~CNodeDynamic();
    void vSetValue(int iNewVal) { i_val = iNewVal; };
    int iGetChildrenNumber() { return static_cast<int>(v_children.size()); };
    void vAddNewChild() { v_children.push_back(new CNodeDynamic()); };
    void vAddChild(CNodeDynamic* newChild);
    CNodeDynamic* pcGetChild(int iChildOffset);
    void vPrint() { std::cout << " " << i_val; };
    void vPrintAllBelow();
    int getVal() { return i_val; };
    void deleteChild(int i) { v_children.erase(v_children.begin() + i); };
private:
    std::vector<CNodeDynamic*> v_children;
    int i_val;
};