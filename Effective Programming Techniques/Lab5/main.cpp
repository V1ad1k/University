#include "CNodeStatic.h"
#include "CTreeStatic.h"
#include "CNodeDynamic.h"
#include "CTreeDynamic.h"
#include <iostream>

using namespace std;

void dynamic_tree_moving() {
    CTreeDynamic tree1, tree2;
    (&tree1)->pcGetRoot()->vAddNewChild();
    (&tree1)->pcGetRoot()->vAddNewChild();
    (&tree1)->pcGetRoot()->vAddNewChild();
    (&tree1)->pcGetRoot()->pcGetChild(0)->vSetValue(1);
    (&tree1)->pcGetRoot()->pcGetChild(1)->vSetValue(2);
    (&tree1)->pcGetRoot()->pcGetChild(2)->vSetValue(3);
    (&tree1)->pcGetRoot()->pcGetChild(2)->vAddNewChild();
    (&tree1)->pcGetRoot()->pcGetChild(2)->pcGetChild(0)->vSetValue(4);
    cout << " Children of 1st tree is:\n";
    (&tree1)->pcGetRoot()->pcGetChild(2)->vPrintAllBelow(); cout << endl;
    (&tree2)->pcGetRoot()->vSetValue(10);
    (&tree2)->pcGetRoot()->vAddNewChild();
    (&tree2)->pcGetRoot()->vAddNewChild();
    (&tree2)->pcGetRoot()->pcGetChild(0)->vSetValue(14);
    (&tree2)->pcGetRoot()->pcGetChild(1)->vSetValue(15);
    (&tree2)->pcGetRoot()->pcGetChild(0)->vAddNewChild();
    (&tree2)->pcGetRoot()->pcGetChild(0)->vAddNewChild();
    (&tree2)->pcGetRoot()->pcGetChild(0)->pcGetChild(0)->vSetValue(16);
    (&tree2)->pcGetRoot()->pcGetChild(0)->pcGetChild(1)->vSetValue(17);
    (&tree2)->pcGetRoot()->pcGetChild(0)->pcGetChild(0)->vAddNewChild();
    (&tree2)->pcGetRoot()->pcGetChild(0)->pcGetChild(0)->pcGetChild(0)->vSetValue(18);
    cout << " Children of 2nd tree is:\n";
    (&tree2)->pcGetRoot()->vPrintAllBelow();
    (&tree1)->bMoveSubtree((&tree1)->pcGetRoot()->pcGetChild(2), (&tree2)->pcGetRoot()->pcGetChild(0), (&tree2)->pcGetRoot());
    cout << "\n Move trees :\n";
    cout << " Children of 1st tree is:\n";
    (&tree1)->pcGetRoot()->pcGetChild(2)->vPrintAllBelow();
    cout << "\n Children of 2nd tree is:\n";
    (&tree2)->pcGetRoot()->vPrintAllBelow(); cout << endl;
}
void static_tree_moving() {
    CTreeStatic tree1, tree2;
    tree1.pcGetRoot()->vAddNewChild();
    tree1.pcGetRoot()->vAddNewChild();
    tree1.pcGetRoot()->vAddNewChild();
    tree1.pcGetRoot()->pcGetChild(0)->vSetValue(1);
    tree1.pcGetRoot()->pcGetChild(1)->vSetValue(2);
    tree1.pcGetRoot()->pcGetChild(2)->vSetValue(3);
    tree1.pcGetRoot()->pcGetChild(2)->vAddNewChild();
    tree1.pcGetRoot()->pcGetChild(2)->pcGetChild(0)->vSetValue(4);
    cout << " Children of 1st tree is:\n";
    tree1.pcGetRoot()->pcGetChild(2)->vPrintAllBelow(); cout << endl;
    tree2.pcGetRoot()->vSetValue(10);
    tree2.pcGetRoot()->vAddNewChild();
    tree2.pcGetRoot()->vAddNewChild();
    tree2.pcGetRoot()->pcGetChild(0)->vSetValue(14);
    tree2.pcGetRoot()->pcGetChild(1)->vSetValue(15);
    tree2.pcGetRoot()->pcGetChild(0)->vAddNewChild();
    tree2.pcGetRoot()->pcGetChild(0)->vAddNewChild();
    tree2.pcGetRoot()->pcGetChild(0)->pcGetChild(0)->vSetValue(16);
    tree2.pcGetRoot()->pcGetChild(0)->pcGetChild(1)->vSetValue(17);
    tree2.pcGetRoot()->pcGetChild(0)->pcGetChild(0)->vAddNewChild();
    tree2.pcGetRoot()->pcGetChild(0)->pcGetChild(0)->pcGetChild(0)->vSetValue(8);
    cout << " Children of 2nd tree is:\n";
    tree2.pcGetRoot()->vPrintAllBelow();
    tree1.bMoveSubtree(tree1.pcGetRoot()->pcGetChild(2), tree2.pcGetRoot()->pcGetChild(0), tree2.pcGetRoot());
    cout << "\n Move trees\n";
    cout << " Children of 1st tree is:\n";
    tree1.pcGetRoot()->pcGetChild(2)->vPrintAllBelow();
    cout << "\n Children of 2nd tree is:\n";
    tree2.pcGetRoot()->vPrintAllBelow(); cout << endl;
}
void static_tree_moving();
void dynamic_tree_moving();

int main()
{
    cout << "Dynamic :\n";
    dynamic_tree_moving(); cout << endl;

    cout << "Static : \n";
    static_tree_moving(); cout << endl;

}
