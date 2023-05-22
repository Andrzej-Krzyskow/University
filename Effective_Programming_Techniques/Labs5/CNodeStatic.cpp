#pragma clang diagnostic push
#pragma ide diagnostic ignored "cppcoreguidelines-narrowing-conversions"
//
// Created by Andy on 19.11.2022.
//

#include "CNodeStatic.h"
#include <algorithm>

using namespace std;


CNodeStatic::~CNodeStatic() = default; /*{
    for (int i = 0; i < v_children.size(); i++) {
        delete this->pcGetChild(i);
    }

     cout << "deleted"<<i_val<<endl;
}*/

void CNodeStatic::vAddNewChild() {
    v_children.emplace_back();
    v_children[v_children.size() - 1].parent = this;
}

void CNodeStatic::vAddNewChild(CNodeStatic *newNode) {
    v_children.emplace_back(*newNode);
    v_children[v_children.size() - 1].parent = this;
}

CNodeStatic *CNodeStatic::pcGetChild(int iChildOffset) {

    if (iChildOffset >= v_children.size()) {
        return nullptr;
    }

    return &v_children[iChildOffset];
}

void CNodeStatic::vPrintAllBelow() {

    vPrint();

    for (CNodeStatic node: v_children) {
        node.vPrintAllBelow();
    }

}


void CNodeStatic::vPrintUp() {
    vPrint();

    if (parent == nullptr) {
        cout << endl;
        return;
    }

    parent->vPrintUp();
}

CNodeStatic *CNodeStatic::getParent() {
    return this->parent;
}

vector<CNodeStatic> *CNodeStatic::getChildren() {
    return &v_children;
}

bool CNodeStatic::operator==(const CNodeStatic &rhs) const {
    return v_children == rhs.v_children &&
           i_val == rhs.i_val &&
           parent == rhs.parent;
}

bool CNodeStatic::operator!=(const CNodeStatic &rhs) const {
    return !(rhs == *this);
}

int CNodeStatic::getIndexOfChild(CNodeStatic *element) {

    auto iterator = std::find(v_children.begin(), v_children.end(), *element);
    int index;

    if (iterator != v_children.end()) {
        index = iterator - v_children.begin();
    } else {
        index = -1;
    }
    return index;
}


#pragma clang diagnostic pop