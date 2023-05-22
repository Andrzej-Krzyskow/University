//
// Created by Andy on 19.11.2022.
//

#pragma clang diagnostic push
#pragma ide diagnostic ignored "OCUnusedGlobalDeclarationInspection"

#ifndef LABS4_CFILEERRCODE_H
#define LABS4_CFILEERRCODE_H

#include <iostream>
#include <vector>

using namespace std;

class CFileErrCode {

public:

    CFileErrCode();

    CFileErrCode(string sFileName);

    ~CFileErrCode();

    bool bOpenFile(string sFileName);

    bool bPrintLine(string sText);

    bool bPrintManyLines(vector<string> sText);

private:
    FILE *pf_file = nullptr;
};


#endif //LABS4_CFILEERRCODE_H

#pragma clang diagnostic pop