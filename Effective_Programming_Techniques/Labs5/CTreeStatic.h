//
// Created by Andy on 20.11.2022.
//

#ifndef LABS05_CTREESTATIC_H
#define LABS05_CTREESTATIC_H

#include "CNodeStatic.h"

using namespace std;

class CTreeStatic {

public:
    CTreeStatic();

    ~CTreeStatic();

    CNodeStatic *pcGetRoot() {
        return (&root);
    }

    void vPrintTree();

    bool bMoveSubtree(CNodeStatic *pcParentNode, CNodeStatic *pcNewChildNode);

private:
    CNodeStatic root;

};


#endif //LABS05_CTREESTATIC_H
