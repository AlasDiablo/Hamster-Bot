package fr.alasdiablo.hamster.data;

import java.sql.*;

public class Database {

    private static Connection connection = null;

    private static Connection createConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/hydris/Desktop/RoyalHamsterClub/hamster.db");
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
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public static Connection getDatabase() {
        return (connection != null) ? connection : createConnection();
    }

    public static void checkUser(long user) {
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
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
    }

    public static void addVoiceXp(float xp, long user) {
        checkUser(user);
        try {
            PreparedStatement statement = getDatabase().prepareStatement(
                    "UPDATE USERXP SET voice_xp = voice_xp + ? WHERE user_id = ?"
            );
            statement.setFloat(1, xp);
            statement.setLong(2, user);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
    }

    public static void addMessageXp(float xp, long user) {
        checkUser(user);
        try {
            PreparedStatement statement = getDatabase().prepareStatement(
                    "UPDATE USERXP SET message_xp = message_xp + ? WHERE user_id = ?"
            );
            statement.setFloat(1, xp);
            statement.setLong(2, user);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
    }

    public static float getVoiceXp(long user) {
        checkUser(user);
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
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
        return -1f;
    }

    public static float getMessageXp(long user) {
        checkUser(user);
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
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
        return -1f;
    }
}
