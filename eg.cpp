#include <iostream>
#include <vector>
using namespace std;

class CustomStack {
private:
    vector<int> stack;
    int maxSize;

public:
    // Constructor
    CustomStack(int size) {
        maxSize = size;
    }

    // Push
    void push(int x) {
        if (stack.size() < maxSize) {
            stack.push_back(x);
            cout << x << " pushed onto stack.\n";
        } else {
            cout << "Stack is full, cannot push " << x << ".\n";
        }
    }

    // Pop
    int pop() {
        if (stack.empty()) {
            cout << "Stack is empty.\n";
            return -1;
        }
        int val = stack.back();
        stack.pop_back();
        cout << val << " popped from stack.\n";
        return val;
    }

    // Increment bottom k elements
    void increment(int k, int val) {
        if (stack.empty()) {
            cout << "Stack is empty.\n";
            return;
        }
        int limit = (k <= stack.size()) ? k : stack.size();
        for (int i = 0; i < limit; i++) {
            stack[i] += val;
        }
        cout << "Bottom " << limit << " elements incremented by " << val << ".\n";
    }

    // Display
    void display() {
        if (stack.empty()) {
            cout << "Stack is empty.\n";
            return;
        }
        cout << "Current stack (bottom -> top): ";
        for (int x : stack) {
            cout << x << " ";
        }
        cout << "\n";
    }
};

// ===============================
// UI Driver
// ===============================
int main() {
    int size;
    cout << "Enter max stack size: ";
    cin >> size;

    CustomStack stk(size);

    while (true) {
        cout << "\n===== Custom Stack Menu =====\n";
        cout << "1. Push\n";
        cout << "2. Pop\n";
        cout << "3. Increment\n";
        cout << "4. Display\n";
        cout << "5. Exit\n";
        cout << "Choose an option: ";

        int choice;
        cin >> choice;

        switch (choice) {
            case 1: {
                int x;
                cout << "Enter value to push: ";
                cin >> x;
                stk.push(x);
                break;
            }
            case 2:
                stk.pop();
                break;
            case 3: {
                int k, val;
                cout << "Enter k and val: ";
                cin >> k >> val;
                stk.increment(k, val);
                break;
            }
            case 4:
                stk.display();
                break;
            case 5:
                cout << "Exiting...\n";
                return 0;
            default:
                cout << "Invalid choice!\n";
        }
    }
}

