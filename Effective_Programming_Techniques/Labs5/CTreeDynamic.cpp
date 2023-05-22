//
// Created by Andy on 20.11.2022.
//

#include "CTreeDynamic.h"

using namespace std;

CTreeDynamic::CTreeDynamic() {

}

CTreeDynamic::~CTreeDynamic() {

}

void CTreeDynamic::vPrintTree() {
    root->vPrintAllBelow();
}

bool CTreeDynamic::bMoveSubtree(CNodeDynamic *pcParentNode, CNodeDynamic *pcNewChildNode) {

    int index = pcNewChildNode->getParent()->getIndexOfChild(pcNewChildNode);
    vector<CNodeDynamic*> *alienChildren = pcNewChildNode->getParent()->getChildren();
    alienChildren->erase(alienChildren->begin()+index);
    pcParentNode->vAddNewChild(pcNewChildNode);

    return true;
}
