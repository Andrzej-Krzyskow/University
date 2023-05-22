def plus(x:Int, y:Int) = x+y
plus(3,4)
def plus2(x:Int)(y:Int) = x*y
plus2(3)(3)

def addCurring (x: Int)(y:Int)=x+y
val add7to = addCurring(7)_
addCurring(2)(3)
add7to(2)
def add(x: Int, y:Int, z:Int)= x+y*z
val add7 = (add _).curried(2)(3)(3)

add7





