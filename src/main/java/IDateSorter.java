import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface IDateSorter {
    Collection<LocalDate> sortDates(List<LocalDate> unsortedDates);
}
