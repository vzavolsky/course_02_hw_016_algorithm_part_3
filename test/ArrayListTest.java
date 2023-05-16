import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

public class ArrayListTest {

    private final ArrayList arrayList = new ArrayList();

    @BeforeEach
    public void beforeEach() {
        arrayList.add(4);
        arrayList.add(3);
        arrayList.add(5);
        arrayList.add(11);
        arrayList.add(0);
        arrayList.add(-1);
    }

    @Test
    public void addByIndexTest() {
        arrayList.add(3, 8);
        ArrayList actual = arrayList;
        ArrayList expected = new ArrayList(new Integer[]{4, 3, 5, 8, 11, 0, -1});
        Assertions.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void addByIndexTestNegative() {
        Assertions.assertThrows(ArrayListException.class, () -> arrayList.add(45, 5));
    }

    @Test
    public void setTest() {
        arrayList.set(3, 8);
        ArrayList actual = arrayList;
        ArrayList expected = new ArrayList(new Integer[]{4, 3, 5, 8, 0, -1});
        Assertions.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void setTestNegative() {
        Assertions.assertThrows(ArrayListException.class, () -> arrayList.set(45, 5));
    }

    @Test
    public void removeByIndexTest() {
        arrayList.remove(3);
        ArrayList actual = arrayList;
        ArrayList expected = new ArrayList(new Integer[]{4, 3, 5, 0, -1});
        Assertions.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void removeByIndexTestNegative() {
        Assertions.assertThrows(ArrayListException.class, () -> arrayList.remove(45));
    }

    @Test
    public void removeByItemTest() {
        Integer value = 4;
        arrayList.remove(value);
        ArrayList actual = arrayList;
        ArrayList expected = new ArrayList(new Integer[]{3, 5, 11, 0, -1});
        Assertions.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void removeByItemTestNegative() {
        Integer value = 22;
        Assertions.assertThrows(ArrayListException.class, () -> arrayList.remove(value));
    }

    public static Stream<Arguments> containsTestParams() {
        return Stream.of(
                Arguments.of(11, true),
                Arguments.of(12, false)
        );
    }

    @ParameterizedTest
    @MethodSource("containsTestParams")
    public void containsTest(int value, boolean expected) {
        boolean actual = arrayList.contains(value);
        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> indexOfTestParams() {
        return Stream.of(
                Arguments.of(11, 3),
                Arguments.of(12, -1)
        );
    }

    @ParameterizedTest
    @MethodSource("indexOfTestParams")
    public void indexOfTest(int value, int expected) {
        int actual = arrayList.indexOf(value);
        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> lastIndexOfTestParams() {
        return Stream.of(
                Arguments.of(11, 2),
                Arguments.of(12, -1)
        );
    }

    @ParameterizedTest
    @MethodSource("lastIndexOfTestParams")
    public void lastIndexOfTest(int value, int expected) {
        int actual = arrayList.lastIndexOf(value);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getTest() {
        Assertions.assertEquals(arrayList.get(3), 11);
    }

    @Test
    public void getTestNegative() {
        Assertions.assertThrows(ArrayListException.class, () -> arrayList.get(45));
    }

    public static Stream<Arguments> equalsTestParams() {
        return Stream.of(
                Arguments.of(new ArrayList(new Integer[]{4, 3, 5, 11, 0, -1}), true),
                Arguments.of(new ArrayList(new Integer[]{4, 3, 5, 11, 0, -3}), false)
        );
    }

    @ParameterizedTest
    @MethodSource("equalsTestParams")
    public void equalsTest(ArrayList list, boolean res) {
        Assertions.assertEquals(arrayList.equals(list), res);
    }

    @Test
    public void equalsTestNegative() {
        Assertions.assertThrows(
                ArrayListException.class,
                () -> arrayList.equals(null)
        );
    }

    public static Stream<Arguments> isEmptyTestParams() {
        return Stream.of(
                Arguments.of(new ArrayList(), true),
                Arguments.of(new ArrayList(new Integer[]{4, 3, 5, 11, 0, -3}), false)
        );
    }

    @ParameterizedTest
    @MethodSource("isEmptyTestParams")
    public void isEmptyTest(ArrayList list, boolean res) {
        Assertions.assertEquals(list.isEmpty(), res);
    }

    public static Stream<Arguments> clearTestParams() {
        return Stream.of(
                Arguments.of(new ArrayList(new Integer[]{null, null, null, null, null, null}), true),
                Arguments.of(new ArrayList(new Integer[]{4, 3, 5, 11, 0, -3}), false)
        );
    }

    @ParameterizedTest
    @MethodSource("clearTestParams")
    public void clearTest(ArrayList list, boolean expected) {
        arrayList.clear();
        boolean actual = list.equals(arrayList);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void toArrayTest() {
        ArrayList newArray = new ArrayList(new Integer[]{4, 3, 5, 11, 0, -1});
        Assertions.assertEquals(arrayList.toArray().toString(", "), newArray.toArray().toString(", "));
    }

    @Test
    public void checkNullItemTestNegative() {
        Assertions.assertThrows(ArrayListException.class, () -> arrayList.add(null));
    }

    @Test
    public void lengthTest() {
        int expected = 8;
        int actual = arrayList.length();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void sortInsertion() {
        ArrayList newArray = new ArrayList(new Integer[]{-1, 0, 3, 4, 5, 11});
        Assertions.assertEquals(newArray.toString(), arrayList.sortInsertion().toString());
    }

    public static Stream<Arguments> containsBinaryTestParams() {
        return Stream.of(
                Arguments.of(3, true),
                Arguments.of(11, true),
                Arguments.of(12, false)
        );
    }

    /*@ParameterizedTest
    @MethodSource("containsBinaryTestParams")
    public void containsBinaryTest(Integer value, boolean expected) {
        Assertions.assertEquals(expected, arrayList.sortInsertion().containsBinary(value));
    }*/
}
