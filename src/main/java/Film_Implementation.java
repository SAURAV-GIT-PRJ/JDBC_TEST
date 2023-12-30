import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Film_Implementation implements Film_Service{

    private static Connection con;

    static {
        String url="jdbc:mysql://localhost:3306/jdbc_test";
        String username="root";
        String pass="Saurav@229";

        try {
            con=DriverManager.getConnection(url,username,pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insertFilmDetails(Films f) {
        int n=0;
        String query="insert into film_info(film_nam,film_year,film_lang,film_rating) values(?,?,?,?)";

        try {
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1,f.getFilmName());
            pstmt.setInt(2,f.getFilmYear());
            pstmt.setString(3,f.getFilmLang());
            pstmt.setFloat(4,f.getFilmRating());
           n= pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return n;
    }

    @Override
    public int updateFilmYear(Films f) {
        int n=0;
        String query="update film_info set film_year=? where film_nam=?";

        try {
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setInt(1,f.getFilmYear());
            pstmt.setString(2,f.getFilmName());

           n= pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n;
    }

    @Override
    public List<Films> displayFilmByAvg() {
        List<Films> filmsList=new ArrayList<>();
        String query="select film_nam from film_info where film_rating>(select avg(film_rating) from film_info);";
        try {
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);

            while(rs.next()){
                String filmNam=rs.getString(2);


                Films f=new Films(0,filmNam,0,"",0);
                filmsList.add(f);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return filmsList;
    }

    @Override
    public List<Films> displayFilmsForOscar() {
        List<Films> filmsList=new ArrayList<>();

        String query="select film_nam from film_info where film_rating>4 limit 5";

        try {
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while (rs.next()){
               String filmNames=rs.getString("film_nam");

                Films f=new Films(0,filmNames,0,"",0);
                filmsList.add(f);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return filmsList;
    }
}
