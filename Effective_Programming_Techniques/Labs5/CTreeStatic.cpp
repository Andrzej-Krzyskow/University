//
// Created by Andy on 20.11.2022.
//

#include "CTreeStatic.h"
#include <algorithm>

using namespace std;

CTreeStatic::CTreeStatic() {

}

CTreeStatic::~CTreeStatic() {

}

void CTreeStatic::vPrintTree() {
    root.vPrintAllBelow();
}

bool CTreeStatic::bMoveSubtree(CNodeStatic *pcParentNode, CNodeStatic *pcNewChildNode) {

    pcParentNode->vAddNewChild(pcNewChildNode);

    int index = pcNewChildNode->getParent()->getIndexOfChild(pcNewChildNode);
    vector<CNodeStatic> *newChildNodeChildren = pcNewChildNode->getParent()->getChildren();
    newChildNodeChildren->erase(newChildNodeChildren->begin() + index);


    return true;
}

