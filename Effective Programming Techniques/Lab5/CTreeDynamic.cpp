#include "CTreeDynamic.h"

bool CTreeDynamic::bMoveSubtree(CNodeDynamic* pcParentNode, CNodeDynamic* pcNewChildNode, CNodeDynamic* childParentNode) {
    pcParentNode->vAddChild(pcNewChildNode);
    for (int i = 0; i < childParentNode->iGetChildrenNumber(); i++) {
        if (childParentNode->pcGetChild(i)->getVal() == pcNewChildNode->getVal())
            childParentNode->deleteChild(i);
    }
    return true;
}
