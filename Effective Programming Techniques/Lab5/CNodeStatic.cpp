#include "CNodeStatic.h"
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

CNodeStatic::~CNodeStatic() {
    for (int i = 0; i < v_children.size(); i++)
        v_children.pop_back();
}

void CNodeStatic::vSetValue(int iNewVal) {
    i_val = iNewVal;
}

void CNodeStatic::vAddNewChild() {
    v_children.push_back(CNodeStatic());
    if(v_children.size() < 2)
        parent.push_back(i_val);
    v_children.at(v_children.size() - 1).setParent(parent);
}

void CNodeStatic::vAddChild(CNodeStatic* newChild) {
    v_children.push_back(*newChild);
    if (v_children.size() < 2)
        parent.push_back(i_val);
    v_children.at(v_children.size() - 1).setParent(parent);
}

void CNodeStatic::vPrintAllBelow() {
    for (int i = 0; i < v_children.size(); i++)
        v_children.at(i).vPrint();
}

void CNodeStatic::vPrintUp() {
    parent.push_back(i_val);
    for (int i = static_cast<int>(parent.size() - 1); i >= 0; i--)
        cout << " " << parent.at(i);
}