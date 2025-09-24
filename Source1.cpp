class Solution {
public:
	bool isPalindrome(int x) {
		// Negative numbers and numbers ending with 0 (except 0 itself) can't be palindrome
		if (x < 0 || (x % 10 == 0 && x != 0)) return false;

		int reversedHalf = 0;
		while (x > reversedHalf) {
			int pop = x % 10;
			reversedHalf = reversedHalf * 10 + pop;
			x /= 10;
		}

		// For even length palindromes, x == reversedHalf
		// For odd length, drop the middle digit (reversedHalf / 10)
		return (x == reversedHalf) || (x == reversedHalf / 10);
	}
};
