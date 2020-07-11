#pragma once
#include "CNodeDynamic.h"

class CTreeDynamic
{
public:
    CTreeDynamic() { c_root = new CNodeDynamic(); };
    ~CTreeDynamic() {};
    CNodeDynamic *pcGetRoot() { return c_root; };
    void vPrintTree() { (*c_root).vPrintAllBelow(); };
    bool bMoveSubtree(CNodeDynamic* pcParentNode, CNodeDynamic* pcNewChildNode, CNodeDynamic* childParentNode);
private:
    CNodeDynamic* c_root;
};



