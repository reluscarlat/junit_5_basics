import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import org.junit.jupiter.api.Assumptions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class MathUtilsTest {

    MathUtils mathUtils;
    boolean isServerUp = true;
    TestInfo testInfo;
    TestReporter testReporter;


    @BeforeAll
    static void beforeAllInit() {
        System.out.println("Before all init!");
    }

    @AfterAll
    static void afterAllCleanup() {
        System.out.println("After all cleanups!");
    }

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter) {
        mathUtils = new MathUtils();
        System.out.println("Initializing... ");
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags() + ".");
    }

    @AfterEach
    void cleanup() {
        System.out.println("Cleaning up...+\n");
    }

    @Test
    @DisplayName("Test 'add method'")
    @Tag("Add")
    void testAdd() {
        int expected = 2;
        int actual = mathUtils.add(1,1);
        assertEquals(expected,actual,"The add method should add two numbers");
    }

    @Test
    @RepeatedTest(5)
    @Tag("Circle")
    @Tag("Math")
    void testComputeCircleArea() {
        assertEquals(314.1592653589793,
                mathUtils.computeCircleArea(10.0),
                "Should return area.");
    }

//    @Test
//    @RepeatedTest(5)
//    @Tag("Circle")
//    void testComputeCircleArea(RepetitionInfo repetitionInfo) {
//        if(repetitionInfo.getCurrentRepetition() >= 3) {
//            // do something
//            System.out.println("repetitin = " + repetitionInfo.getCurrentRepetition());
//        }
//        assertEquals(314.1592653589793,
//                mathUtils.computeCircleArea(10.0),
//                "Should return area.");
//    }

    @Nested
    @DisplayName("Add Numbers Class")
    @Tag("Add")      // tag is used to rum only methods annotated with specific tag.
    class AddNumbersTest {

        @Test
        @DisplayName("Testing positive numbers addition.")
        void testAddPositive(){
            assertEquals(2,mathUtils.add(1,1),() ->"The add method should add two numbers");
        }

        @Test
        @DisplayName("Testing negative numbers addition.")
        void testAddNegative() {
            assertEquals(-2,mathUtils.add(-1,-1),() -> "The add method should add two numbers");
        }
    }

    @Test
    @DisplayName("Multiply method")
    @Tag("Multiply")
    void testMultiply(TestReporter testReporter, TestInfo testInfo) {
        System.out.println("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags() + ".");
//        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags() + ".");
        assertAll(
                () -> assertEquals(1,mathUtils.multiply(1,1)),
                () -> assertEquals(4,mathUtils.multiply(2,2)),
                () -> assertEquals(6,mathUtils.multiply(2,3)),
                () -> assertEquals(0,mathUtils.multiply(1,0))
        );
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testDivide() {
        assumeTrue(isServerUp);  // if ( isServerUp == false ) then don't run this test.
        assertThrows(ArithmeticException.class,
                () -> mathUtils.divide(1,0));
    }

    @Test
    @DisplayName("Method that should not run.")
    @Disabled
    void testDisabled(){
        fail("This test sould be disabled");
    }
}