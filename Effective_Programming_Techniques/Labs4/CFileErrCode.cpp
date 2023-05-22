#pragma clang diagnostic push
#pragma ide diagnostic ignored "cppcoreguidelines-pro-type-member-init"
#pragma ide diagnostic ignored "OCUnusedGlobalDeclarationInspection"
#pragma ide diagnostic ignored "performance-unnecessary-value-param"
//
// Created by Andy on 19.11.2022.
//

#include "CFileErrCode.h"

using namespace std;

CFileErrCode::CFileErrCode() = default;

CFileErrCode::CFileErrCode(string sFileName) {
    bOpenFile(sFileName);
}

CFileErrCode::~CFileErrCode() {
    if(pf_file!= nullptr) {
        fclose(pf_file);
    }
}

bool CFileErrCode::bOpenFile(string sFileName) {

    pf_file = fopen(sFileName.c_str(), "w+");

    if (pf_file == nullptr) {
        return false;
    }

    return true;
}

bool CFileErrCode::bPrintLine(string sText) {

    if (pf_file != nullptr) {
        fprintf(pf_file, "%s", sText.c_str());
        return true;
    }

    return false;
}

bool CFileErrCode::bPrintManyLines(vector<string> sText) {

    bool temp;

    for ( string& i : sText) {
        temp = bPrintLine(i);
    }

    return temp;
}


#pragma clang diagnostic pop