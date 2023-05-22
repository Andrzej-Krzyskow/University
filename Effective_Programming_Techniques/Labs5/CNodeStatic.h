#pragma clang diagnostic push
#pragma ide diagnostic ignored "readability-make-member-function-const"
#pragma ide diagnostic ignored "cppcoreguidelines-narrowing-conversions"
//
// Created by Andy on 19.11.2022.
//

#ifndef LABS05_CNODESTATIC_H
#define LABS05_CNODESTATIC_H

#include <iostream>
#include <vector>

using namespace std;

class CNodeStatic {

public:

    CNodeStatic() {
        i_val = 0;
    }

/*    CNodeStatic(CNodeStatic &parent) {
        i_val = 0;
        this->parent = &parent;
    };*/

    ~CNodeStatic();

    void vSetValue(int iNewVal) {
        i_val = iNewVal;
    };

    int iGetChildrenNumber() {
        return (v_children.size());
    };

    void vAddNewChild();

    void vAddNewChild(CNodeStatic* newNode);

    CNodeStatic *pcGetChild(int iChildOffset);

    void vPrint() {
        cout << i_val<<" ";
    };

    void vPrintAllBelow();

    void vPrintUp();

    CNodeStatic *getParent();

    vector<CNodeStatic> *getChildren();

    int getIndexOfChild(CNodeStatic *element);

    bool operator==(const CNodeStatic &rhs) const;
    bool operator!=(const CNodeStatic &rhs) const;
private:
    vector<CNodeStatic> v_children;


    int i_val;

    CNodeStatic *parent = nullptr;

    void printUpRec(CNodeStatic &);
};


#endif //LABS05_CNODESTATIC_H

#pragma clang diagnostic pop