#include <iostream>

using namespace std;


class CTable {

public:
    int *arr;

    CTable() {
        s_name = "TABLE";
        cout << "without: " + s_name << endl;
        length = 10;
        arr = new int[length];
    }

    CTable(string sName, int iTableLen) {

        s_name = sName;

        cout << "parameter: " + s_name << endl;
        length = iTableLen;
        arr = new int[length];
    }

    CTable(CTable &table) {
        s_name = table.s_name + "_copy";

        int *newArr = new int[table.length];
        copy(table.arr, table.arr +  table.length, newArr);

        length = table.length;
        arr = newArr;

        cout << "copy: " + s_name << endl;
    }

    ~CTable() {
        cout << "removing: " + s_name << endl;
        delete[] arr;
    }

    void vSetName(string sName) {
        s_name = sName;
    }

    bool bSetNewSize(int iTableLen) {

        if (iTableLen <= 0 || iTableLen == length) {
            return false;
        }

        int *newArr = new int[iTableLen];
        copy(arr, arr + min(length, iTableLen), newArr);
        delete[] arr;

        length = iTableLen;
        arr = newArr;
        return true;
    }

    CTable *pcClone() {
        /*CTable *newTable = new CTable(this->s_name, this->length);
        int *newArr = new int[this->length];
        copy(this->arr, this->arr + this->length, newArr);
        newTable->arr = newArr;*/
        return new CTable(*this);
    }

    static void v_mod_tab(CTable *pcTab, int iNewSize) {
        int *newArr = new int[iNewSize];
        copy(pcTab->arr, pcTab->arr + min(pcTab->length, iNewSize), newArr);
        delete[] pcTab->arr;

        pcTab->length = iNewSize;
        pcTab->arr = newArr;
    }

    static void v_mod_tab(CTable pcTab, int iNewSize) {
        int *newArr = new int[iNewSize];
        copy(pcTab.arr, pcTab.arr + min(pcTab.length, iNewSize), newArr);
        delete[] pcTab.arr;

        pcTab.length = iNewSize;
        pcTab.arr = newArr;
    }

    string getName() {
        return s_name;
    }

private:
    string s_name;
    int length;

};


int main() {
/*    CTable table = CTable("test", 10);
    cout << table.getName() << endl;*/

    /*CTable c_tab;
    CTable *pc_new_tab;
    pc_new_tab = c_tab.pcClone();*/

    //<editor-fold desc="testing pcClone">
/*        CTable c_tab;


        for (int i = 0; i < 10; i++) {
            c_tab.arr[i] = i+1;
        }
        cout << "\n";
        for (int i = 0; i < 10; i++) {
            cout << c_tab.arr[i]<<" ";
        }
        cout << "\n";

        CTable *pc_new_tab;
        pc_new_tab = c_tab.pcClone();

        for (int i = 0; i < 10; i++) {
            c_tab.arr[i] = i*i;
        }
        cout << "\n";
        for (int i = 0; i < 10; i++) {
            cout << c_tab.arr[i]<<" ";
        }
        cout << "\n";
        for (int i = 0; i < 10; i++) {
            cout << pc_new_tab->arr[i]<<" ";
        }
        cout << "\n";*/
    //</editor-fold>

    //<editor-fold desc="testing v_mod_tab">
    /*CTable c_tab;

    for (int i = 0; i < 10; i++) {
        c_tab.arr[i] = i + 1;
    }
    for (int i = 0; i < 10; i++) {
        cout << c_tab.arr[i] << " ";
    }
    cout << "\nFIRST CALL\n";

    CTable::v_mod_tab(c_tab, 5);
    cout << "end of FIRST CALL\n";

    for (int i = 0; i < 10; i++) {
        cout << c_tab.arr[i] << " ";
    }


    cout << "\nSECOND CALL\n";

    CTable::v_mod_tab(&c_tab, 5);
    cout << "end of SECOND CALL\n";

    for (int i = 0; i < 5; i++) {
        cout << c_tab.arr[i] << " ";
    }
    cout << "\n";*/
    //</editor-fold>

    //<editor-fold desc="Static VS Dynamic">
    //static
    CTable staticTab = CTable("my static table", 15);
    //dynamic --> needs to be deleted by hand, explicitly
    CTable *dynamicTab = new CTable("my dynamic table", 20);
    delete dynamicTab;
    //</editor-fold>

    //<editor-fold desc="List of CTables">
    CTable objects[5];
    //</editor-fold>

    return 0;
}