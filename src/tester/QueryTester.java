package tester;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.moviedb.db.DBConnectionManager;

public class QueryTester {

    public static void main(String[] args) throws Exception {

        PreparedStatement statement = DBConnectionManager.getConnection()
                .prepareStatement("SELECT title from MOVIE");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
            System.out.println(resultSet.getString(1));
    }

}
