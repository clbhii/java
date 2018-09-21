class StudentTest2 extends GroovyTestCase {
   void testDisplay() {
      def stud = new Student(name : 'Joe', ID : '1')
      def expected = 'Joe2'
	  println(expected);
      assertToString(stud.name, expected)
   }
   
}

