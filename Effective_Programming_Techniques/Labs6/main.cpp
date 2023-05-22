#include <iostream>
#include "CNodeDynamic.h"

using namespace std;

void cNodeDynamicTest(){

    CNodeDynamic<int> rootI(0);


    //rootI.vSetValue(0);
    rootI.vAddNewChild();
    rootI.vAddNewChild();
    rootI.pcGetChild(0)->vSetValue(1);
    rootI.pcGetChild(1)->vSetValue(2);

    rootI.pcGetChild(0)->vAddNewChild();
    rootI.pcGetChild(0)->vAddNewChild();
    rootI.pcGetChild(0)->pcGetChild(0)->vSetValue(11);
    rootI.pcGetChild(0)->pcGetChild(1)->vSetValue(12);

    rootI.pcGetChild(1)->vAddNewChild();
    rootI.pcGetChild(1)->vAddNewChild();
    rootI.pcGetChild(1)->pcGetChild(0)->vSetValue(21);
    rootI.pcGetChild(1)->pcGetChild(1)->vSetValue(22);

    rootI.vPrintAllBelow();
    cout << endl;

    CNodeDynamic<double> rootD(0.0);

    //rootD.vSetValue(0.0);
    rootD.vAddNewChild();
    rootD.vAddNewChild();
    rootD.pcGetChild(0)->vSetValue(1.1);
    rootD.pcGetChild(1)->vSetValue(2.2);

    rootD.pcGetChild(0)->vAddNewChild();
    rootD.pcGetChild(0)->vAddNewChild();
    rootD.pcGetChild(0)->pcGetChild(0)->vSetValue(11.11);
    rootD.pcGetChild(0)->pcGetChild(1)->vSetValue(12.12);

    rootD.pcGetChild(1)->vAddNewChild();
    rootD.pcGetChild(1)->vAddNewChild();
    rootD.pcGetChild(1)->pcGetChild(0)->vSetValue(21.21);
    rootD.pcGetChild(1)->pcGetChild(1)->vSetValue(22.22);

    rootD.vPrintAllBelow();


    CNodeDynamic<string> rootS("O");

    //rootS.vSetValue("O");
    rootS.vAddNewChild();
    rootS.vAddNewChild();
    rootS.pcGetChild(0)->vSetValue("A");
    rootS.pcGetChild(1)->vSetValue("B");

    rootS.pcGetChild(0)->vAddNewChild();
    rootS.pcGetChild(0)->vAddNewChild();
    rootS.pcGetChild(0)->pcGetChild(0)->vSetValue("AA");
    rootS.pcGetChild(0)->pcGetChild(1)->vSetValue("AB");

    rootS.pcGetChild(1)->vAddNewChild();
    rootS.pcGetChild(1)->vAddNewChild();
    rootS.pcGetChild(1)->pcGetChild(0)->vSetValue("BA");
    rootS.pcGetChild(1)->pcGetChild(1)->vSetValue("BB");

    cout << endl;
    rootS.vPrintAllBelow();
}


int main() {

    cNodeDynamicTest();

    return 0;
}
