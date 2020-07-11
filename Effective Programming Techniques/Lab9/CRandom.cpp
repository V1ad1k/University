#include "CRandom.h"
#include <iostream>
#include <string>
int CRandom::getRandomInt(const int pN1, const int pN2)
{
    if (pN1 < pN2)
    {
        return pN1 + rand() % (pN2 - pN1);
    }
    return pN2 + rand() % (pN1 - pN2);
}

double CRandom::getRandomDouble(const double pN1, const double pN2)
{
    if (pN1 < pN2)
    {
        return (double)rand() / (double)RAND_MAX * (pN2 - pN1) + pN1;
    }
    return (double)rand() / (double)RAND_MAX * (pN1 - pN2) + pN2;
}
