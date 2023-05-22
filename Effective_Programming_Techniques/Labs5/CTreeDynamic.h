//
// Created by Andy on 20.11.2022.
//

#ifndef LABS05_CTREEDYNAMIC_H
#define LABS05_CTREEDYNAMIC_H

#include "CNodeDynamic.h"

using namespace std;

class CTreeDynamic {
public:
    CTreeDynamic();

    ~CTreeDynamic();

    CNodeDynamic *pcGetRoot() {
        return root;
    }

    void vPrintTree();

    bool bMoveSubtree(CNodeDynamic *pcParentNode, CNodeDynamic *pcNewChildNode);

private:
    CNodeDynamic *root = new CNodeDynamic();
};


#endif //LABS05_CTREEDYNAMIC_H
