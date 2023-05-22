//
// Created by Andy on 19.11.2022.
//

#pragma clang diagnostic push
#pragma ide diagnostic ignored "OCUnusedGlobalDeclarationInspection"
#ifndef LABS4_CFILELASTERROR_H
#define LABS4_CFILELASTERROR_H

#include <iostream>
#include <vector>

using namespace std;


class CFileLastError {

public:

    CFileLastError();

    CFileLastError(string sFileName);

    ~CFileLastError();

    void vOpenFile(string sFileName);

    void vPrintLine(string sText);

    void vPrintManyLines(vector<string> sText);

    bool bGetLastError();

private:
    FILE *pf_file = nullptr;
    bool b_last_error = false;
};

#endif //LABS4_CFILELASTERROR_H

#pragma clang diagnostic pop