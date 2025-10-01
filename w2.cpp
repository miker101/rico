#include <iostream>
#include <queue>
using namespace std;

// Tree Node Definition
struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

// Check mirror symmetry
bool isMirror(TreeNode* t1, TreeNode* t2) {
    if (!t1 && !t2) return true;
    if (!t1 || !t2) return false;
    return (t1->val == t2->val) &&
           isMirror(t1->left, t2->right) &&
           isMirror(t1->right, t2->left);
}

bool isSymmetric(TreeNode* root) {
    if (!root) return true;
    return isMirror(root->left, root->right);
}

// Build tree from level-order input
TreeNode* buildTree() {
    cout << "Enter values level by level (use -1 for NULL):\n";
    int val;
    cin >> val;
    if (val == -1) return NULL;

    TreeNode* root = new TreeNode(val);
    queue<TreeNode*> q;
    q.push(root);

    while (!q.empty()) {
        TreeNode* node = q.front();
        q.pop();

        int leftVal, rightVal;
        cout << "Enter left child of " << node->val << " (-1 for NULL): ";
        cin >> leftVal;
        if (leftVal != -1) {
            node->left = new TreeNode(leftVal);
            q.push(node->left);
        }

        cout << "Enter right child of " << node->val << " (-1 for NULL): ";
        cin >> rightVal;
        if (rightVal != -1) {
            node->right = new TreeNode(rightVal);
            q.push(node->right);
        }
    }
    return root;
}

// UI Menu
int main() {
    cout << "==== Symmetric Tree Checker ====\n";

    TreeNode* root = buildTree();

    cout << "\nChecking symmetry...\n";
    if (isSymmetric(root)) {
        cout << "✅The tree IS symmetric!\n";
    } else {
        cout << " The tree is NOT symmetric.\n";
    }

    return 0;
}

