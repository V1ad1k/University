#include "CNodeDynamic.h"
#include <iostream>

template <typename T>
CNodeDynamic<T>::~CNodeDynamic() {
    for (int i = 0; i < v_children.size(); i++) {
        delete v_children.at(i);
    }
}

template <typename T>
void CNodeDynamic<T>::vAddChild(CNodeDynamic<T>* newChild) {
    v_children.push_back(newChild);
}

template <typename T>
CNodeDynamic<T>* CNodeDynamic<T>::pcGetChild(int iChildOffset) {
    if (iChildOffset >= iGetChildrenNumber() || iChildOffset < 0)
        return NULL;
    else
        return v_children.at(iChildOffset);
}

template <typename T>
void CNodeDynamic<T>::vPrintAllBelow() {
    for (int i = 0; i < v_children.size(); i++)
        (*v_children.at(i)).vPrint();

}
