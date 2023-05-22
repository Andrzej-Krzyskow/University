//
// Created by Andy on 20.11.2022.
//

#include "CNodeDynamic.h"
#include <algorithm>

using namespace std;

CNodeDynamic::~CNodeDynamic() {

    cout << "deleted " << i_val << " ";

    for (int i = 0; i < v_children.size(); i++) {
        delete this->pcGetChild(i);
    }
}

void CNodeDynamic::vAddNewChild() {
    v_children.emplace_back(new CNodeDynamic());
    v_children[v_children.size() - 1]->parent = this;
}

void CNodeDynamic::vAddNewChild(CNodeDynamic *newNode) {
    v_children.emplace_back(newNode);
    v_children[v_children.size() - 1]->parent = this;
}

CNodeDynamic *CNodeDynamic::pcGetChild(int iChildOffset) {

    if (iChildOffset >= v_children.size()) {
        return nullptr;
    }

    return v_children[iChildOffset];
}

CNodeDynamic *CNodeDynamic::getParent() {
    return this->parent;
}

void CNodeDynamic::vPrintAllBelow() {

    vPrint();

    for (CNodeDynamic *node: v_children) {
        node->vPrintAllBelow();
    }
}

bool CNodeDynamic::operator==(const CNodeDynamic &rhs) const {
    return v_children == rhs.v_children &&
           i_val == rhs.i_val &&
           parent == rhs.parent;
}

bool CNodeDynamic::operator!=(const CNodeDynamic &rhs) const {
    return !(rhs == *this);
}

int CNodeDynamic::getIndexOfChild(CNodeDynamic *element) {
    auto iterator = std::find(v_children.begin(), v_children.end(), element);
    int index;

    if (iterator != v_children.end()) {
        index = iterator - v_children.begin();
    } else {
        index = -1;
    }
    return index;
}

vector<CNodeDynamic*>* CNodeDynamic::getChildren() {
    return &v_children;
}
