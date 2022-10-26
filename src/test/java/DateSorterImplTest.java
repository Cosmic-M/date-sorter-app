import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DateSorterImplTest {
    private static IDateSorter dateSorter;
    private static final LocalDate jan1of2015 = LocalDate.of(2015,1,1);
    private static final LocalDate jan1of2022 = LocalDate.of(2022,1,1);
    private static final LocalDate jan1of2005 = LocalDate.of(2005,1,1);
    private static final LocalDate may1of2015 = LocalDate.of(2015,5,1);
    private static final LocalDate may1of2022 = LocalDate.of(2022,5,1);
    private static final LocalDate may1of2005 = LocalDate.of(2005,5,1);
    private static final LocalDate may10of2022 = LocalDate.of(2022,5,10);
    private static final LocalDate may25of2022 = LocalDate.of(2022,5,25);
    private static final LocalDate jan10of2022 = LocalDate.of(2022,1,10);
    private static final LocalDate jan25of2022 = LocalDate.of(2022,1,25);
    private static final LocalDate oct10of2010 = LocalDate.of(2010,10,10);
    private static final LocalDate dec31of2012 = LocalDate.of(2012,12,31);
    private static final LocalDate jun10of2010 = LocalDate.of(2010,6,10);
    private static final LocalDate jul31of2012 = LocalDate.of(2012,7,31);

    @BeforeAll
    public static void initial() {
        dateSorter = new DateSorterImpl();

    }

    @Test
    public void sortDates_emptyList_ok() {
        List<LocalDate> dates = Collections.emptyList();
        List<LocalDate> result = (List<LocalDate>) dateSorter.sortDates(dates);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void sortDates_repeatableData_ok() {
        List<LocalDate> dates = List.of(jun10of2010, oct10of2010, jun10of2010, oct10of2010);
        List<LocalDate> result = (List<LocalDate>) dateSorter.sortDates(dates);
        Assertions.assertEquals(oct10of2010, result.get(0));
        Assertions.assertEquals(oct10of2010, result.get(1));
        Assertions.assertEquals(jun10of2010, result.get(2));
        Assertions.assertEquals(jun10of2010, result.get(3));
    }

    @Test
    public void sortDates_differentYearsTheSameDaysAndMonthWithR_ok() {
        List<LocalDate> dates = List.of(jan1of2015, jan1of2022, jan1of2005);
        List<LocalDate> result = (List<LocalDate>) dateSorter.sortDates(dates);
        Assertions.assertEquals(jan1of2005, result.get(0));
        Assertions.assertEquals(jan1of2015, result.get(1));
        Assertions.assertEquals(jan1of2022, result.get(2));
    }

    @Test
    public void sortDates_differentYearsTheSameDaysAndMonthWithoutR_ok() {
        List<LocalDate> dates = List.of(may1of2015, may1of2022, may1of2005);
        List<LocalDate> result = (List<LocalDate>) dateSorter.sortDates(dates);
        Assertions.assertEquals(may1of2022, result.get(0));
        Assertions.assertEquals(may1of2015, result.get(1));
        Assertions.assertEquals(may1of2005, result.get(2));
    }

    @Test
    public void sortDates_differentDaysTheSameYearsAndMonthsWithoutR_ok() {
        List<LocalDate> dates = List.of(may10of2022, may1of2022, may25of2022);
        List<LocalDate> result = (List<LocalDate>) dateSorter.sortDates(dates);
        Assertions.assertEquals(may25of2022, result.get(0));
        Assertions.assertEquals(may10of2022, result.get(1));
        Assertions.assertEquals(may1of2022, result.get(2));
    }

    @Test
    public void sortDates_differentDaysTheSameYearsAndMonthsWithR_ok() {
        List<LocalDate> dates = List.of(jan10of2022, jan25of2022, jan1of2022);
        List<LocalDate> result = (List<LocalDate>) dateSorter.sortDates(dates);
        Assertions.assertEquals(jan1of2022, result.get(0));
        Assertions.assertEquals(jan10of2022, result.get(1));
        Assertions.assertEquals(jan25of2022, result.get(2));
    }

    @Test
    public void sortDates_differentDaysYearsAndMonthsWithR_ok() {
        List<LocalDate> dates = List.of(jan1of2022, oct10of2010, dec31of2012);
        List<LocalDate> result = (List<LocalDate>) dateSorter.sortDates(dates);
        Assertions.assertEquals(oct10of2010, result.get(0));
        Assertions.assertEquals(dec31of2012, result.get(1));
        Assertions.assertEquals(jan1of2022, result.get(2));
    }

    @Test
    public void sortDates_differentDaysYearsAndMonthsWithoutR_ok() {
        List<LocalDate> dates = List.of(jun10of2010, jul31of2012, may1of2005);
        List<LocalDate> result = (List<LocalDate>) dateSorter.sortDates(dates);
        Assertions.assertEquals(jul31of2012, result.get(0));
        Assertions.assertEquals(jun10of2010, result.get(1));
        Assertions.assertEquals(may1of2005, result.get(2));
    }

    @Test
    public void sortDates_differentDaysYearsAndMonthsBothCategories_ok() {
        List<LocalDate> dates = List
                .of(may25of2022, jan1of2015, jul31of2012, dec31of2012, jun10of2010, oct10of2010);
        List<LocalDate> result = (List<LocalDate>) dateSorter.sortDates(dates);
        Assertions.assertEquals(oct10of2010, result.get(0));
        Assertions.assertEquals(dec31of2012, result.get(1));
        Assertions.assertEquals(jan1of2015, result.get(2));
        Assertions.assertEquals(may25of2022, result.get(3));
        Assertions.assertEquals(jul31of2012, result.get(4));
        Assertions.assertEquals(jun10of2010, result.get(5));
    }
}
