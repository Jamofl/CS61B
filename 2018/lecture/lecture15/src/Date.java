import java.util.Objects;

public class Date {

    private final int month;
    private final int day;
    private final int year;


    public Date(int d, int m, int y){
        month = m;
        day = d;
        year = y;
    }


    // have to override the equals method extend from Object
    // including null check, class check and casting
    @Override
    public boolean equals(Object obj) {
        if (this == obj)// the truely same
            return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        else{
            Date date = (Date) obj;
            return this.day == date.day &&
                    this.year == date.year &&
                    this.month == date.month;
        }
    }










    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Date date = (Date) o;
//        return month == date.month &&
//                day == date.day &&
//                year == date.year;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(month, day, year);
//    }
}
