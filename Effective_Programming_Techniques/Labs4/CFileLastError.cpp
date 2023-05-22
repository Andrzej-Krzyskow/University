#pragma clang diagnostic push
#pragma ide diagnostic ignored "cppcoreguidelines-pro-type-member-init"
#pragma ide diagnostic ignored "OCUnusedGlobalDeclarationInspection"
#pragma ide diagnostic ignored "performance-unnecessary-value-param"
//
// Created by Andy on 19.11.2022.
//
#include "CFileLastError.h"

CFileLastError::CFileLastError() = default;

CFileLastError::CFileLastError(string sFileName) {
    vOpenFile(sFileName);
}

CFileLastError::~CFileLastError() {
    if(pf_file!= nullptr) {
        fclose(pf_file);
    }
}

void CFileLastError::vOpenFile(string sFileName) {
    pf_file = fopen(sFileName.c_str(), "w+");
}

void CFileLastError::vPrintLine(string sText) {
    if (pf_file != nullptr) {
        fprintf(pf_file, "%s", sText.c_str());
    } else {
        b_last_error = true;
    }
}

void CFileLastError::vPrintManyLines(vector<string> sText) {
    for ( string& i : sText) {
        vPrintLine(i);
    }
}

bool CFileLastError::bGetLastError() {
    return (b_last_error);
}

#pragma clang diagnostic pop