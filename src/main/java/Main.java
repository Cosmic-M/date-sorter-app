import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String...args) {
        LocalDate firstDate = LocalDate.of(2005, 7, 1);
        LocalDate secondDate = LocalDate.of(2005, 1, 2);
        LocalDate thirdDate = LocalDate.of(2005, 1, 1);
        LocalDate fourthDate = LocalDate.of(2005, 5, 3);
        List<LocalDate> dates = List.of(firstDate, secondDate, thirdDate, fourthDate);
        IDateSorter dateSorter = new DateSorterImpl();
        Collection<LocalDate> sortedDateList = dateSorter.sortDates(dates);
        sortedDateList.forEach(System.out::println);
    }
}
