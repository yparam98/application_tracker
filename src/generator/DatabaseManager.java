package generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager
{
    private Connection mariadb_connection;
    private ArrayList<Application> applications = new ArrayList<>();
    private ArrayList<Company> companies = new ArrayList<>();
    private ArrayList<Candidate> candidates = new ArrayList<>();
    private ArrayList<Contact> contact_informations = new ArrayList<>();
    private ArrayList<Address> addresses = new ArrayList<>();

    public DatabaseManager()
    {
        try
        {
            this.mariadb_connection = DriverManager.getConnection("jdbc:mysql://localhost/careers", "yathavan", "password");
            initialize_db();
        } catch (SQLException exception)
        {
            System.out.println(exception.getLocalizedMessage());
            System.exit(1);
        }
    }

    public Application[] get_applications() throws SQLException
    {
        ArrayList<Application> applications = new ArrayList<>();

        Statement application_cursor = this.mariadb_connection.createStatement();
        ResultSet resultSet = application_cursor.executeQuery("select * from application");


        // convert ArrayList<Application> to a fixed array of Application, return that instead. Easier on memory.
        Application[] applications_array = new Application[applications.size()];
        applications_array = applications.toArray(applications_array);
        return applications_array;
    }

    private void initialize_db() throws SQLException
    {
        Statement initial_query = this.mariadb_connection.createStatement();
        if (!initial_query.executeQuery("show tables").next()) // if db has no tables
        {
            // create tables
            initial_query.executeQuery(get_query_from_file("create_tables.sql"));
        }
        else
        { // populate the arrays
            populateEverything();
        }
    }

    private void populateEverything() throws SQLException
    {
        Statement querier = this.mariadb_connection.createStatement();
        ResultSet application_results = querier.executeQuery("select * from application");
        ResultSet candidate_results = querier.executeQuery("select * from candidate");
        ResultSet company_results = querier.executeQuery("select * from company");
        ResultSet contact_results = querier.executeQuery("select * from contact");
        ResultSet address_results = querier.executeQuery("select * from address");

        // order of priority: address -> contact -> candidate -> company -> application

        String holder = "";

        while ((holder = address_results.))
    }

    private String get_query_from_file(String file_name)
    {
        StringBuilder query = new StringBuilder();
        String current_line;

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file_name));

            while ((current_line = bufferedReader.readLine()) != null)
            {
                query.append(current_line);
            }
        }
        catch (IOException ioException)
        {
            System.out.println(ioException.getLocalizedMessage());
        }

        return query.toString();
    }

    private static class Application
    {
        private static int id;
        private static String description, resume, cover_letter, status;
        private static URL url;
        private static Company company;
        private static Candidate candidate;

        public Application(int id, String desc, String url)
        {
            Application.id = id;
            Application.description = desc;
            try
            {
                Application.url = new URL(url);
            }
            catch (MalformedURLException e)
            {
                Application.url = null;
            }
        }

        public static void setCandidate(Candidate candidate)
        {
            Application.candidate = candidate;
        }

        public static void setCompany(Company company)
        {
            Application.company = company;
        }
    }

    private static class Candidate
    {
        private static int id;
        private static String first_name, last_name;
        private static Contact contact_information;

        Candidate(int id, String fname, String lname)
        {
            Candidate.id = id;
            Candidate.first_name = fname;
            Candidate.last_name = lname;
        }

        public static void setContact_information(Contact contact_information)
        {
            Candidate.contact_information = contact_information;
        }
    }

    private static class Company
    {
        private static int id;
        private static String name, hr_name;
        private static Contact contact_information;

        Company(int id, String company_name)
        {
            Company.id = id;
            Company.name = company_name;
        }

        public static void setContact_information(Contact contact_information)
        {
            Company.contact_information = contact_information;
        }
    }

    private static class Contact
    {
        private static int id;
        private static String email, phone;
        private static Address address;

        Contact(int id, String email, String phone)
        {
            Contact.email = email;
            Contact.phone = phone;
        }

        public static void setAddress(Address address)
        {
            Contact.address = address;
        }
    }

    private static class Address
    {
        private static int id, street_num;
        private static String street_name, city, state, country;

        Address(int id, int street_num, String street_name, String city, String state, String country)
        {
            Address.id = id;
            Address.street_num = street_num;
            Address.street_name = street_name;
            Address.city = city;
            Address.state = state;
            Address.country = country;
        }
    }
}
