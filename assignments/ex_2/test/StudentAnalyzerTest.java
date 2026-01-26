import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentAnalyzerTest {

    private StudentAnalyzer studentAnalyzer;

    @BeforeEach
    void initAnalyzer() {
        studentAnalyzer = new StudentAnalyzer();
    }

    /*
     * =====================================================
     * TEST CHO HÀM countExcellentStudents()
     * =====================================================
     */

    @Test
    void shouldCountExcellentScores_whenListContainsValidAndInvalidValues() {
        List<Double> inputScores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0);
        int expected = 2;
        int actual = studentAnalyzer.countExcellentStudents(inputScores);
        assertEquals(expected, actual);
    }

    @Test
    void shouldCountExcellentScores_whenAllScoresAreValid() {
        List<Double> inputScores = Arrays.asList(9.5, 8.0, 7.5, 8.5, 6.0);
        assertEquals(3, studentAnalyzer.countExcellentStudents(inputScores));
    }

    @Test
    void shouldReturnZero_whenScoreListIsEmpty() {
        List<Double> inputScores = Collections.emptyList();
        assertEquals(0, studentAnalyzer.countExcellentStudents(inputScores));
    }

    @Test
    void shouldReturnZero_whenScoreListIsNull() {
        assertEquals(0, studentAnalyzer.countExcellentStudents(null));
    }

    @Test
    void shouldIgnoreZeroScores_whenCountingExcellentStudents() {
        List<Double> inputScores = Arrays.asList(0.0, 0.0, 0.0);
        assertEquals(0, studentAnalyzer.countExcellentStudents(inputScores));
    }

    @Test
    void shouldCountAllTensAsExcellent() {
        List<Double> inputScores = Arrays.asList(10.0, 10.0, 10.0);
        assertEquals(3, studentAnalyzer.countExcellentStudents(inputScores));
    }

    @Test
    void shouldTreatEightAsExcellentBoundary() {
        List<Double> inputScores = Arrays.asList(8.0, 7.9, 8.1);
        assertEquals(2, studentAnalyzer.countExcellentStudents(inputScores));
    }

    @Test
    void boundaryTest_justBelowAndAboveEight() {
        List<Double> inputScores = Arrays.asList(7.99, 8.0, 8.01);
        assertEquals(2, studentAnalyzer.countExcellentStudents(inputScores));
    }

    @Test
    void boundaryTest_multipleValuesAroundEight() {
        List<Double> inputScores = Arrays.asList(7.98, 7.99, 8.0, 8.01, 8.02);
        assertEquals(3, studentAnalyzer.countExcellentStudents(inputScores));
    }

    @Test
    void shouldIgnoreInvalidLowScores() {
        List<Double> inputScores = Arrays.asList(-0.01, 0.0, 0.01, 8.5);
        assertEquals(1, studentAnalyzer.countExcellentStudents(inputScores));
    }

    @Test
    void shouldIgnoreInvalidHighScores() {
        List<Double> inputScores = Arrays.asList(9.99, 10.0, 10.01);
        assertEquals(2, studentAnalyzer.countExcellentStudents(inputScores));
    }

    @Test
    void shouldSkipNegativeScores() {
        List<Double> inputScores = Arrays.asList(9.0, -5.0, 8.5, -1.0);
        assertEquals(2, studentAnalyzer.countExcellentStudents(inputScores));
    }

    @Test
    void shouldSkipScoresAboveTen() {
        List<Double> inputScores = Arrays.asList(9.0, 15.0, 8.5, 100.0);
        assertEquals(2, studentAnalyzer.countExcellentStudents(inputScores));
    }

    @Test
    void shouldReturnZero_whenAllScoresAreInvalid() {
        List<Double> inputScores = Arrays.asList(-1.0, 11.0, -5.0, 20.0);
        assertEquals(0, studentAnalyzer.countExcellentStudents(inputScores));
    }

    @Test
    void shouldIgnoreNullValuesInScoreList() {
        List<Double> inputScores = Arrays.asList(9.0, null, 8.5, null);
        assertEquals(2, studentAnalyzer.countExcellentStudents(inputScores));
    }

    /*
     * =====================================================
     * TEST CHO HÀM calculateValidAverage()
     * =====================================================
     */

    @Test
    void shouldCalculateAverageIgnoringInvalidValues() {
        List<Double> inputScores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0);
        assertEquals(8.17, studentAnalyzer.calculateValidAverage(inputScores), 0.01);
    }

    @Test
    void shouldCalculateAverage_whenAllScoresAreValid() {
        List<Double> inputScores = Arrays.asList(10.0, 8.0, 6.0);
        assertEquals(8.0, studentAnalyzer.calculateValidAverage(inputScores), 0.01);
    }

    @Test
    void shouldReturnZeroAverage_whenListIsEmpty() {
        List<Double> inputScores = Collections.emptyList();
        assertEquals(0.0, studentAnalyzer.calculateValidAverage(inputScores), 0.01);
    }

    @Test
    void shouldReturnZeroAverage_whenListIsNull() {
        assertEquals(0.0, studentAnalyzer.calculateValidAverage(null), 0.01);
    }

    @Test
    void shouldHandleAllZeroScoresCorrectly() {
        List<Double> inputScores = Arrays.asList(0.0, 0.0, 0.0);
        assertEquals(0.0, studentAnalyzer.calculateValidAverage(inputScores), 0.01);
    }

    @Test
    void shouldCalculateAverageOfAllTens() {
        List<Double> inputScores = Arrays.asList(10.0, 10.0, 10.0);
        assertEquals(10.0, studentAnalyzer.calculateValidAverage(inputScores), 0.01);
    }

    @Test
    void boundaryTest_zeroAndTen() {
        List<Double> inputScores = Arrays.asList(0.0, 10.0);
        assertEquals(5.0, studentAnalyzer.calculateValidAverage(inputScores), 0.01);
    }

    @Test
    void boundaryTest_nearZeroValues() {
        List<Double> inputScores = Arrays.asList(-0.01, 0.0, 0.01, 5.0);
        assertEquals(1.67, studentAnalyzer.calculateValidAverage(inputScores), 0.01);
    }

    @Test
    void boundaryTest_nearTenValues() {
        List<Double> inputScores = Arrays.asList(9.99, 10.0, 10.01);
        assertEquals(9.995, studentAnalyzer.calculateValidAverage(inputScores), 0.01);
    }

    @Test
    void boundaryTest_multipleEdgesCombined() {
        List<Double> inputScores = Arrays.asList(-0.01, 0.0, 0.01, 9.99, 10.0, 10.01);
        assertEquals(5.0, studentAnalyzer.calculateValidAverage(inputScores), 0.01);
    }

    @Test
    void shouldHandleHighPrecisionFloatingNumbers() {
        List<Double> inputScores = Arrays.asList(7.999999, 8.0, 8.000001);
        assertEquals(8.0, studentAnalyzer.calculateValidAverage(inputScores), 0.000001);
    }

    @Test
    void shouldIgnoreNegativeScoresWhenCalculatingAverage() {
        List<Double> inputScores = Arrays.asList(10.0, -5.0, 8.0, -1.0);
        assertEquals(9.0, studentAnalyzer.calculateValidAverage(inputScores), 0.01);
    }

    @Test
    void shouldIgnoreScoresGreaterThanTenWhenCalculatingAverage() {
        List<Double> inputScores = Arrays.asList(10.0, 15.0, 8.0, 100.0);
        assertEquals(9.0, studentAnalyzer.calculateValidAverage(inputScores), 0.01);
    }

    @Test
    void shouldReturnZeroAverage_whenNoValidScoresExist() {
        List<Double> inputScores = Arrays.asList(-1.0, 11.0, -5.0, 20.0);
        assertEquals(0.0, studentAnalyzer.calculateValidAverage(inputScores), 0.01);
    }

    @Test
    void shouldIgnoreNullValuesWhenCalculatingAverage() {
        List<Double> inputScores = Arrays.asList(10.0, null, 8.0, null);
        assertEquals(9.0, studentAnalyzer.calculateValidAverage(inputScores), 0.01);
    }

    @Test
    void shouldReturnSameValue_whenOnlyOneValidScoreExists() {
        List<Double> inputScores = Arrays.asList(7.5);
        assertEquals(7.5, studentAnalyzer.calculateValidAverage(inputScores), 0.01);
    }
}
