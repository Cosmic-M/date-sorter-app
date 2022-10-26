import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DateSorterImpl implements IDateSorter {
    private static final List<Integer> MONTHS_WITHOUT_R = List.of(5, 6, 7, 8);
    private static final Integer FROM_INDEX_YEAR = 0;
    private static final Integer TO_INDEX_YEAR = 4;
    private static final Integer FROM_INDEX_MONTH = 5;
    private static final Integer TO_INDEX_MONTH = 7;
    private static final Integer FROM_INDEX_DAY = 8;
    private static final Integer TO_INDEX_DAY = 10;

    @Override
    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        return unsortedDates.stream()
                .sorted(getDateComparator())
                .collect(Collectors.toList());
    }

    private Comparator<LocalDate> getDateComparator() {
        return (firstDate, secondDate) -> {
            String firstDateStr = firstDate.toString();
            String secondDateStr = secondDate.toString();
            int firstDateMonth = Integer
                    .parseInt(firstDateStr.substring(FROM_INDEX_MONTH, TO_INDEX_MONTH));
            int secondDateMonth = Integer
                    .parseInt(secondDateStr.substring(FROM_INDEX_MONTH, TO_INDEX_MONTH));
            int yearOfFirstDate;
            int yearOfSecondDate;
            int dayOfFirstDate;
            int dayOfSecondDate;
            if (!MONTHS_WITHOUT_R.contains(firstDateMonth)
                    && MONTHS_WITHOUT_R.contains(secondDateMonth)) {
                return -1;
            } else if (MONTHS_WITHOUT_R.contains(firstDateMonth)
                    && !MONTHS_WITHOUT_R.contains(secondDateMonth)) {
                return 1;
            } else if (MONTHS_WITHOUT_R.contains(firstDateMonth)
                    && MONTHS_WITHOUT_R.contains(secondDateMonth)) {
                yearOfFirstDate = getYearOfDate(firstDateStr);
                yearOfSecondDate = getYearOfDate(secondDateStr);
                if (yearOfFirstDate > yearOfSecondDate) {
                    return -1;
                } else if (yearOfFirstDate < yearOfSecondDate) {
                    return 1;
                }
                if (firstDateMonth == secondDateMonth) {
                    dayOfFirstDate = getDayOfDate(firstDateStr);
                    dayOfSecondDate = getDayOfDate(secondDateStr);
                    return Integer.compare(dayOfSecondDate, dayOfFirstDate);
                }
                return firstDateMonth < secondDateMonth ? 1 : -1;
            } else {
                yearOfFirstDate = getYearOfDate(firstDateStr);
                yearOfSecondDate = getYearOfDate(secondDateStr);
                if (yearOfFirstDate > yearOfSecondDate) {
                    return 1;
                } else if (yearOfFirstDate < yearOfSecondDate) {
                    return -1;
                }
                if (firstDateMonth == secondDateMonth) {
                    dayOfFirstDate = getDayOfDate(firstDateStr);
                    dayOfSecondDate = getDayOfDate(secondDateStr);
                    return Integer.compare(dayOfFirstDate, dayOfSecondDate);
                }
                return firstDateMonth > secondDateMonth ? 1 : -1;
            }
        };
    }

    private int getDayOfDate(String dateAsString) {
        return Integer.parseInt(dateAsString.substring(FROM_INDEX_DAY, TO_INDEX_DAY));
    }

    private int getYearOfDate(String dateAsString) {
        return Integer.parseInt(dateAsString.substring(FROM_INDEX_YEAR, TO_INDEX_YEAR));
    }
}
