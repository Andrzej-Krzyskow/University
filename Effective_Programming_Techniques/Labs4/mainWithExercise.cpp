#include <iostream>
#include "CFileLastError.h"
#include "CFileThrowEx.h"
#include "CFileErrCode.h"

using namespace std;

void v_test_file() {
    FILE *pf_file;
    pf_file = fopen("filename", "w+");
    if (pf_file == nullptr) return;
    fprintf(pf_file, "write sth to a filebxbxcbxcb");
    fclose(pf_file);
}

int main() {

    // ex. 1
    CFileLastError cFileLastError = CFileLastError();

    cout << cFileLastError.bGetLastError() << endl;
    cFileLastError.vPrintLine("testing writing");
    cout << cFileLastError.bGetLastError();

    // ex. 2
    CFileThrowEx cFileThrowEx = CFileThrowEx();

    cFileThrowEx.vPrintLine("testing writing");

    // ex. 3

    CFileErrCode cFileErrCode = CFileErrCode();

    cout << cFileErrCode.bPrintLine("testing writing") << endl;


    // EXERCISE
    string fileName = "filename.txt";
    for (int i = 0; i < 10; i++) {
        cFileLastError.vOpenFile(fileName);
        cFileThrowEx.vOpenFile(fileName);
        cFileErrCode.bOpenFile(fileName);
    }



    return 0;
}



/*
ex. 1
 Consider whether using GetLastError error reporting is convenient. ------ Not really, error occurred, and we can still do things
 Is writing nested methods (one method calls the other) with such error reporting suitable? ------ Nope, as during execution of nested method, error can occur


  */