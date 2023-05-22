object Main {


  // ex. 1
  trait MapTrait[A, B] {

    def put(key: A, value: B): Boolean // puts an entry in a collection, and if the same key was already there,
    // the old pair is replaced

    def get(key: A): B // retrieves the entry from map with the given key, but doesn't delete

    def size: Int // return a size of a map, that is number of entries in it

    def remove(key: A): Boolean // removes the entry with the given key, if such one exists

    def contains(key: A): Boolean // checks if a map contains an entry with a given key or not

    def getKeys: Array[A] // retrieves all keys from a map as an array

    def getValues: Array[B] // retrieves all values from a map as an array

    // if a method return Boolean it informs whether a method was successful or not
  }


  // ex. 2
  trait SetTrait[A] {

    def add(element:A):Boolean // adds element to the set

    def remove(element:A):Boolean // removes the element, if such one exists

    def contains(element:A):Boolean // check whether the set contains the element or not

    def size: Int // return the number of elements in the set

    def union(anotherSet: SetTrait[A]):SetTrait[A] // returns the union of this set and anotherSet

    def intersection(anotherSet: SetTrait[A]):SetTrait[A] // returns the intersection of this set and anotherSet

    def difference(anotherSet: SetTrait[A]):SetTrait[A] // returns the difference of this set and anotherSet

    def isSubsetOf(anotherSet: SetTrait[A]):Boolean // check whether this set a subset of anotherSet
    
    // if a method return Boolean it informs whether a method was successful or not
  }


  // ex. 3

  /*

  ArrayList:
    - implements List interface and inherits AbstractList
    - when adding a new element, Java ensures if there is enough space
    - if there is, it simply adds a new element
    - if not, old array will be copied to newly created array
    - newly created array is preferably 2 times larger (if possible, if not then it has +1 to capacity)
    - size, isEmpty, get, set, iterator, listIterator -> constant time
    - add -> considering amortization it's constant time (worst-case, when copying array is needed -> linear time)
    - All of the other operations -> linear time
    - Constant factor is low compared to that for the LinkedList
    - implementation is not synchronized
  LinkedList:
    - is implemented as doubly-linked list, which is a list of linked elements/objects which can be traversed in both ways
    - inherits the List and Deque interfaces
    - adding operation simply appends an element to the end of the list -> O(1) time
    - adding at specific index –> on average O(n) time
    - getting the element or contains operation –> O(n) time
    - removing element or element at specific index -> O(n) time (if we remove only first or last element it's constant time
    - implementation is not synchronized
  Which one is better?
   - in many cases ArrayList is better, but if our program usually works over
    first or last element, LinkedList is the best as it provides constant time for
    get, add, remove operations for first and last element
   - if we use iterator for LinkedList, it provides constant time for Iterator.remove() and ListIterator.add(E element) operations
   - main benefit for ArrayList is get operation, as it's always constant time
   - another benefit for ArrayList is that it takes less space, as LinkedList requires more overhead (pointers to the next and previous element)
  */
}
