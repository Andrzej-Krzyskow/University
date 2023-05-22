//
// Created by Andy on 19.11.2022.
//

#ifndef LABS4_CFILETHROWEX_H
#define LABS4_CFILETHROWEX_H

#include <iostream>
#include <vector>

using namespace std;

class CFileThrowEx {
public:
    CFileThrowEx();

    CFileThrowEx(string sFileName);

    ~CFileThrowEx();

    void vOpenFile(string sFileName);

    void vPrintLine(string sText);

    void vPrintManyLines(vector<string> sText);

private:
    FILE *pf_file = nullptr;
};


#endif //LABS4_CFILETHROWEX_H
