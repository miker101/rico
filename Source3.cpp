class FoodRatings {
    
    unordered_map<string, string> foodToCuisine;   // food -> cuisine
    unordered_map<string, int> foodToRating;       // food -> rating

    
    struct cmp {
        bool operator()(const pair<int, string>& a, const pair<int, string>& b) const {
            if (a.first == b.first) return a.second < b.second;
            return a.first > b.first;
        }
    };

    
    unordered_map<string, set<pair<int, string>, cmp>> cuisineToFoods;

public:
    FoodRatings(vector<string>& foods, vector<string>& cuisines, vector<int>& ratings) {
        int n = foods.size();
        for (int i = 0; i < n; i++) {
            foodToCuisine[foods[i]] = cuisines[i];
            foodToRating[foods[i]] = ratings[i];
            cuisineToFoods[cuisines[i]].insert({ ratings[i], foods[i] });
        }
    }

    void changeRating(string food, int newRating) {
        string cuisine = foodToCuisine[food];
        int oldRating = foodToRating[food];

        
        cuisineToFoods[cuisine].erase({ oldRating, food });

        
        foodToRating[food] = newRating;
        cuisineToFoods[cuisine].insert({ newRating, food });
    }

    string highestRated(string cuisine) {
        return cuisineToFoods[cuisine].begin()->second;
    }
};

