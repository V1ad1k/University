#pragma once
#include <iostream>
#include <vector>

class CNodeStatic
{
// ponowne wybranie vSetValue(int) nie zmienia parent�w 
// w vector<int> parent 
// TODO
public:
    CNodeStatic() { i_val = 0; };
    ~CNodeStatic();
    void vSetValue(int iNewVal);
    int iGetChildrenNumber() { return static_cast<int>(v_children.size()); };
    void vAddNewChild();
    void vAddChild(CNodeStatic* newChild);
    CNodeStatic* pcGetChild(int iChildOffset) { return iChildOffset >= v_children.size() || iChildOffset < 0 ? NULL : &v_children.at(iChildOffset); };
    void vPrint() { std::cout << " " << i_val; };
    void vPrintAllBelow();
    void vPrintUp();
    int getVal() { return i_val; };
    void deleteChild(int i) { v_children.erase(v_children.begin()+i); };

protected:
    void setParent(std::vector<int> parentV) { parent = parentV; };

private:
    std::vector<int> parent;
    std::vector<CNodeStatic> v_children;
    int i_val;
};