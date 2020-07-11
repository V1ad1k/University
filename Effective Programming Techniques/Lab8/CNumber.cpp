#pragma once
#include <iostream>

class CNumber
{
public:
    CNumber() { i_size = 1;
    pi_number = new int[i_size];}

    CNumber(const CNumber& cOther)
    {
        pi_number = cOther.pi_number;
        i_size = cOther.i_size;
        std::cout << "Copy \n";
    }

    CNumber(CNumber&& cOther)
    {
        pi_number = cOther.pi_number;
        i_size = cOther.i_size;
        cOther.pi_number = NULL;
        std::cout << "MOVE \n";
    }

    ~CNumber() { if (pi_number != NULL) delete pi_number; std::cout << "CNumber Destructor\n"; }

    CNumber operator=(const CNumber& cOther) {
        if (pi_number != NULL) delete pi_number;
        i_size = cOther.i_size;
        pi_number = new int[i_size];
        for(int i = 0; i < i_size; i++)
            pi_number[i] = cOther.pi_number[i];
        return std::move(*this);
    }



    void setValue(int a) {
        for (int i = 0; i < i_size; i++) {
            pi_number[i] = a + i;
        }
    }

    void vPrint() {
        for (int i = 0; i < i_size; i++)
            std::cout << " " << pi_number[i];
        std::cout << std::endl;
    }
private:
    int* pi_number;
    int i_size;
};

