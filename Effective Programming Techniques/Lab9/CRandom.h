    #ifndef CRandom_H
    #define CRandom_H
    #include<ctime>
    #include<iostream>
    #include <string>
    using namespace std;

    class CRandom
    {
    public:
        CRandom() {};
        ~CRandom() {};
        int getRandomInt(const int pN1, const int pN2);
        double getRandomDouble(const double pN1, const double pN2);
    };

    #endif