import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.*;
public class ExecuteQueries {
    public static void main(String[] args) throws SQLException {
        ExecuteQueries eq = new ExecuteQueries();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;
        }
        System.out.println("Oracle JDBC Driver Registered!");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@acaddbprod.uta.edu:1523/pcse1p.data.uta.edu",
                    "axk3905", "G3radTs1fdr7d");

            if (connection != null) {
                try {
                    eq.addNewCustomer(connection);
                    eq.checkMultipleLoans(connection);
                    eq.addManager(connection);
                    eq.executeLoanTransaction(connection);
                    eq.addSavingsAccount(connection, "512-92-9137");
                    eq.addNewBranch(connection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        connection.close();
    }

    void addNewCustomer(Connection connection) throws SQLException {
        int savings = 100000;
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date_today = dateObj.format(formatter);
        Statement statement = connection.createStatement();
        String query = "INSERT INTO Customers(ssn, name, street, city, bankerId) VALUES" +
                "('512-92-9159', 'Random Person', '123 Main St', 'Anytown', '391-61-1149')";
        System.out.println(query);
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.close();
        System.out.println("Customer added successfully");
        String query2 = "INSERT INTO Accounts (accountNo, accountType, accountBalance, interestRate, overdraft)"
                + "VALUES (6, 'Savings', " + savings + ", 0.01, 0)";
        statement.executeUpdate(query2);
        ResultSet resultSet2 = statement.executeQuery(query2);
        resultSet2.close();
        String query3 = "INSERT INTO Transactions (accountNo, transactionType, transactionAmount, transaction_date) "+
                "VALUES (6, 'Deposit', '1000', "+"to_date('"+date_today + "', 'yyyy/MM/dd')"+")";
        ResultSet resultSet3 = statement.executeQuery(query3);
        resultSet3.close();
        String query4 = "UPDATE Accounts SET accountBalance = accountBalance + 1000 WHERE accountNo = 6";
        ResultSet resultSet4 = statement.executeQuery(query4);
        resultSet4.close();
        String query5 = "INSERT INTO Transactions (accountNo, transactionType, transactionAmount, transaction_date) "
                + "VALUES (6, 'Withdrawal', '1000', "+ "to_date('"+date_today + "', 'yyyy-MM-dd')" + ")";
        ResultSet resultSet5 = statement.executeQuery(query5);
        resultSet5.close();
        String query6 = "UPDATE Accounts SET accountBalance = accountBalance - 1000 WHERE accountNo = 6";
        ResultSet resultSet6 = statement.executeQuery(query6);
        resultSet6.close();
        statement.close();
        statement.close();
    }

    void checkMultipleLoans(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT Customers.ssn, Customers.name, COUNT(*) FROM Customers JOIN LoanOwners ON Customers.ssn = LoanOwners.customerId GROUP BY Customers.ssn, Customers.name HAVING COUNT(*) > 1";
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("Customer ssn, name, number of loans");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3));
        }
        resultSet.close();
        statement.close();
    }

    void addManager(Connection connection) throws SQLException {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date_today = dateObj.format(formatter);
        Statement statement = connection.createStatement();
        String query = "INSERT INTO Bankers(ssn, name, phone, dependents, managerId, startDate, employmentType) VALUES" +
                "('512-92-9160', 'Random Manager', '123 Main St', 'Anytown', NULL, "+ "to_date('"+date_today + "', 'yyyy-MM-dd')" +", 'Manager')";
        System.out.println(query);
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.close();
        System.out.println("Manager added successfully");
        statement.close();
    }

    void executeLoanTransaction(Connection connection) throws SQLException {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date_today = dateObj.format(formatter);
        Statement statement = connection.createStatement();
        String query = "INSERT INTO Payments (loanId, paymentAmount, payment_date) " +
                "VALUES (1, 1000,"+" to_date('"+date_today+"', 'yyyy-MM-dd'))";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.close();
        String query2 = "UPDATE Loans SET loanAmount = loanAmount - 1000 WHERE loanId = 1";
        ResultSet resultSet2 = statement.executeQuery(query2);
        resultSet2.close();
        statement.close();
        System.out.println("Loan payment added successfully");
    }

    void addSavingsAccount(Connection connection, String customerId) throws SQLException {
        String key[] = {"accountNo"};
        Statement statement = connection.createStatement();
        String query = "INSERT INTO Accounts (accountType, accountBalance, interestRate, overdraft)"
                + "VALUES ('Savings', '100000', '0.1', '0')";
        PreparedStatement ps = connection.prepareStatement(query, key);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            int accountNo = rs.getInt(1);
            String query2 = "INSERT INTO AccountOwners (accountNo, customerId) VALUES (" + accountNo + ", '" + customerId + "')";
            ResultSet resultSet = statement. executeQuery(query2);
            resultSet.close();
            statement.close();
            System.out.println("Savings account added successfully");
        }
        rs.close();
        ps.close();
        statement.close();
        System.out.println("Savings account added successfully");
    }

    void addNewBranch(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "INSERT INTO Branches (name, city, assets) VALUES ('SBI-Kochi', 'Kochi', '100000000')";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.close();
        statement.close();
        System.out.println("Branch added successfully");
    }
















}
