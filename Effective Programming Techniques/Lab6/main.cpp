#include "CNodeDynamic.h"
#include "CTreeDynamic.h"
#include "CNodeDynamic.cpp"
#include "CTreeDynamic.cpp"
#include <iostream>
#include <string>

using namespace std;

void dynamic_tree_moving_string() {
    CTreeDynamic<string> tree1, tree2;

    tree1.pcGetRoot()->vAddNewChild();
    tree1.pcGetRoot()->vAddNewChild();
    tree1.pcGetRoot()->vAddNewChild();
    tree1.pcGetRoot()->pcGetChild(0)->vSetValue("a");
    tree1.pcGetRoot()->pcGetChild(1)->vSetVazlue("b");
    tree1.pcGetRoot()->pcGetChild(2)->vSetValue("c");
    tree1.pcGetRoot()->pcGetChild(2)->vAddNewChild();
    tree1.pcGetRoot()->pcGetChild(2)->pcGetChild(0)->vSetValue("kk");
    cout << " Children of 1st tree  =\n";
    tree1.pcGetRoot()->pcGetChild(2)->vPrintAllBelow(); cout << endl;
    tree2.pcGetRoot()->vSetValue("d");
    tree2.pcGetRoot()->vAddNewChild();
    tree2.pcGetRoot()->vAddNewChild();
    tree2.pcGetRoot()->pcGetChild(0)->vSetValue("qq");
    tree2.pcGetRoot()->pcGetChild(1)->vSetValue("hh");
    tree2.pcGetRoot()->pcGetChild(0)->vAddNewChild();
    tree2.pcGetRoot()->pcGetChild(0)->vAddNewChild();
    tree2.pcGetRoot()->pcGetChild(0)->pcGetChild(0)->vSetValue("k");
    tree2.pcGetRoot()->pcGetChild(0)->pcGetChild(1)->vSetValue("l");
    tree2.pcGetRoot()->pcGetChild(0)->pcGetChild(0)->vAddNewChild();
    tree2.pcGetRoot()->pcGetChild(0)->pcGetChild(0)->pcGetChild(0)->vSetValue("m");
    cout << "\n Children of 2nd tree  =\n";
    tree2.pcGetRoot()->vPrintAllBelow();
    std::cout << "\n\nTree1 has size before move = " << tree1.countTreeSize() << std::endl;
    std::cout << "Tree2 has size before move = " << tree2.countTreeSize() << std::endl;
    tree1.bMoveSubtree((&tree1)->pcGetRoot()->pcGetChild(2), (&tree2)->pcGetRoot()->pcGetChild(0), (&tree2)->pcGetRoot());
    cout << "\n\n Move trees\n";
    cout << "\n Children of 1st tree = \n";
    tree1.pcGetRoot()->pcGetChild(2)->vPrintAllBelow();
    cout << "\n\n Children of 2nd tree = \n";
    tree2.pcGetRoot()->vPrintAllBelow(); cout << endl;
    std::cout << "\nTree1 has size after move = " <<tree1.countTreeSize() << std::endl;
    std::cout << "Tree1 has size after move = "<< tree2.countTreeSize() << std::endl;
}
void dynamic_tree_moving_double() {
    CTreeDynamic<double> tree1, tree2;

    tree1.pcGetRoot()->vAddNewChild();
    tree1.pcGetRoot()->vAddNewChild();
    tree1.pcGetRoot()->vAddNewChild();
    tree1.pcGetRoot()->pcGetChild(0)->vSetValue(1.1);
    tree1.pcGetRoot()->pcGetChild(1)->vSetValue(2.4);
    tree1.pcGetRoot()->pcGetChild(2)->vSetValue(1.12);
    tree1.pcGetRoot()->pcGetChild(2)->vAddNewChild();
    tree1.pcGetRoot()->pcGetChild(2)->pcGetChild(0)->vSetValue(1.1);
    cout << "Children of 1st tree =\n";
    tree1.pcGetRoot()->pcGetChild(2)->vPrintAllBelow(); cout << endl;
    tree2.pcGetRoot()->vSetValue(0.4);
    tree2.pcGetRoot()->vAddNewChild();
    tree2.pcGetRoot()->vAddNewChild();
    tree2.pcGetRoot()->pcGetChild(0)->vSetValue(22.2);
    tree2.pcGetRoot()->pcGetChild(1)->vSetValue(333.33);
    tree2.pcGetRoot()->pcGetChild(0)->vAddNewChild();
    tree2.pcGetRoot()->pcGetChild(0)->vAddNewChild();
    tree2.pcGetRoot()->pcGetChild(0)->pcGetChild(0)->vSetValue(0.54);
    tree2.pcGetRoot()->pcGetChild(0)->pcGetChild(1)->vSetValue(21.43);
    tree2.pcGetRoot()->pcGetChild(0)->pcGetChild(0)->vAddNewChild();
    tree2.pcGetRoot()->pcGetChild(0)->pcGetChild(0)->pcGetChild(0)->vSetValue(9.54);
    cout << "\nChildren of 2nd tree =\n";
    tree2.pcGetRoot()->vPrintAllBelow();
    tree1.bMoveSubtree((&tree1)->pcGetRoot()->pcGetChild(2), (&tree2)->pcGetRoot()->pcGetChild(0), (&tree2)->pcGetRoot());
    cout << "\n\n Move trees\n";
    cout << "\n Children of 1st tree \n";
    tree1.pcGetRoot()->pcGetChild(2)->vPrintAllBelow();
    cout << "\n\n Children of 2nd tree =\n";
    tree2.pcGetRoot()->vPrintAllBelow(); cout << endl;
}

void dynamic_tree_moving_int() {
    CTreeDynamic<int> tree1, tree2;
    tree1.pcGetRoot()->vAddNewChild();
    tree1.pcGetRoot()->vAddNewChild();
    tree1.pcGetRoot()->vAddNewChild();
    tree1.pcGetRoot()->pcGetChild(0)->vSetValue(1);
    tree1.pcGetRoot()->pcGetChild(1)->vSetValue(2);
    tree1.pcGetRoot()->pcGetChild(2)->vSetValue(3);
    tree1.pcGetRoot()->pcGetChild(2)->vAddNewChild();
    tree1.pcGetRoot()->pcGetChild(2)->pcGetChild(0)->vSetValue(4);
    cout << "Children of 1st tree \n";
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
    tree2.pcGetRoot()->pcGetChild(0)->pcGetChild(0)->pcGetChild(0)->vSetValue(18);
    cout << "\nChildren of 2nd tree =\n";
    tree2.pcGetRoot()->vPrintAllBelow();
    tree1.bMoveSubtree(tree1.pcGetRoot()->pcGetChild(2), tree2.pcGetRoot()->pcGetChild(0), tree2.pcGetRoot());
    cout << "\n\n Move trees\n";
    cout << "\n Children of 1st tree =\n";
    tree1.pcGetRoot()->pcGetChild(2)->vPrintAllBelow();
    cout << "\n\n Children of 2nd tree =\n";
    tree2.pcGetRoot()->vPrintAllBelow(); cout << endl;
}
void dynamic_tree_moving_string();
void dynamic_tree_moving_double();
void dynamic_tree_moving_int();
int main()
{
    cout << "Trees of type String\n\n";
    dynamic_tree_moving_string(); cout << endl;
    cout << "Trees of type Double\n\n";
    dynamic_tree_moving_double(); cout << endl;
    cout << "Trees of type Int\n\n";
    dynamic_tree_moving_int(); cout << endl;
}


