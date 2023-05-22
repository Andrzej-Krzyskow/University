#include <iostream>
#include "CNodeStatic.h"
#include "CTreeStatic.h"
#include "CNodeDynamic.h"
#include "CTreeDynamic.h"

using namespace std;

void v_tree_static_test() {

    // 2 test
    CNodeStatic root;

    root.vAddNewChild();
    root.vAddNewChild();
    root.pcGetChild(0)->vSetValue(1);
    root.pcGetChild(1)->vSetValue(2);

    root.pcGetChild(0)->vAddNewChild();
    root.pcGetChild(0)->vAddNewChild();
    root.pcGetChild(0)->pcGetChild(0)->vSetValue(11);
    root.pcGetChild(0)->pcGetChild(1)->vSetValue(12);

    root.pcGetChild(1)->vAddNewChild();
    root.pcGetChild(1)->vAddNewChild();
    root.pcGetChild(1)->pcGetChild(0)->vSetValue(21);
    root.pcGetChild(1)->pcGetChild(1)->vSetValue(22);

    // my 2 test
    root.vPrintAllBelow();
    cout << endl << root.pcGetChild(5);
    cout << endl << root.iGetChildrenNumber();

    // 3 test
    cout << "\n\n";
    root.pcGetChild(0)->pcGetChild(1)->vPrintUp();

    // 6 test
    CTreeStatic tree1;
    CTreeStatic tree2;
    CNodeStatic *root1 = tree1.pcGetRoot();
    CNodeStatic *root2 = tree2.pcGetRoot();

    root1->vAddNewChild();
    root1->vAddNewChild();
    root1->vAddNewChild();
    root1->pcGetChild(0)->vSetValue(1);
    root1->pcGetChild(1)->vSetValue(2);
    root1->pcGetChild(2)->vSetValue(3);


    root1->pcGetChild(2)->vAddNewChild();
    root1->pcGetChild(2)->pcGetChild(0)->vSetValue(4);

    root2->vSetValue(50);
    root2->vAddNewChild();
    root2->vAddNewChild();
    root2->pcGetChild(0)->vSetValue(54);
    root2->pcGetChild(1)->vSetValue(55);
    root2->pcGetChild(1)->vAddNewChild();
    root2->pcGetChild(1)->pcGetChild(0)->vSetValue(66);

    root2->pcGetChild(0)->vAddNewChild();
    root2->pcGetChild(0)->vAddNewChild();
    root2->pcGetChild(0)->pcGetChild(0)->vSetValue(56);
    root2->pcGetChild(0)->pcGetChild(1)->vSetValue(57);

    root2->pcGetChild(0)->pcGetChild(0)->vAddNewChild();
    root2->pcGetChild(0)->pcGetChild(0)->pcGetChild(0)->vSetValue(58);

    tree1.bMoveSubtree(root1->pcGetChild(2), root2->pcGetChild(0));
}

void v_tree_dynamic_test() {
    CNodeDynamic root;

    root.vAddNewChild();
    root.vAddNewChild();
    root.pcGetChild(0)->vSetValue(1);
    root.pcGetChild(1)->vSetValue(2);

    root.pcGetChild(0)->vAddNewChild();
    root.pcGetChild(0)->vAddNewChild();
    root.pcGetChild(0)->pcGetChild(0)->vSetValue(11);
    root.pcGetChild(0)->pcGetChild(1)->vSetValue(12);

    root.pcGetChild(1)->vAddNewChild();
    root.pcGetChild(1)->vAddNewChild();
    root.pcGetChild(1)->pcGetChild(0)->vSetValue(21);
    root.pcGetChild(1)->pcGetChild(1)->vSetValue(22);

    // my 2 test
    root.vPrintAllBelow();
    cout << endl << root.pcGetChild(5);
    cout << endl << root.iGetChildrenNumber();

    cout << "\n\n";

    // 3 test
/*    cout << endl<<endl;
    root.pcGetChild(0)->pcGetChild(1)->vPrintUp();*/

// 6 test
    CTreeDynamic tree1;
    CTreeDynamic tree2;
    CNodeDynamic *root1 = tree1.pcGetRoot();
    CNodeDynamic *root2 = tree2.pcGetRoot();

    root1->vAddNewChild();
    root1->vAddNewChild();
    root1->vAddNewChild();
    root1->pcGetChild(0)->vSetValue(1);
    root1->pcGetChild(1)->vSetValue(2);
    root1->pcGetChild(2)->vSetValue(3);


    root1->pcGetChild(2)->vAddNewChild();
    root1->pcGetChild(2)->pcGetChild(0)->vSetValue(4);

    root2->vSetValue(50);
    root2->vAddNewChild();
    root2->vAddNewChild();
    root2->pcGetChild(0)->vSetValue(54);
    root2->pcGetChild(1)->vSetValue(55);
    root2->pcGetChild(1)->vAddNewChild();
    root2->pcGetChild(1)->pcGetChild(0)->vSetValue(66);

    root2->pcGetChild(0)->vAddNewChild();
    root2->pcGetChild(0)->vAddNewChild();
    root2->pcGetChild(0)->pcGetChild(0)->vSetValue(56);
    root2->pcGetChild(0)->pcGetChild(1)->vSetValue(57);

    root2->pcGetChild(0)->pcGetChild(0)->vAddNewChild();
    root2->pcGetChild(0)->pcGetChild(0)->pcGetChild(0)->vSetValue(58);

    tree1.bMoveSubtree(root1->pcGetChild(2), root2->pcGetChild(0));


}


int main() {
    v_tree_static_test();
    cout << "---------------------------------------\n";
    v_tree_dynamic_test();

    return 0;
}
