#pragma once
#include <vector>
#include <iostream>

template <typename T>
class CNodeDynamic
{
public:
    CNodeDynamic() {};
    ~CNodeDynamic();
    void vSetValue(T iNewVal) { i_val = iNewVal; };
    int iGetChildrenNumber() { return static_cast<int>(v_children.size()); };
    void vAddNewChild() { v_children.push_back(new CNodeDynamic<T>()); };
    void vAddChild(CNodeDynamic<T>* newChild);
    CNodeDynamic<T>* pcGetChild(int iChildOffset);
    void vPrint() { std::cout << " " << i_val; };
    void vPrintAllBelow();
    T getVal() { return i_val; };
    void deleteChild(int i) { v_children.erase(v_children.begin() + i); };
private:
    std::vector<CNodeDynamic<T>*> v_children;
    T i_val;
};

