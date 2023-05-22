#pragma clang diagnostic push
#pragma ide diagnostic ignored "readability-make-member-function-const"
#pragma ide diagnostic ignored "cppcoreguidelines-narrowing-conversions"
//
// Created by Andy on 20.11.2022.
//

#ifndef LABS05_CNODEDYNAMIC_H
#define LABS05_CNODEDYNAMIC_H

#include <iostream>
#include <vector>

class CNodeDynamic {

public:
    CNodeDynamic() {
        i_val = 0;
    };

    ~CNodeDynamic();

    void vSetValue(int iNewVal) {
        i_val = iNewVal;
    };

    int iGetChildrenNumber() {
        return v_children.size();
    };

    void vAddNewChild();



    CNodeDynamic *pcGetChild(int iChildOffset);

    CNodeDynamic *getParent();

    int getIndexOfChild(CNodeDynamic *element);

    void vPrint() {
        cout << i_val<<" ";
    };

    void vPrintAllBelow();

    bool operator==(const CNodeDynamic &rhs) const;

    bool operator!=(const CNodeDynamic &rhs) const;

    void vAddNewChild(CNodeDynamic *newNode);

    vector<CNodeDynamic*> *getChildren();

private:
    vector<CNodeDynamic *> v_children;
    int i_val;

    CNodeDynamic *parent = nullptr;

};


#endif //LABS05_CNODEDYNAMIC_H

#pragma clang diagnostic pop