import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.sql.*;
public class UserInput {
    public static void main(String[] args) throws SQLException {
        UserInput ui = new UserInput();
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
                    Scanner sc = new Scanner(System.in);
                    ui.printMenu();
                    int choice = sc.nextInt();
                    ui.decidechoices(choice, connection);
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

    void printMenu() {
        System.out.println("Please enter the digit of operation you wanna perform");
        System.out.println("1. Add a branch");
        System.out.println("2. Add a banker");
        System.out.println("3. Add a Customer");
        System.out.println("4. Add a Loan");
        System.out.println("5. Add an Account");
        System.out.println("6. Register a Payment");
        System.out.println("7. Register a Transaction");
    }

    void decidechoices(int choice, Connection conn) throws SQLException {
        switch (choice) {
            case 1:
                addBranch(conn);
                break;
            case 2:
                addBanker(conn);
                break;
            case 3:
                addCustomer(conn);
                break;
            case 4:
                addLoan(conn);
                break;
            case 5:
                addAccount(conn);
                break;
            case 6:
                registerPayment(conn);
                break;
            case 7:
                registerTransaction(conn);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    void addBranch(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the branch name");
        String branchName = sc.nextLine();
        System.out.println("Enter the branch city");
        String branchCity = sc.nextLine();
        System.out.println("Enter the branch assets");
        int branchAssets = sc.nextInt();

        String query = "INSERT INTO Branches VALUES(?,?,?)";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString(1, branchName);
        preparedStmt.setString(2, branchCity);
        preparedStmt.setInt(3, branchAssets);
        preparedStmt.execute();
        System.out.println("Branch added successfully");
    }

    void addBanker(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the banker details");
        System.out.println("Enter the banker ssn");
        String bankerSsn = sc.nextLine();
        System.out.println("Enter the banker name");
        String bankerName = sc.nextLine();
        System.out.println("Enter the banker phone");
        String bankerPhone = sc.nextLine();
        System.out.println("Enter the banker depedents");
        String bankerDependents = sc.nextLine();
        System.out.println("Enter the banker managerId");
        String bankerManagerId = sc.nextLine();
        System.out.println("Enter the banker employment Type");
        String employmentType = sc.nextLine();

        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date_today = dateObj.format(formatter);
        Statement statement = connection.createStatement();
        String query;
        if (bankerManagerId.equals(""))
            query = "INSERT INTO Bankers VALUES('" + bankerSsn + "','" + bankerName + "','" + bankerPhone + "','" + bankerDependents + "',NULL," + "to_date('"+date_today + "', 'yyyy/MM/dd')" + ",'" + employmentType + "')";
        else
            query = "INSERT INTO Bankers VALUES('" + bankerSsn + "','" + bankerName + "','" + bankerPhone + "','" + bankerDependents + "','" + bankerManagerId + "'," + "to_date('"+date_today + "', 'yyyy/MM/dd')" + ",'" + employmentType + "')";
        statement.executeUpdate(query);
        System.out.println("Banker added successfully");
        statement.close();
    }

    void addCustomer(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the customer details");
        System.out.println("Enter the customer ssn");
        String customerSsn = sc.nextLine();
        System.out.println("Enter the customer name");
        String customerName = sc.nextLine();
        System.out.println("Enter the customer street");
        String customerStreet = sc.nextLine();
        System.out.println("Enter the customer city");
        String customerCity = sc.nextLine();
        System.out.println("Enter the banker Id");
        String bankerId = sc.nextLine();

        Statement statement = connection.createStatement();
        String query = "SELECT * FROM Bankers WHERE ssn= '" + bankerId + "'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            String query2 = "INSERT INTO Customers VALUES(?,?,?,?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query2);
            preparedStmt.setString(1, customerSsn);
            preparedStmt.setString(2, customerName);
            preparedStmt.setString(3, customerStreet);
            preparedStmt.setString(4, customerCity);
            preparedStmt.setString(5, bankerId);
            preparedStmt.execute();
            System.out.println("Customer added successfully");
        } else {
            System.out.println("Banker does not exist");
        }
    }

    void addLoan(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the loan details");
        System.out.println("Enter the customer ssn");
        String customerSsn = sc.nextLine();
        System.out.println("Enter the Branch Name");
        String branchName = sc.nextLine();
        System.out.println("Enter the loan amount");
        int loanAmount = sc.nextInt();

        Statement statement = connection.createStatement();
        String query = "SELECT * FROM Customers WHERE ssn= '" + customerSsn + "'";
        ResultSet rs = statement.executeQuery(query);

        if (rs.next()) {
            String query2 = "INSERT INTO Loans ( customerId, branchName, loanAmount) VALUES('"+customerSsn+"','"+branchName+"',"+loanAmount+")";
            statement.executeUpdate(query2);
            System.out.println(query2);
            System.out.println("Loan added successfully");
            statement.close();
        } else {
            System.out.println("Customer does not exist");
        }
    }

    void addAccount(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the account details");
        System.out.println("Enter the account Type");
        String accountType = sc.nextLine();
        System.out.println("Enter the account balance");
        int accountBalance = sc.nextInt();
        System.out.println("Enter the interest rate");
        int interestRate = sc.nextInt();
        System.out.println("Enter the overdraft limit");
        int overdraftLimit = sc.nextInt();
        System.out.println("Enter the customer ssn");
        String customerSsn = sc.nextLine();


        Statement statement = connection.createStatement();
        String query = "SELECT * FROM Customers WHERE ssn= '" + customerSsn + "'";
        ResultSet rs = statement.executeQuery(query);

        if (rs.next()) {
            String query2 = "INSERT INTO Accounts VALUES(?,?,?,?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, accountType);
            preparedStmt.setInt(2, accountBalance);
            preparedStmt.setInt(3, interestRate);
            preparedStmt.setInt(4, overdraftLimit);
            preparedStmt.setString(5, customerSsn);
            preparedStmt.execute();

            ResultSet rs2 = preparedStmt.getGeneratedKeys();
            if (rs2.next()) {
                int accountId = rs2.getInt(1);
                System.out.println("Account added successfully");
                System.out.println("Account Id: " + accountId);
                String query3 = "INSERT INTO AccountOwners VALUES(?,?)";
                PreparedStatement preparedStmt2 = connection.prepareStatement(query3);
                preparedStmt2.setInt(1, accountId);
                preparedStmt2.setString(2, customerSsn);
            }
        } else {
            System.out.println("Customer does not exist");
        }

    }

    void registerPayment(Connection connection) throws SQLException {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date_today = dateObj.format(formatter);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the payment details");
        System.out.println("Enter the loan Id");
        int loanId = sc.nextInt();
        System.out.println("Enter the amount");
        int amount = sc.nextInt();

        Statement statement = connection.createStatement();
        String query = "SELECT * FROM Loans WHERE loanId= '" + loanId + "'";
        ResultSet rs = statement.executeQuery(query);

        if (rs.next()) {
            Statement statement2 = connection.createStatement();
//            String query2 = "INSERT INTO Payments VALUES('" + loanId + "','" + amount + "','" + "to_date('"+date_today + "', 'yyyy/MM/dd')" + "')";
            String query2 = "INSERT INTO Payments (loanId, paymentAmount, payment_date) VALUES ('"+loanId+"','"+ amount+"',"+" to_date('"+date_today+"', 'yyyy-MM-dd'))";
            statement2.executeUpdate(query2);
            System.out.println("Payment added successfully");
        } else {
            System.out.println("Loan does not exist");
        }

    }

    void registerTransaction(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date_today = dateObj.format(formatter);
        System.out.println("Enter the transaction details");
        System.out.println("Enter the account Id");
        System.out.println("Enter the account Number");
        int accountNo = sc.nextInt();
        System.out.println("Enter the Transaction Type");
        String transactionType = sc.next();
        System.out.println("Enter the amount");
        int amount = sc.nextInt();

        Statement statement = connection.createStatement();
        String query = "INSERT INTO Transactions (accountNo, transactionType, transactionAmount, transaction_date) VALUES ('"+accountNo+"','"+ transactionType+"',"+amount+","+" to_date('"+date_today+"', 'yyyy-MM-dd'))";
        System.out.println(query);
        statement.executeUpdate(query);
        System.out.println("Transaction added successfully");

        statement.close();
    }
}
