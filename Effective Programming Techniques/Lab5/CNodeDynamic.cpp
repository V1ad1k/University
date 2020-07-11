#include "CNodeDynamic.h"
#include <iostream>

CNodeDynamic::~CNodeDynamic() {
    for (int i = 0; i < v_children.size(); i++) {
        delete v_children.at(i);
    }
}

void CNodeDynamic::vAddChild(CNodeDynamic* newChild) {
    v_children.push_back(newChild);
}

CNodeDynamic* CNodeDynamic::pcGetChild(int iChildOffset) {
    if (iChildOffset >= iGetChildrenNumber() || iChildOffset < 0)
        return NULL;
    else
        return v_children.at(iChildOffset);
}

void CNodeDynamic::vPrintAllBelow() {
    for (int i = 0; i < v_children.size(); i++)
        (*v_children.at(i)).vPrint();

}