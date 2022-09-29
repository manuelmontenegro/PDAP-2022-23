

def suma(x, y):
  return x + y
  
print(suma(4, 5))
print(suma.__call__(4, 5))

def count(xs, f):
  result = 0
  for x in xs:
    if f(x):
      result += 1
  return result
  
def isEven(x):
    return x % 2 == 0
    
def isPositive(x):
    return x > 0
    
xs = [1, 5, -2, 1, 4, 2]
print(count(xs, isEven))
print(count(xs, isPositive))
    
class MiClase:
  def __call__(self, x):
    return x % 2 == 0

print("PARES:")    
print(count(xs, MiClase()))

print("LAMBDA:")
print(count(xs, lambda x: x % 2 == 0))
print(count(xs, lambda x: x > 0))
