#pragma clang diagnostic push
#pragma ide diagnostic ignored "hicpp-exception-baseclass"
#pragma ide diagnostic ignored "cppcoreguidelines-pro-type-member-init"
#pragma ide diagnostic ignored "OCUnusedGlobalDeclarationInspection"
#pragma ide diagnostic ignored "performance-unnecessary-value-param"
//
// Created by Andy on 19.11.2022.
//
#include "CFileThrowEx.h"

using namespace std;

CFileThrowEx::CFileThrowEx() = default;

CFileThrowEx::CFileThrowEx(string sFileName) {
    vOpenFile(sFileName);
}

CFileThrowEx::~CFileThrowEx() {
    if (pf_file != nullptr) {
        fclose(pf_file);
    }
}

void CFileThrowEx::vOpenFile(string sFileName) {
    pf_file = fopen(sFileName.c_str(), "w+");
}

void CFileThrowEx::vPrintLine(string sText) {
    if (pf_file != nullptr){
        fprintf(pf_file, "%s", sText.c_str());
    } else {
        try {
            throw 1;
        } catch (int e) {
            cout<<"\nAn exception #" << e << '\n';
        }
    }

}


void CFileThrowEx::vPrintManyLines(vector<string> sText) {
    for ( string& i : sText) {
        vPrintLine(i);
    }
}


#pragma clang diagnostic pop