#include <iostream>
#include <string>
#define defaultString "name"
#define defaultsize  20
using namespace std;

class CTable {

public:
	string s_name;
	int size;
	int * pi_table;

	CTable() {
		vSetName(defaultString);
		size = defaultsize;
		cout << "Without : " << s_name << endl;
		pi_table = new int[defaultsize];
	}

	CTable(const string& sName, int iTableLen) {
		vSetName(sName);
		cout << "Parameter : " << sName << endl;
		size = iTableLen;
		pi_table = new int[size];
	}

	CTable(CTable & pcOther) {
		vSetName(pcOther.s_name + "_copy");
		size = pcOther.size;
		pi_table = new int[size];
		for (int i = 0; i < pcOther.size; i++) {
			pi_table[i] = pcOther.pi_table[i];
		}
		cout << "Copy: " << s_name << endl;
	}

	~CTable() {
		delete[] pi_table;
		cout << "Removing: " << s_name << endl;
	}

	void vSetName(string sName) {
		s_name = sName;
	}

	bool bSetNewSize(int iTableLen) {
		if (iTableLen < this->size)
			return false;

		int * pi_tableNew = new int[iTableLen];
		for (int i = 0; i < size; i++) {
			pi_tableNew[i] = pi_table[i];
		}
		delete[] pi_table;
		pi_table = pi_tableNew;
		this->size = iTableLen;
		return true;
	}

	CTable * pcClone() {
		return new CTable(*this);
	}

	void v_mod_tab(CTable *pcTab, int iNewSize) {
		pcTab->bSetNewSize(iNewSize);
	}

	void v_mod_tab(CTable cTab, int iNewSize) {
		cTab.bSetNewSize(iNewSize);
	}

	CTable * pcGetReverted() {
		cout << "Reversing tab" << endl;
		CTable * tab = new CTable(*this);
		for (int i = 0; i < size; i++) {
			tab->pi_table[size - 1 - i] = pi_table[i];
		}
		return tab;
	}

};

int main() {
	CTable tab;
	CTable * pc_new_tab;
	pc_new_tab = tab.pcClone();

	cout << "Size of array of " << pc_new_tab->s_name << ": " << pc_new_tab->size << endl;

	pc_new_tab->v_mod_tab(*pc_new_tab, 6);
	cout << "Size of array of " << pc_new_tab->s_name << ": " << pc_new_tab->size << endl;

	pc_new_tab->v_mod_tab(pc_new_tab, 8);
	cout << "Size of array of " << pc_new_tab->s_name << ": " << pc_new_tab->size << endl;

	pc_new_tab->bSetNewSize(10);
	cout << "Size of array of " << pc_new_tab->s_name << ": " << pc_new_tab->size << endl;

	cout << " array values  of " << pc_new_tab->s_name << endl;
	for (int i = 0; i < pc_new_tab->size; i++) {
		pc_new_tab->pi_table[i] = i;
	}
	cout << "Printing array of " << pc_new_tab->s_name << endl;
	for (int i = 0; i < pc_new_tab->size; i++) {
		cout << pc_new_tab->pi_table[i] << " ";
	}
	cout << endl;


	CTable * rev = pc_new_tab->pcGetReverted();
	cout << "Printing array of " << rev->s_name << endl;
	for (int i = 0; i < rev->size; i++) {
		cout << rev->pi_table[i] << " ";
	}
	cout << endl;

	CTable * pi_table = new CTable[5];
	delete[] pi_table;
	delete pc_new_tab;
	delete rev;
	return 0;
}