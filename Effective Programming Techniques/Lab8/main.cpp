#include "CTab.h"
#include "CTable.h"
#include "CNumber.h"
#include <iostream>

int i_ms_test();
CTab cCreateTab();


int main()
{

    cout << " Before move";
    CTab c_tab1;
    CTab c_tab2;
    cout << " Size = 9" << endl;
    c_tab2.bSetSize(9);
    c_tab1 =  c_tab2;
    std::cout<< c_tab1.iGetSize() << std::endl;
    std::cout<<i_ms_test()<<std::endl;

    std::cout << "\n Task 2 :\n";
    CTable c_table("tableOne",4);
    c_table.setValue(10);
    CTable c_other_tab("tableTwo", 5);
    c_other_tab.setValue(15);
    std::cout << "\n 1st table:\n";
    c_table.vPrint();
    std::cout << "\n 2nd table:\n";
    c_other_tab.vPrint();
    std::cout << "\n After move:\n";
    c_table = std::move(c_other_tab);
    std::cout << "\n 1st table:\n";
    c_table.vPrint();
    std::cout << "\n 2nd table:\n";
    c_table.vPrint();
    std::cout << std::endl;

    std::cout << "\n extra task: \n";
    CNumber number1;
    CNumber other;
    number1.setValue(2);
    number1.vPrint();
    other.setValue(7);
    other.vPrint();
    std::cout << "\nMove table\n";
    number1 = other;
}

CTab cCreateTab()
{
    CTab c_res;
    c_res.bSetSize(5);
    return std::move(c_res);
}//CTab cCreateTab()

int i_ms_test()
{
    CTab c_tab = cCreateTab();
    return c_tab.iGetSize();
}//int i_test()




