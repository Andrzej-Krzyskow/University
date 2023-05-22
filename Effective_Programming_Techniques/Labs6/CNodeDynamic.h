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

using namespace std;

template <typename T> class CNodeDynamic {

public:
    CNodeDynamic() {
    };


    CNodeDynamic(T i_value) {
        i_val = i_value;
    };


    ~CNodeDynamic();

    void vSetValue(T iNewVal) {
        i_val = iNewVal;
    };

    int iGetChildrenNumber() {
        return v_children.size();
    };

    void vAddNewChild();

    CNodeDynamic *pcGetChild(int iChildOffset);

    CNodeDynamic *getParent();

    void vPrint() {
        cout << i_val<<" ";
    };

    void vPrintAllBelow();

    void vAddNewChild(CNodeDynamic *newNode);

    vector<CNodeDynamic*> *getChildren();

private:
    vector<CNodeDynamic *> v_children;
    T i_val;

    CNodeDynamic *parent = nullptr;

};

template<typename T>
void CNodeDynamic<T>::vAddNewChild() {
    v_children.emplace_back(new CNodeDynamic());
    v_children[v_children.size() - 1]->parent = this;
}

template<typename T>
CNodeDynamic<T>::~CNodeDynamic() {
    v_children.emplace_back(new CNodeDynamic());
    v_children[v_children.size() - 1]->parent = this;
}

template<typename T>
CNodeDynamic<T> *CNodeDynamic<T>::pcGetChild(int iChildOffset) {
    if (iChildOffset >= v_children.size()) {
        return nullptr;
    }

    return v_children[iChildOffset];
}

template<typename T>
CNodeDynamic<T> *CNodeDynamic<T>::getParent() {
    return this->parent;
}

template<typename T>
void CNodeDynamic<T>::vPrintAllBelow() {
    vPrint();

    for (CNodeDynamic *node: v_children) {
        node->vPrintAllBelow();
    }
}

template<typename T>
void CNodeDynamic<T>::vAddNewChild(CNodeDynamic *newNode) {
    vPrint();

    for (CNodeDynamic *node: v_children) {
        node->vPrintAllBelow();
    }
}

template<typename T>
vector<CNodeDynamic<T> *> *CNodeDynamic<T>::getChildren() {
    return &v_children;
}


#endif //LABS05_CNODEDYNAMIC_H

#pragma clang diagnostic pop