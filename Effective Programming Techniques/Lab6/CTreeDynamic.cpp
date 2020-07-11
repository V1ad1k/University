

#include "CTreeDynamic.h"

template <typename T>
bool CTreeDynamic<T>::bMoveSubtree(CNodeDynamic<T>* pcParentNode, CNodeDynamic<T>* pcNewChildNode, CNodeDynamic<T>* childParentNode) {
    pcParentNode->vAddChild(pcNewChildNode);
    for (int i = 0; i < childParentNode->iGetChildrenNumber(); i++) {
        if (childParentNode->pcGetChild(i)->getVal() == pcNewChildNode->getVal())
            childParentNode->deleteChild(i);
    }
    return true;
}

template<typename T>
int CTreeDynamic<T>::countTreeSize() {
    cSize = 0;
    return CTSHelper(c_root);
}

template<typename T>
int CTreeDynamic<T>::CTSHelper(CNodeDynamic<T>* Node) {
    cSize++;
    for (int i = 0; i < Node->iGetChildrenNumber(); i++)
        CTSHelper(Node->pcGetChild(i));
    return cSize;
}

template<typename T> int CTreeDynamic<T>::countTreeSum() {
     sum = 0;
    return CTSHelp(c_root);
}
template<typename T>
int CTreeDynamic<T>::CTSHelp(CNodeDynamic<T>* Node) {
    for(int i=0;i<Node->iGetChildrenNumber();i++){
        CTSHelp(sum+=pcGetChild(i));
    }
    return i_val+sum;
}