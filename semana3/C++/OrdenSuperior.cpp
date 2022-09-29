#include <iostream>
#include <vector>
#include <functional>

using namespace std;

template <typename T>
int count(const vector<T> xs, function<bool(int)> f) {
  int result = 0;
  for (const T &x : xs) {
    if (f(x)) result++;
  }
  return result;
}

class IsEven {
  public:
    bool operator()(int x) { return x % 2 == 0; }
};

class IsPositive {
  public:
    bool operator()(int x) { return x > 0; }
};

int main() {
  vector<int> v = {1, -2, 3, 0, 5, 3};
  
  cout << count(v, IsEven()) << endl;
  cout << count(v, IsPositive()) << endl;
  
  cout << count(v, [](int x) { return x % 2 == 0; }) << endl;
  cout << count(v, [](int x) { return x > 0; }) << endl;

}