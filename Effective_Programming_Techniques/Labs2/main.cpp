#include <iostream>

using namespace std;

void v_alloc_table_add_5(int iSize) {

    if (iSize < 1) {
        return;
    }

    int *pointer = new int[iSize];

    for (int i = 0; i < iSize; i++) {
        pointer[i] = i + 5;
        cout << pointer[i] << " ";
    }

    delete[] pointer;

}

bool b_alloc_table_2_dim(int ***piTable, int iSizeX, int iSizeY) {

    if (iSizeX < 1 || iSizeY < 1) {
        return false;
    }

    int **table = new int *[iSizeX];

    for (int i = 0; i < iSizeX; i++) {
        table[i] = new int[iSizeY];
    }

    for (int i = 0; i < iSizeY; i++) {
        for (int j = 0; j < iSizeX; j++) {
            table[i][j] = i + j + 1;
            //cout<<table[i][j]<<" ";
        }
        //cout << endl;
    }

    *piTable = table;

    return true;
}

bool b_dealloc_table_2_dim(int ***piTable, int iSizeX) {

    if (*piTable == nullptr || iSizeX < 1) {
        return false;
    }

    for (int i = 0; i < iSizeX; i++) {
        delete[] (*piTable)[i];
    }

    delete[] *piTable;
    *piTable = nullptr;

    return true;
}


int main() {

    cout << "\nex. 1" << endl;
    v_alloc_table_add_5(5);

    cout << "\n\nex. 2" << endl;

    int **pi_table;
    int ***ptr = &pi_table;
    //cout << "Returned: " << b_alloc_table_2_dim(ptr, 5, 3) << endl;
    b_alloc_table_2_dim(ptr, 5, 3);

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 5; j++) {
            cout << pi_table[i][j] << " ";
        }
        cout << endl;
    }

    cout << endl << "ex. 3" << endl;

    if (b_dealloc_table_2_dim(ptr, 5)) {
        cout << "Memory freed";
    } else {
        cout << "Memory is still reserved";
    }

    return 0;
}
