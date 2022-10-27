import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DateSorterImpl implements IDateSorter {
    private static final List<Integer> MONTHS_WITH_R = List.of(1, 2, 3, 4, 9, 10, 11, 12);
    private static final int NATURAL_ORDER = 1;
    private static final int REVERSE_ORDER = -1;

    @Override
    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        return unsortedDates.stream()
                .sorted(getDateComparator())
                .collect(Collectors.toList());
    }

    private Comparator<LocalDate> getDateComparator() {
        return (firstDate, secondDate) -> {
            int monthOfFirstDate = firstDate.getMonthValue();
            int monthOfSecondDate = secondDate.getMonthValue();
            if (MONTHS_WITH_R.contains(monthOfFirstDate)) {
                if (!MONTHS_WITH_R.contains(monthOfSecondDate)) {
                    return -1;
                } else {
                    return compareTwoDates(monthOfFirstDate, monthOfSecondDate,
                            firstDate, secondDate, NATURAL_ORDER);
                }
            } else {
                if (MONTHS_WITH_R.contains(monthOfSecondDate)) {
                    return 1;
                } else {
                    return compareTwoDates(monthOfFirstDate, monthOfSecondDate,
                            firstDate, secondDate, REVERSE_ORDER);
                }
            }
        };
    }

    private int compareTwoDates(int monthOfFirstDate, int monthOfSecondDate,
                           LocalDate firstDate, LocalDate secondDate, int order) {
        int yearOfFirstDate = firstDate.getYear();
        int yearOfSecondDate = secondDate.getYear();
        if (yearOfFirstDate > yearOfSecondDate) {
            return order;
        } else if (yearOfFirstDate < yearOfSecondDate) {
            return -1 * order;
        }
        if (monthOfFirstDate == monthOfSecondDate) {
            int dayOfFirstDate = firstDate.getDayOfMonth();
            int dayOfSecondDate = secondDate.getDayOfMonth();
            return Integer.compare(dayOfFirstDate, dayOfSecondDate) * order;
        }
        return monthOfFirstDate < monthOfSecondDate ? -1 * order : order;
    }
}
