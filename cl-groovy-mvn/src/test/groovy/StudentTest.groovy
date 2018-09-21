class StudentTest extends GroovyTestCase {
   void testDisplay() {
      def stud = new Student(name : 'Joe', ID : '1')
      def expected = 'Joe'
	  println(expected);
      assertToString(stud.name, expected)
   }
   
}

