package fr.alasdiablo.hamster.data;

import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    private static Connection connection = null;

    private static Connection createConnection() {
        try {
            //connection = DriverManager.getConnection("jdbc:sqlite:/home/hydris/Desktop/RoyalHamsterClub/hamster.db");
            connection = DriverManager.getConnection("jdbc:sqlite:hamster.db");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            System.exit(1);
        }
        try {
            PreparedStatement userXpTableCreation = connection.prepareStatement(
            "create table if not exists USERXP (" +
                "user_id int," +
                "voice_xp float," +
                "message_xp float" +
                ")"
            );
            userXpTableCreation.execute();
            userXpTableCreation.close();

            PreparedStatement userOfTheDayTableCreation = connection.prepareStatement(
            "create table if not exists USERLEVEL (" +
                "user_id int," +
                "user_level int" +
                ")"
            );
            userOfTheDayTableCreation.execute();
            userOfTheDayTableCreation.close();


        } catch (SQLException throwable) {
            throwable.printStackTrace();
            System.exit(1);
        }

        return connection;
    }

    public static Connection getDatabase() {
        return (connection != null) ? connection : createConnection();
    }

    public static class LevelStats {
        private static void checkUser(long user) {
            try {
                PreparedStatement check = getDatabase().prepareStatement(
                        "SELECT * FROM USERLEVEL WHERE user_id = ?"
                );
                check.setLong(1, user);
                ResultSet resultSet = check.executeQuery();
                if (!resultSet.next()) {
                    PreparedStatement setIn = getDatabase().prepareStatement(
                            "INSERT INTO USERLEVEL VALUES (?, 0)"
                    );
                    setIn.setLong(1, user);
                    setIn.executeUpdate();
                    setIn.close();
                }
                check.close();
            } catch (SQLException ignored) {}
        }

        public static void setLevel(int level, long user) {
            LevelStats.checkUser(user);
            try {
                PreparedStatement statement = getDatabase().prepareStatement(
                        "UPDATE USERLEVEL SET user_level = ? WHERE user_id = ?"
                );
                statement.setFloat(1, level);
                statement.setLong(2, user);
                statement.executeUpdate();
                statement.close();
            } catch (SQLException ignored) {}
        }

        public static int getLevel(long user) {
            LevelStats.checkUser(user);
            try {
                PreparedStatement statement = getDatabase().prepareStatement(
                        "SELECT user_level FROM USERLEVEL WHERE user_id = ?"
                );
                statement.setLong(1, user);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int result = resultSet.getInt("user_level");
                statement.close();
                return result;
            } catch (SQLException ignored) {}
            return -1;
        }
    }

    public static class GlobalStats {
        private static void checkUser(long user) {
            try {
                PreparedStatement check = getDatabase().prepareStatement(
                        "SELECT * FROM USERXP WHERE user_id = ?"
                );
                check.setLong(1, user);
                ResultSet resultSet = check.executeQuery();
                if (!resultSet.next()) {
                    PreparedStatement setIn = getDatabase().prepareStatement(
                            "INSERT INTO USERXP VALUES (?, 0, 0)"
                    );
                    setIn.setLong(1, user);
                    setIn.executeUpdate();
                    setIn.close();
                }
                check.close();
            } catch (SQLException ignored) {}
        }

        public static List<Pair<Long, Float>> getVoiceClassement() {
            try {
                PreparedStatement statement = getDatabase().prepareStatement(
                        "SELECT user_id, voice_xp FROM USERXP ORDER BY voice_xp DESC"
                );
                ResultSet resultSet = statement.executeQuery();
                List<Pair<Long, Float>> out = new ArrayList<>();
                while (resultSet.next()) {
                    long userId = resultSet.getLong("user_id");
                    float xp = resultSet.getFloat("voice_xp");
                    out.add(new Pair<>(userId, xp));
                }
                statement.close();
                return out;
            } catch (SQLException ignored) {}
            return null;
        }

        public static List<Pair<Long, Float>> getMessageClassement() {
            try {
                PreparedStatement statement = getDatabase().prepareStatement(
                        "SELECT user_id, message_xp FROM USERXP ORDER BY message_xp DESC"
                );
                ResultSet resultSet = statement.executeQuery();
                List<Pair<Long, Float>> out = new ArrayList<>();
                while (resultSet.next()) {
                    long userId = resultSet.getLong("user_id");
                    float xp = resultSet.getFloat("message_xp");
                    out.add(new Pair<>(userId, xp));
                }
                statement.close();
                return out;
            } catch (SQLException ignored) {}
            return null;
        }

        public static void addVoiceXp(float xp, long user) {
            GlobalStats.checkUser(user);
            try {
                PreparedStatement statement = getDatabase().prepareStatement(
                        "UPDATE USERXP SET voice_xp = voice_xp + ? WHERE user_id = ?"
                );
                statement.setFloat(1, xp);
                statement.setLong(2, user);
                statement.executeUpdate();
                statement.close();
            } catch (SQLException ignored) {}
        }

        public static void addMessageXp(float xp, long user) {
            GlobalStats.checkUser(user);
            try {
                PreparedStatement statement = getDatabase().prepareStatement(
                        "UPDATE USERXP SET message_xp = message_xp + ? WHERE user_id = ?"
                );
                statement.setFloat(1, xp);
                statement.setLong(2, user);
                statement.executeUpdate();
                statement.close();
            } catch (SQLException ignored) {}
        }

        public static float getVoiceXp(long user) {
            GlobalStats.checkUser(user);
            try {
                PreparedStatement statement = getDatabase().prepareStatement(
                        "SELECT voice_xp FROM USERXP WHERE user_id = ?"
                );
                statement.setLong(1, user);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                float result = resultSet.getFloat("voice_xp");
                statement.close();
                return result;
            } catch (SQLException ignored) {}
            return -1f;
        }

        public static float getMessageXp(long user) {
            GlobalStats.checkUser(user);
            try {
                PreparedStatement statement = getDatabase().prepareStatement(
                        "SELECT message_xp FROM USERXP WHERE user_id = ?"
                );
                statement.setLong(1, user);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                float result = resultSet.getFloat("message_xp");
                statement.close();
                return result;
            } catch (SQLException ignored) {}
            return -1f;
        }
    }
}
