#pragma once
#include "CNodeStatic.h"

class CTreeStatic
{
public:
    CTreeStatic() {};
    ~CTreeStatic() {};
    CNodeStatic* pcGetRoot() { return(&c_root); }
    void vPrintTree() { c_root.vPrintAllBelow(); };
    bool bMoveSubtree(CNodeStatic* pcParentNode, CNodeStatic* pcNewChildNode, CNodeStatic* childParentNode);
private:
    CNodeStatic c_root;
};

