#include "CTreeStatic.h"

bool CTreeStatic::bMoveSubtree(CNodeStatic* pcParentNode, CNodeStatic* pcNewChildNode, CNodeStatic* childParentNode) {
    pcParentNode->vAddChild(pcNewChildNode);
    for (int i = 0; i < childParentNode->iGetChildrenNumber(); i++) {
        if (childParentNode->pcGetChild(i)->getVal() == pcNewChildNode->getVal())
            childParentNode->deleteChild(i);
    }
    return true;
}