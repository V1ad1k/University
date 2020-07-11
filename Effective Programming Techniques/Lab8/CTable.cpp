//
// Created by Vlad on 12/12/2019.
//

#ifndef LAB8_CTABLE_H
#define LAB8_CTABLE_H
#pragma once

#include <string>
#include <iostream>

using namespace std;

class CTable {
private:
    string s_name;
    int* array;
    int tableLength;

public:
    CTable operator + (CTable& obj) {
        int tLength;


        if (tableLength > obj.tableLength)
            tLength = tableLength;
        else
            tLength = obj.tableLength;

        CTable retTable("added", tLength);
        for (int i = 0; i < tLength; i++) {
            if (i >= tableLength)
                retTable.array[i] = 0 + obj.array[i];
            else if (i >= obj.tableLength)
                retTable.array[i] = array[i] + 0;
            else
                retTable.array[i] = array[i] + obj.array[i];
        }

        retTable.vPrint();
        return retTable;
    }

    CTable() {
        s_name = "empty";
        cout << "without: " + s_name + "\n";
        array = new int[10];
    }

    CTable(string sName, int iTableLen) {
        s_name = sName;
        cout << "parameter: " + sName + "\n";
        array = new int[iTableLen];
        tableLength = iTableLen;
    }

    CTable(CTable& pcOther) {
        s_name = pcOther.s_name + "_copy";

        cout << "copy: " + s_name + "\n";
        array = pcOther.array;
        tableLength = pcOther.tableLength;
    }

    ~CTable() {
        cout << s_name << " deleted " "\n";
        s_name = "";
    }

    void vSetName(string sName) {
        s_name = sName;
    }

    bool bSetNewSize(int iTableLen) {
        if (iTableLen > 0) {
            tableLength = iTableLen;
            array = NULL;
            array = new int[iTableLen];
            return true;
        }
        else
            return false;
    }

    CTable* pcClone() {
        return new CTable(*this);
    }

    void setValue(int startValue) {
        for (int i = 0; i < tableLength; i++)
            array[i] = startValue + i + 1;
    }


    void vPrint() {
        for (int i = 0; i < tableLength; i++)
            cout << array[i] << " ";
        cout << endl;
    }

};


#endif //LAB8_CTABLE_H
