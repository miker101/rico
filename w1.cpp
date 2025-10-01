#include <iostream>
#include <vector>
using namespace std;

class Queue {
private:
    vector<int> q;
    int front, rear, count, capacity;

public:
    Queue(int k) {
        q.resize(k);
        capacity = k;
        front = 0;
        rear = 0;
        count = 0;
    }

    bool enQueue(int value) {
        if (isFull()) return false;
        q[rear] = value;
        rear = (rear + 1) % capacity;
        count++;
        return true;
    }

    bool deQueue() {
        if (isEmpty()) return false;
        front = (front + 1) % capacity;
        count--;
        return true;
    }

    int Front() {
        return isEmpty() ? -1 : q[front];
    }

    int Rear() {
        return isEmpty() ? -1 : q[(rear - 1 + capacity) % capacity];
    }

    bool isEmpty() {
        return count == 0;
    }

    bool isFull() {
        return count == capacity;
    }

    void printQueue() {
        if (isEmpty()) {
            cout << "Queue is empty.\n";
            return;
        }
        cout << "Queue elements: ";
        for (int i = 0; i < count; i++) {
            int index = (front + i) % capacity;
            cout << q[index] << " ";
        }
        cout << endl;
    }
};

int main() {
    int size;
    cout << "Enter size of Circular Queue: ";
    cin >> size;
    MyCircularQueue cq(size);

    int choice, val;
    while (true) {
        cout << "\n--- Circular Queue Menu ---\n";
        cout << "1. Enqueue\n";
        cout << "2. Dequeue\n";
        cout << "3. Front\n";
        cout << "4. Rear\n";
        cout << "5. Check Empty\n";
        cout << "6. Check Full\n";
        cout << "7. Display Queue\n";
        cout << "0. Exit\n";
        cout << "Enter choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                cout << "Enter value: ";
                cin >> val;
                if (cq.enQueue(val)) cout << "Enqueued " << val << endl;
                else cout << "Queue is full!\n";
                break;
            case 2:
                if (cq.deQueue()) cout << "Dequeued.\n";
                else cout << "Queue is empty!\n";
                break;
            case 3:
                cout << "Front element: " << cq.Front() << endl;
                break;
            case 4:
                cout << "Rear element: " << cq.Rear() << endl;
                break;
            case 5:
                cout << (cq.isEmpty() ? "Queue is empty.\n" : "Queue is NOT empty.\n");
                break;
            case 6:
                cout << (cq.isFull() ? "Queue is full.\n" : "Queue is NOT full.\n");
                break;
            case 7:
                cq.displayQueue();
                break;
            case 0:
                cout << "Exiting...\n";
                return 0;
            default:
                cout << "Invalid choice! Try again.\n";
        }
    }
}

