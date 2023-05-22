import scala.collection.mutable

object Main extends App{

  // ex. 2
  def copyList[A](arraySeq: mutable.ArraySeq[A]): mutable.ArraySeq[A] = {
    arraySeq.map(identity)
  }

  var arr = mutable.ArraySeq(1,2,3,4)
  var copy = copyList(arr)
  println(s"copy: ${copyList(copy)}")
  arr = mutable.ArraySeq()
  println(s"arr: $arr")
  println(s"copy: $copy")


}
/*

    ex. 1
        Let's [A] be a supertype of [B] and [C], and consider the given code to be valid.
        If we create a Sequence with a type parameter [A] and assign to it a Sequence
        with [B] type i.e.
                val sequenceOfB: Sequence[A] = new Sequence [B]
       then in theory we should be able to append to that Sequence another sequence of type [A].
       Hence, we should be able to append to "sequenceOfB" a Sequence of type [C], which in fact is
       also of type [A]. However, after this operation our "sequenceOfB", which was of type [B], would
       have to be simultaneously of type [B] and [C]. That would break type guarantees.

       A solution to that is to take as a parameter and return only a Sequence of type [A] which is the supertype
       of both [B] and [C]. Thus, the following code would be valid, as [B>:A] indicates that we take a supertype of [A]:

            abstract class Sequence[+A] {
                def append[B>:A](x: Sequence[B]):Sequence[B]
            }


    ex. 3
        Arrays in Java are covariant. Thus, if [B] is a subtype of [A], then 'B[]' is also a subtype of 'A[]'.
        However, in Java we can have a run-time exception, ArrayStoreException, if:

        class Person{}
        class Student extends Person{}

        Student[] students = new Student[1]
        students[0] = new Student()
        Person[] peopleStudents = students <--- there is no compilation error
        peopleStudents[0] = new Person() <---- that will cause 'ArrayStoreException', as 'peopleStudents' is an array of Students

    ex. 4
        Collections are invariant in Java, because we do not want to have the above problem.
        Hence, the following code won't compile:

        class Person{}
        class Student extends Person{}
        List<Student> studentsList = new ArrayList<Student>();
        List<Person> personStudentList = studentsList;  <---- compilation error: Required type: List<Person> Provided: List<Student>

     */