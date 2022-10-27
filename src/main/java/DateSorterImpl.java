import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class DateSorterImpl implements IDateSorter {
    private static final List<Integer> MONTHS_WITH_R = List.of(1, 2, 3, 4, 9, 10, 11, 12);

    @Override
    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        List<LocalDate> listWithR = new java.util.ArrayList<>(unsortedDates.stream()
                .filter(d -> MONTHS_WITH_R.contains(d.getMonthValue()))
                .sorted(Comparator.naturalOrder())
                .toList());
        List<LocalDate> listWithoutR = unsortedDates.stream()
                .filter(d -> !MONTHS_WITH_R.contains(d.getMonthValue()))
                .sorted(Comparator.reverseOrder())
                .toList();
        return Stream.concat(listWithR.stream(), listWithoutR.stream())
                .toList();
    }
}
