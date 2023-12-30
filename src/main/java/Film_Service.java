import java.util.List;

public interface Film_Service {
    int insertFilmDetails(Films f);

    int updateFilmYear(Films f);

    List<Films> displayFilmByAvg();

    List<Films> displayFilmsForOscar();
}
